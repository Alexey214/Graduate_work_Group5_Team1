package pro.sky.graduate_work_group5_team1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.api.AdsApi;
import pro.sky.graduate_work_group5_team1.exeption.AdsCommentNotFoundException;
import pro.sky.graduate_work_group5_team1.exeption.UserNotFoundException;
import pro.sky.graduate_work_group5_team1.model.dto.*;
import pro.sky.graduate_work_group5_team1.service.AdsService;

import javax.validation.constraints.Min;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
public class AdsController implements AdsApi {

    private final AdsService adsService;

    /**
     * Метод для получения всех объявлений. Возвращает ResponseWrapperAds. В полях которого Коллекция объявленийц и счетчик их кол-ва.
     * @return ResponseWrapperAds
     */
    @Override
    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getALLAds() {
        return ResponseEntity.ok(adsService.getALLAds());
    }

    /**
     * Метод для добавления объявления. Принимает текстовые данные и мультипарт файл фотографии.
     *
     * @param createAds
     * @param file
     * @return AdsDto
     */
    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAds(@RequestPart("properties") CreateAds createAds,
                                         @RequestPart("image") MultipartFile file) {
        return ResponseEntity.ok(adsService.addAds(createAds, file));
    }

    /**
     * Метод для получения всех комментариев объявления. Возвращает ResponseWrapperAdsComment. В полях которого Коллекция комментариев и счетчик их кол-ва.
     * В метод передается id объявления.
     * @param adPk
     * @return ResponseWrapperAdsComment
     */
    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @GetMapping("/{adPk}/comments")
    public ResponseEntity<ResponseWrapperAdsComment> getAdsComments(@PathVariable("adPk") @Min(1) Integer adPk) {
        return ResponseEntity.ok(adsService.getAdsComments(adPk));
    }

    /**
     * Метод для добавления комментария к объявлению. В метод передается айди объявления и сущность комментария.
     *
     * @throws org.springframework.web.client.HttpClientErrorException.NotFound если автор объявления не найден
     * @param adPk
     * @param adsCommentDto
     * @return AdsCommentDto
     */
    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @PostMapping("/{adPk}/comments")
    public ResponseEntity<AdsCommentDto> addAdsComments(@PathVariable("adPk") Integer adPk,
                                                        @RequestBody AdsCommentDto adsCommentDto) {
        return ResponseEntity.ok(adsService.addAdsComments(adPk, adsCommentDto));
    }
    /**
     * Получение полной информации об объявлении (в т.ч. об авторе)
     *
     * @throws org.springframework.web.client.HttpClientErrorException.NotFound если пользователь или объявление не найдены
     * @param id идентификатор объявления
     * @return  FullAds
     */
    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<FullAds> getAds(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(adsService.getAds(id));
    }
    /**
     * Метод для удаления объявления по ID
     *
     * @param id идентификатор объявления
     * @return AdsDto
     */
    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<AdsDto> removeAds(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(adsService.removeAds(id));
    }
    /**
     * Метод для  изменения объявления. Возвращает изменённое объявление
     * @throws org.springframework.web.client.HttpClientErrorException.NotFound Если объявление не найдено
     * @param id        идентификатор объявления
     * @param createAds изменённое объявление
     * @return  AdsDto
     */
    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable("id") Integer id,
                                            @RequestBody CreateAds createAds) {
        return ResponseEntity.ok(adsService.updateAds(id, createAds));
    }
    /**
     * Метод для получения комментария
     *
     * @throws AdsCommentNotFoundException если комментарий не найден
     * @param adPk ключ объявления, к которому принадлежит комментарий
     * @param id   идентификатор комментария
     * @return  AdsCommentDto
     */
    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @GetMapping("/{adPk}/comments/{id}")
    public ResponseEntity<AdsCommentDto> getAdsComment(@PathVariable("adPk") Integer adPk,
                                                       @PathVariable("id") Integer id) {
        return ResponseEntity.ok(adsService.getAdsComment(adPk, id));
    }

    /**
     * Метод для удаления комментария
     *
     * @throws AdsCommentNotFoundException если комментарий не найден
     * @param adPk
     * @param id
     * @return AdsCommentDto
     */
    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @DeleteMapping("/{adPk}/comments/{id}")
    public ResponseEntity<AdsCommentDto> deleteAdsComment(@PathVariable("adPk") Integer adPk,
                                                          @PathVariable("id") Integer id) {
        return ResponseEntity.ok(adsService.deleteAdsComment(adPk, id));
    }

    /**
     *
     * @throws org.springframework.web.client.HttpClientErrorException.NotFound если комментарий, объявление, или пользователь не найдены.
     * @param adPk
     * @param id
     * @param adsCommentDto
     * @return измененный комментарий AdsCommentDto
     */
    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @PatchMapping("/{adPk}/comments/{id}")
    public ResponseEntity<AdsCommentDto> updateAdsComment(@PathVariable("adPk") Integer adPk,
                                                          @PathVariable("id") Integer id,
                                                          @RequestBody AdsCommentDto adsCommentDto) {
        return ResponseEntity.ok(adsService.updateAdsComment(adPk, id, adsCommentDto));
    }

    /**
     * Метод для получения всех объявлений авторизованного пользователя. Возвращает список объявлений и счетчиком объявлений в виде ResponseWrapperAds
     * @throws UserNotFoundException если пользователь не найден
     * @return ResponseWrapperAds
     */
    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe() {
        return ResponseEntity.ok(adsService.getAdsMe());
    }

    /**
     * Обновление фотографии объявления
     * @throws pro.sky.graduate_work_group5_team1.exeption.AdsNotFoundException если объявление не найдено
     * @param id
     * @param file
     * @return AdsDto
     */
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> updateAdsImage(@PathVariable("id") Integer id,
                                                 @RequestPart("image") MultipartFile file) {
        if (adsService.patchAdsImage(id, file) == 1) {
            return ResponseEntity.ok(null);
        } else return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
