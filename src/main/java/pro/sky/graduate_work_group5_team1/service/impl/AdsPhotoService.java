package pro.sky.graduate_work_group5_team1.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.model.Ads;
import pro.sky.graduate_work_group5_team1.model.AdsPhoto;
import pro.sky.graduate_work_group5_team1.repository.AdsPhotoRepository;
import pro.sky.graduate_work_group5_team1.repository.AdsRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AdsPhotoService implements pro.sky.graduate_work_group5_team1.service.AdsPhotoService {

    private final AdsPhotoRepository adsPhotoRepository;
    private final AdsRepository adsRepository;

    public AdsPhotoService(AdsPhotoRepository adsPhotoRepository, AdsRepository adsRepository) {
        this.adsPhotoRepository = adsPhotoRepository;
        this.adsRepository = adsRepository;
    }

    @Value("&{path.to.photo.folder}")
    private String photoDir;
    private final Logger logger = LoggerFactory.getLogger(AdsPhotoService.class);

    public void uploadPhoto(Long adsId, MultipartFile adsPhotoFile) throws IOException {
        logger.debug("method uploadPhoto is started");
        Ads ads = adsRepository.getReferenceById(adsId.intValue());
        Path filePath = Path.of(photoDir, ads + "." + getExtensions(Objects.requireNonNull(adsPhotoFile.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = adsPhotoFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        AdsPhoto adsPhoto = findAdsPhoto(adsId);
        adsPhoto.setFilePath(filePath.toString());
        adsPhoto.setFileSize(adsPhotoFile.getSize());
        adsPhoto.setMediaType(adsPhotoFile.getContentType());
        adsPhoto.setData(adsPhotoFile.getBytes());
        adsPhotoRepository.save(adsPhoto);
    }

    public String savePhoto(MultipartFile file) {
        AdsPhoto adsPhoto = new AdsPhoto();
        adsPhoto.setFileSize(file.getSize());
        adsPhoto.setMediaType(file.getContentType());
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        try {
            adsPhoto.setData(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Long photoId = adsPhotoRepository.save(adsPhoto).getId();
        return "/images/" + photoId;
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @Transactional
    @Override
    public byte[] getPhoto(Long id) {
        AdsPhoto photo = adsPhotoRepository.getReferenceById(id);
        return photo.getData();
    }

    @Override
    public void patchPhoto(Integer imageId, MultipartFile file) {
        AdsPhoto adsPhotoToPatch = adsPhotoRepository.findById(imageId.longValue()).get();
        try {
            adsPhotoToPatch.setData(file.getBytes());
            adsPhotoToPatch.setFileSize(file.getSize());
            adsPhotoToPatch.setMediaType(file.getContentType());
            adsPhotoRepository.save(adsPhotoToPatch);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public AdsPhoto findAdsPhoto(long adsId) {
        logger.debug("method findAdsPhoto is started");
        AdsPhoto adsPhoto = new AdsPhoto();
        adsPhoto = adsPhotoRepository.findAdsPhotoByAds_Pk((int) adsId);
        return adsPhoto;
    }
}
