package pro.sky.graduate_work_group5_team1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.model.AdsPhoto;
import pro.sky.graduate_work_group5_team1.service.impl.AdsPhotoServiceImpl;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
public class AdsPhotoController {

    private final AdsPhotoServiceImpl adsPhotoServiceImpl;

    @GetMapping(value = "/images/{id}", produces = {MediaType.IMAGE_PNG_VALUE})
    public byte[] getImage(@PathVariable("id") Long id) {
        return adsPhotoServiceImpl.getPhoto(id);
    }


    @PostMapping(value = "/{adsId}/adsPhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPhoto(@PathVariable Long adsId, @RequestParam MultipartFile adsPhoto) throws Exception {
        adsPhotoServiceImpl.uploadPhoto(adsId, adsPhoto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/photo-from-db")
    public ResponseEntity<byte[]> downloadPhoto(@PathVariable Long id) {
        AdsPhoto adsPhoto = adsPhotoServiceImpl.findAdsPhoto(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(adsPhoto.getMediaType()));
        headers.setContentLength(adsPhoto.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(adsPhoto.getData());
    }
}
