package pro.sky.graduate_work_group5_team1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pro.sky.graduate_work_group5_team1.mapper.AdsCommentListMapper;
import pro.sky.graduate_work_group5_team1.mapper.AdsCommentMapper;
import pro.sky.graduate_work_group5_team1.mapper.AdsListMapper;
import pro.sky.graduate_work_group5_team1.mapper.AdsMapper;
import pro.sky.graduate_work_group5_team1.model.Ads;
import pro.sky.graduate_work_group5_team1.model.dto.*;
import pro.sky.graduate_work_group5_team1.repository.AdsRepository;
import pro.sky.graduate_work_group5_team1.repository.CommentRepository;
import pro.sky.graduate_work_group5_team1.service.AdsService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsMapper adsMapper;

    private final AdsListMapper adsListMapper;

    private final AdsCommentMapper adsCommentMapper;

    private final AdsCommentListMapper adsCommentListMapper;

    private final AdsRepository adsRepository;

    private final CommentRepository commentRepository;




    @Override
    public AdsCommentDto addAdsComments(Integer ad_pk, AdsCommentDto adsCommentDto) {
        return null;
    }

    @Override
    public AdsDto addAds(CreateAds createAds) {
        log.info("Добавляем объявление с title {}:", createAds.getTitle());
        Ads adsToCommit = new Ads();

        adsToCommit.setDescription(createAds.getDescription());
        adsToCommit.setImage(createAds.getImage());
        adsToCommit.setPk(createAds.getPk());
        adsToCommit.setPrice(createAds.getPrice());
        adsToCommit.setTitle(createAds.getTitle());

        adsRepository.save(adsToCommit);

        return adsMapper.toDto(adsToCommit);
    }

    @Override
    public void deleteAdsComment(Integer adPk, Integer id) {

    }

    @Override
    public ResponseWrapperAds getALLAds() {
        log.info("Ищем все объявления");
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        List<AdsDto> results = adsListMapper.toDtoList(adsRepository.findAll());
        responseWrapperAds.setResults(results);
        responseWrapperAds.setCount(results.size());
        return responseWrapperAds;
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
        log.info("Удаляем объявление с id {}:", id);
        adsRepository.deleteById(id);
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
