package pro.sky.graduate_work_group5_team1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.api.AdsApi;
import pro.sky.graduate_work_group5_team1.model.User;
import pro.sky.graduate_work_group5_team1.model.dto.*;
import pro.sky.graduate_work_group5_team1.service.AdsService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
public class AdsController implements AdsApi {

    private final AdsService adsService;

    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @PostMapping("/{adPk}/comments")
    public ResponseEntity<AdsCommentDto> addAdsComments(@PathVariable("adPk") String adPk,
                                                        @RequestBody AdsCommentDto adsCommentDto) {
        if (Integer.parseInt(adPk) < 0 || adsCommentDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        AdsCommentDto adsCommentDtoTmp = adsService.addAdsComments(Integer.parseInt(adPk), adsCommentDto);
        if (adsCommentDtoTmp == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(adsCommentDtoTmp);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAds(@RequestPart("properties") CreateAds createAds,
                                         @RequestPart("image") MultipartFile file) {
        if (createAds == null || file == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        AdsDto adsDto = adsService.addAds(createAds, file, user);
        if (adsDto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(adsDto);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @DeleteMapping("/{adPk}/comments/{id}")
    public ResponseEntity<AdsCommentDto> deleteAdsComment(@PathVariable("adPk") String adPk,
                                                          @PathVariable("id") Integer id) {
        if (Integer.parseInt(adPk) < 0 && id < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        AdsCommentDto adsCommentDto = adsService.deleteAdsComment(Integer.parseInt(adPk), id, user);
        if (adsCommentDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(adsCommentDto);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getALLAds() {
        ResponseWrapperAds responseWrapperAds = adsService.getALLAds();
//        if (responseWrapperAds.getCount() == 0) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
        return ResponseEntity.ok(responseWrapperAds);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @GetMapping("/{adPk}/comments/{id}")
    public ResponseEntity<AdsCommentDto> getAdsComment(@PathVariable("adPk") String adPk,
                                                       @PathVariable("id") Integer id) {
        if (Integer.parseInt(adPk) < 0 && id < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        AdsCommentDto adsCommentDto = null;
        try {
            adsCommentDto = adsService.getAdsComment(Integer.parseInt(adPk), id);
        } catch (Exception ignored) {}
        if (adsCommentDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(adsCommentDto);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @GetMapping("/{adPk}/comments")
    public ResponseEntity<ResponseWrapperAdsComment> getAdsComments(@PathVariable("adPk") String adPk) {
        if (Integer.parseInt(adPk) < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        ResponseWrapperAdsComment responseWrapperAdsComment = adsService.getAdsComments(Integer.parseInt(adPk));
        try {
            if (responseWrapperAdsComment.getCount() == 0) {
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(responseWrapperAdsComment);
    }

    /*
    Тут прям муть какая-то. В yaml файле указаны какие-то query запросы.
    Типы объектов тут тоже в половине случаев - object.
    В общем кто будет делать - нужно всё это перепроверить.
     */
    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe() {
        ResponseWrapperAds responseWrapperAds = adsService.getAdsMe();
        return ResponseEntity.ok(responseWrapperAds);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<FullAds> getAds(@PathVariable("id") Integer id) {
        if (id < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        FullAds fullAds = adsService.getAds(id);
        if (fullAds == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(fullAds);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<AdsDto> removeAds(@PathVariable("id") Integer id) {
        if (id < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        AdsDto adsDto = adsService.removeAds(id, user);
        if (adsDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(adsDto);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @PatchMapping("/{adPk}/comments/{id}")
    public ResponseEntity<AdsCommentDto> updateAdsComment(@PathVariable("adPk") String adPk,
                                                          @PathVariable("id") Integer id,
                                                          @RequestBody AdsCommentDto adsCommentDto) {
        if (Integer.parseInt(adPk) < 0 && id < 0 && adsCommentDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        AdsCommentDto adsCommentDtoTmp = adsService.updateAdsComment(Integer.parseInt(adPk), id, adsCommentDto, user);
        if (adsCommentDtoTmp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(adsCommentDto);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable("id") Integer id, @RequestBody CreateAds createAds) {
        if (id < 0 && createAds == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        AdsDto adsDtoTmp = adsService.updateAds(id, createAds, user);
        if (adsDtoTmp == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(adsDtoTmp);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> updateAdsImage(@PathVariable("id") Integer id, @RequestPart("image") MultipartFile file) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        if (adsService.patchAdsImage(id, file, user) == 1) {
            return ResponseEntity.ok(null);
        } else return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }
}
