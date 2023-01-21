package pro.sky.graduate_work_group5_team1.service;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.model.AdsPhoto;

public interface AdsPhotoService {


    AdsPhoto savePhoto(MultipartFile file);

    byte[] getPhoto(Long id);

    void patchPhoto(Integer imageId, MultipartFile file);
}
