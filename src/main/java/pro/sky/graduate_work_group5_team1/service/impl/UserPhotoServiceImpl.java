package pro.sky.graduate_work_group5_team1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.model.UserPhoto;
import pro.sky.graduate_work_group5_team1.repository.UserPhotoRepository;
import pro.sky.graduate_work_group5_team1.service.UserPhotoService;
import pro.sky.graduate_work_group5_team1.security.UtilSecurity;
import pro.sky.graduate_work_group5_team1.util.UtilClassGraduate;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserPhotoServiceImpl implements UserPhotoService, UtilSecurity, UtilClassGraduate {

    private final UserPhotoRepository userPhotoRepository;

    @Override
    public String createEmptyPhoto() {
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setFileSize(0);
        userPhoto.setMediaType(null);
        userPhoto.setData(null);
        Long photoId = userPhotoRepository.save(userPhoto).getId();
        return "/users/me/image/" + photoId;
    }

    @Override
    public byte[] getPhoto() {
        Long photoId = getUserPhotoId(getUser().getImage());
        UserPhoto userPhoto = userPhotoRepository.getReferenceById(photoId);
        return userPhoto.getData();
    }

    @Override
    public Integer patchPhoto(MultipartFile photo) {
        Long photoId = getUserPhotoId(getUser().getImage());
        UserPhoto userPhoto = userPhotoRepository.getReferenceById(photoId);
        try {
            userPhoto.setData(photo.getBytes());
            userPhoto.setFileSize(photo.getSize());
            userPhoto.setMediaType(photo.getContentType());
            userPhotoRepository.save(userPhoto);
            return 1;
        } catch (IOException e) {
            return 0;
        }
    }

    private Long getUserPhotoId(String photoPath) {
        String[] photoPathArray = photoPath.split("/");
        return Long.parseLong(photoPathArray[photoPathArray.length - 1]);
    }
}
