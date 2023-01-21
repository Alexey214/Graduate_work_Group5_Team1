package pro.sky.graduate_work_group5_team1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pro.sky.graduate_work_group5_team1.service.impl.AdsPhotoServiceImpl;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
public class AdsPhotoController {

    private final AdsPhotoServiceImpl adsPhotoServiceImpl;

    /**
     * метод для получения фото объявления.
     * @param id
     * @return byte []
     */
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @GetMapping(value = "/images/{id}", produces = {MediaType.IMAGE_PNG_VALUE})
    public byte[] getImage(@PathVariable("id") Long id) {
        return adsPhotoServiceImpl.getPhoto(id);
    }
}
