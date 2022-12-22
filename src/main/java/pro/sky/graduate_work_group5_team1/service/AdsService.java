package pro.sky.graduate_work_group5_team1.service;

import pro.sky.graduate_work_group5_team1.model.AdsComment;
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
    AdsDto addAds(CreateAds createAds);

    /**
     * Метод для удаления комментария
     *
     * @param adPk ключ объявления, в котором написан комментарий
     * @param id   идентификатор комментария
     * @return
     */
    AdsCommentDto deleteAdsComment(Integer adPk, Integer id);

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
     *
     * @param authenticated
     * @param authorities0Authority
     * @param credentials
     * @param details
     * @param principal
     * @return
     */
    ResponseWrapperAds getAdsMe(Boolean authenticated,
                                String authorities0Authority,
                                Object credentials,
                                Object details,
                                Object principal);

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
    AdsDto removeAds(Integer id);

    /**
     * Метод для изменения комментария
     *
     * @param adPk          ключ объявления
     * @param id            идентификатор комментария
     * @param adsCommentDto изменённый комментарий
     * @return возвращает изменённый экземпляр {@link AdsCommentDto}
     */
    AdsCommentDto updateAdsComment(Integer adPk, Integer id, AdsCommentDto adsCommentDto);

    /**
     * Метод изменения объявления
     *
     * @param id     идентификатор объявления
     * @param adsDto изменённое объявление
     * @return возвращает изменённый экземпляр {@link AdsDto}
     */
    AdsDto updateAds(Integer id, AdsDto adsDto);
}
