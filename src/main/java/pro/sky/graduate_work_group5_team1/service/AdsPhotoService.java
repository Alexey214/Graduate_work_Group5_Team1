package pro.sky.graduate_work_group5_team1.service;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.model.Ads;
import pro.sky.graduate_work_group5_team1.model.AdsPhoto;


import java.io.IOException;

public interface AdsPhotoService {
    /**
     * Метод для загрузки фото к обьявлению
     *
     * @param adsId        id объявления к которому добавляется фото. Так же копируется в поле id фото в БД
     * @param adsPhotoFile мультипарт файл фотографии
     */
    void uploadPhoto(Long adsId, MultipartFile adsPhotoFile) throws IOException;

    /**
     * Метод ищет фото по id объявления
     *
     * @param adsId
     * @return AdsPhoto
     */
    AdsPhoto findAdsPhoto(long adsId);

    String savePhoto(MultipartFile file);

    byte[] getPhoto(Long id);
}
