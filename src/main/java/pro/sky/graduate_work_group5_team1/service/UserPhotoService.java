package pro.sky.graduate_work_group5_team1.service;

import org.springframework.web.multipart.MultipartFile;

public interface UserPhotoService {

    String createEmptyPhoto();

    byte[] getPhoto();

    Integer patchPhoto(MultipartFile photo);
}
