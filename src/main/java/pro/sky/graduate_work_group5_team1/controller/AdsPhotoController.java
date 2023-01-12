package pro.sky.graduate_work_group5_team1.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.model.AdsPhoto;
import pro.sky.graduate_work_group5_team1.service.impl.AdsPhotoServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class AdsPhotoController {
    AdsPhotoServiceImpl adsPhotoServiceImpl;

    public AdsPhotoController(AdsPhotoServiceImpl adsPhotoServiceImpl) {
        this.adsPhotoServiceImpl = adsPhotoServiceImpl;
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

    @GetMapping(value = "/{id}/photo-from-file")
    public void downloadPhoto(@PathVariable Long id, HttpServletResponse response) throws IOException {
        AdsPhoto adsPhoto = adsPhotoServiceImpl.findAdsPhoto(id);
        Path path = Path.of(adsPhoto.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(adsPhoto.getMediaType());
            response.setContentLength((int) adsPhoto.getFileSize());
            is.transferTo(os);
        }
    }
}
