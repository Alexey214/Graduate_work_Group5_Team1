package pro.sky.graduate_work_group5_team1.service;

import org.springframework.web.multipart.MultipartFile;

public interface UserPhotoService {

    /**
     * Метод для создания пустого эндпоинта аватара пользователя
     *
     * @return возвращает эндпоинт в виде строки
     */
    String createEmptyPhoto();

    /**
     * Метод получения аватара пользователя в виде массива байт
     *
     * @return возвращает изображение в виде массива байт
     */
    byte[] getPhoto();

    /**
     * Метод изменения аватара пользователя
     *
     * @param photo новый аватар пользователя
     * @return возвращает "1" в случае успеха и "0" в если операция невозможна
     */
    Integer patchPhoto(MultipartFile photo);
}
