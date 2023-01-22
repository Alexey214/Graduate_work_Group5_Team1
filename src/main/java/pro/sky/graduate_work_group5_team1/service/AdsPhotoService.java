package pro.sky.graduate_work_group5_team1.service;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.model.AdsPhoto;

public interface AdsPhotoService {


    /**
     * Метод сохранения изображения объявления
     *
     * @param file изображение объявления
     * @return возвращает эндпоинт изображения объявления в виде строки
     */
    AdsPhoto savePhoto(MultipartFile file);

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
}
