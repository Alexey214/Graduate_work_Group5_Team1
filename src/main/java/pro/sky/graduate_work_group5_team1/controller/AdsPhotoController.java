package pro.sky.graduate_work_group5_team1.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.model.AdsPhoto;
import pro.sky.graduate_work_group5_team1.service.impl.AdsPhotoService;

@RestController
public class AdsPhotoController {

    private final AdsPhotoService adsPhotoService;

    public AdsPhotoController(AdsPhotoService adsPhotoService) {
        this.adsPhotoService = adsPhotoService;
    }

    @PostMapping(value = "/{adsId}/adsPhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPhoto(@PathVariable Long adsId, @RequestParam MultipartFile adsPhoto) throws Exception {
        adsPhotoService.uploadPhoto(adsId, adsPhoto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/photo-from-db")
    public ResponseEntity<byte[]> downloadPhoto(@PathVariable Long id) {
        AdsPhoto adsPhoto = adsPhotoService.findAdsPhoto(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(adsPhoto.getMediaType()));
        headers.setContentLength(adsPhoto.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(adsPhoto.getData());
    }
}
