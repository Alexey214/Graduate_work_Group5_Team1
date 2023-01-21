package pro.sky.graduate_work_group5_team1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.model.Ads;
import pro.sky.graduate_work_group5_team1.model.AdsPhoto;
import pro.sky.graduate_work_group5_team1.repository.AdsPhotoRepository;
import pro.sky.graduate_work_group5_team1.repository.AdsRepository;
import pro.sky.graduate_work_group5_team1.service.AdsPhotoService;
import pro.sky.graduate_work_group5_team1.util.UtilClassGraduate;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdsPhotoServiceImpl implements AdsPhotoService, UtilClassGraduate {

    private final AdsPhotoRepository adsPhotoRepository;
    private final AdsRepository adsRepository;

    @Value("&{path.to.photo.folder}")
    private String photoDir;

    public void uploadPhoto(Long adsId, MultipartFile adsPhotoFile) throws IOException {
        log.debug("method {} is started", methodName());
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
        log.debug("method {} is started", methodName());
        AdsPhoto adsPhoto = new AdsPhoto();
        adsPhoto.setFileSize(file.getSize());
        adsPhoto.setMediaType(file.getContentType());
        try {
            adsPhoto.setData(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Long photoId = adsPhotoRepository.save(adsPhoto).getId();
        return "/images/" + photoId;
    }

    @Transactional
    @Override
    public byte[] getPhoto(Long id) {
        log.debug("method {} is started. getPhoto {}", methodName(), id);
        AdsPhoto photo = adsPhotoRepository.getReferenceById(id);
        return photo.getData();
    }

    @Override
    public void patchPhoto(Integer imageId, MultipartFile file) {
        log.debug("method {} is started. patchPhoto {}", methodName(), imageId);
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
        log.debug("method {} is started. Get fileName {}", methodName(), fileName);
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public AdsPhoto findAdsPhoto(long adsId) {
        log.debug("method {} is started from adsId {}", methodName(), adsId);
        AdsPhoto adsPhoto = new AdsPhoto();
        adsPhoto = adsPhotoRepository.findAdsPhotoByAds_Pk((int) adsId);
        return adsPhoto;
    }

    @Override
    public void deleteImage(String photoPath) {
        String[] photoPathArray = photoPath.split("/");
        Long photoId = Long.parseLong(photoPathArray[photoPathArray.length - 1]);
        adsPhotoRepository.deleteById(photoId);
    }
}
