package pro.sky.graduate_work_group5_team1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.model.AdsPhoto;
import pro.sky.graduate_work_group5_team1.repository.AdsPhotoRepository;
import pro.sky.graduate_work_group5_team1.service.AdsPhotoService;
import pro.sky.graduate_work_group5_team1.util.UtilClassGraduate;

import java.io.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdsPhotoServiceImpl implements AdsPhotoService, UtilClassGraduate {

    private final AdsPhotoRepository adsPhotoRepository;

    public AdsPhoto savePhoto(MultipartFile file) {
        log.debug("method {} is started", methodName());
        AdsPhoto adsPhoto = new AdsPhoto();
        adsPhoto.setFileSize(file.getSize());
        adsPhoto.setMediaType(file.getContentType());
        try {
            adsPhoto.setData(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return adsPhotoRepository.save(adsPhoto);
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
        AdsPhoto adsPhotoToPatch = adsPhotoRepository.findById(imageId.longValue()).orElse(null);
        try {
            assert adsPhotoToPatch != null;
            adsPhotoToPatch.setData(file.getBytes());
            adsPhotoToPatch.setFileSize(file.getSize());
            adsPhotoToPatch.setMediaType(file.getContentType());
            adsPhotoRepository.save(adsPhotoToPatch);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
