package pro.sky.graduate_work_group5_team1.service;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.model.Ads;
import pro.sky.graduate_work_group5_team1.model.AdsPhoto;


import java.io.IOException;

public interface AdsPhotoService {
    /**
     * Метод для загрузки фото к обьявлению
     *
     * @param adsId        идентификатор объявления к которому добавляется фото. Так же копируется в поле id фото в БД
     * @param adsPhotoFile мультипарт файл фотографии
     */
    void uploadPhoto(Long adsId, MultipartFile adsPhotoFile) throws IOException;

    /**
     * Метод ищет фото по id объявления
     *
     * @param adsId ключ объявления
     * @return AdsPhoto
     */
    AdsPhoto findAdsPhoto(long adsId);

    /**
     * Метод сохранения изображения объявления
     *
     * @param file изображение объявления
     * @return возвращает эндпоинт изображения объявления в виде строки
     */
    String savePhoto(MultipartFile file);

    /**
     * Метод получения изображения объявления
     *
     * @param id идентификатор объявления
     * @return возвращает изображение в виде массива байт
     */
    byte[] getPhoto(Long id);

    /**
     * Метод изменения изображения объявления
     *
     * @param imageId идентификатор изображения
     * @param file    изображение объявления
     */
    void patchPhoto(Integer imageId, MultipartFile file);

    /**
     * Метод удаления изображения
     *
     * @param photoPath путь к изображению
     */
    void deleteImage(String photoPath);
}
