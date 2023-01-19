package pro.sky.graduate_work_group5_team1.service;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.model.Ads;
import pro.sky.graduate_work_group5_team1.model.AdsComment;
import pro.sky.graduate_work_group5_team1.model.User;
import pro.sky.graduate_work_group5_team1.model.dto.*;

public interface AdsService {

    /**
     * Метод для добавления комментариев к объявлению
     *
     * @param adPk          ключ, соответствующий данному {@link AdsComment}
     * @param adsCommentDto сущность комментария с ключевым полем "text"
     * @return возвращает добавленный экземпляр {@link AdsCommentDto}
     */
    AdsCommentDto addAdsComments(Integer adPk, AdsCommentDto adsCommentDto);

    /**
     * Метод добавления объявления
     *
     * @param createAds экземпляр нового объявления
     * @return возвращает экземпляр класса {@link AdsDto}
     */
    AdsDto addAds(CreateAds createAds, MultipartFile file, User userId);

    /**
     * Метод для удаления комментария
     *
     * @param adPk ключ объявления, в котором написан комментарий
     * @param id   идентификатор комментария
     * @return
     */
    AdsCommentDto deleteAdsComment(Integer adPk, Integer id, User user);

    /**
     * Метод для получения списка всех объявлений с указанием их количества
     *
     * @return возвращает экземпляр {@link ResponseWrapperAds}
     */
    ResponseWrapperAds getALLAds();

    /**
     * Метод для получения комментария
     *
     * @param adPk ключ объявления, к которому принадлежит комментарий
     * @param id   идентификатор комментария
     * @return возвращает экземпляр {@link AdsCommentDto}
     */
    AdsCommentDto getAdsComment(Integer adPk, Integer id);

    /**
     * Метод для получения списка всех комментариев одного объявления и указанием их количества
     *
     * @param adPk ключ объявления
     * @return возвращает экземпляр {@link ResponseWrapperAdsComment}
     */
    ResponseWrapperAdsComment getAdsComments(Integer adPk);

    /**
     * Метод загадка
     */
    ResponseWrapperAds getAdsMe();

    /**
     * Получение полной информации об объявлении (в т.ч. об авторе)
     *
     * @param id идентификатор объявления
     * @return возвращает экземпляр {@link FullAds}
     */
    FullAds getAds(Integer id);

    /**
     * Метод для удаления объявления
     *
     * @param id идентификатор объявления
     * @return
     */
    AdsDto removeAds(Integer id, User user);

    /**
     * Метод для изменения комментария
     *
     * @param adPk          ключ объявления
     * @param id            идентификатор комментария
     * @param adsCommentDto изменённый комментарий
     * @return возвращает изменённый экземпляр {@link AdsCommentDto}
     */
    AdsCommentDto updateAdsComment(Integer adPk, Integer id, AdsCommentDto adsCommentDto, User user);

    /**
     * Метод изменения объявления
     *
     * @param id     идентификатор объявления
     * @param createAds изменённое объявление
     * @return возвращает изменённый экземпляр {@link AdsDto}
     */
    AdsDto updateAds(Integer id, CreateAds createAds, User user);

    Integer patchAdsImage(Integer id, MultipartFile file, User user);
}
