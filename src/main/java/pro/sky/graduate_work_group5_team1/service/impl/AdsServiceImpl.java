package pro.sky.graduate_work_group5_team1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pro.sky.graduate_work_group5_team1.model.dto.*;
import pro.sky.graduate_work_group5_team1.service.AdsService;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {


    @Override
    public AdsCommentDto addAdsComments(Integer ad_pk, AdsCommentDto adsCommentDto) {
        return null;
    }

    @Override
    public AdsDto addAds(CreateAds createAds) {
        return null;
    }

    @Override
    public void deleteAdsComment(Integer adPk, Integer id) {

    }

    @Override
    public ResponseWrapperAds getALLAds() {
        return null;
    }

    @Override
    public AdsCommentDto getAdsComment(Integer adPk, Integer id) {
        return null;
    }

    @Override
    public ResponseWrapperAdsComment getAdsComments(Integer adPk) {
        return null;
    }

    @Override
    public ResponseWrapperAds getAdsMe(Boolean authenticated, String authorities0Authority, Object credentials, Object details, Object principal) {
        return null;
    }

    @Override
    public FullAds getAds(Integer id) {
        return null;
    }

    @Override
    public void removeAds(Integer id) {

    }

    @Override
    public AdsCommentDto updateAdsComment(Integer adPk, Integer id, AdsCommentDto adsCommentDto) {
        return null;
    }

    @Override
    public AdsDto updateAds(Integer id, AdsDto adsDto) {
        return null;
    }
}
