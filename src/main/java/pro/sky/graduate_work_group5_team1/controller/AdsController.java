package pro.sky.graduate_work_group5_team1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.graduate_work_group5_team1.api.AdsApi;
import pro.sky.graduate_work_group5_team1.model.dto.*;
import pro.sky.graduate_work_group5_team1.service.AdsService;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
public class AdsController implements AdsApi {

    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }


    @Override
    @PostMapping("/{ad_pk}/comment")
    public ResponseEntity<AdsCommentDto> addAdsComments(@PathVariable("ad_pk") String ad_pk, @RequestBody AdsCommentDto adsCommentDto) {
        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity<AdsDto> addAds(@RequestBody CreateAds createAds) {
        return null;
    }

    @Override
    @DeleteMapping("/{ad_pk}/comment/{id}")
    public ResponseEntity<Void> deleteAdsComment(@PathVariable("ad_pk") String adPk,
                                                 @PathVariable("id") Integer id) {
        return null;
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getALLAds() {
        return null;
    }

    @Override
    @GetMapping("/{ad_pk}/comment/{id}")
    public ResponseEntity<AdsCommentDto> getAdsComment(@PathVariable("ad_pk") String adPk,
                                                       @PathVariable("id") Integer id) {
        return null;
    }

    @Override
    @GetMapping("/{ad_pk}/comment")
    public ResponseEntity<ResponseWrapperAdsComment> getAdsComments(@PathVariable("ad_pk") String adPk) {
        return null;
    }

    /*
    Тут прям муть какая-то. В yaml файле указаны какие-то query запросы.
    Типы объектов тут тоже в половине случаев - object.
    В общем кто будет делать - нужно всё это перепроверить.
     */
    @Override
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe(@RequestParam(value = "authenticated", required = false) Boolean authenticated,
                                                       @RequestParam(value = "authorities[0].authority", required = false) String authorities0Authority,
                                                       @RequestParam(value = "credentials", required = false) Object credentials,
                                                       @RequestParam(value = "details", required = false) Object details,
                                                       @RequestParam(value = "principal", required = false) Object principal) {
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<FullAds> getAds(@PathVariable("id") Integer id) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAds(@PathVariable("id") Integer id) {
        return null;
    }

    @Override
    @PatchMapping("/{ad_pk}/comment/{id}")
    public ResponseEntity<AdsCommentDto> updateAdsComment(@PathVariable("ad_pk") String adPk,
                                                          @PathVariable("id") Integer id,
                                                          @RequestBody AdsCommentDto adsCommentDto) {
        return null;
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable("id") Integer id, @RequestBody AdsDto adsDto) {
        return null;
    }


}
