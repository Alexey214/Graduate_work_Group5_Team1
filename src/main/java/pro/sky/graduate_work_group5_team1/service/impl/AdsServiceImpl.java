package pro.sky.graduate_work_group5_team1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pro.sky.graduate_work_group5_team1.exeption.AdsCommentNotFoundException;
import pro.sky.graduate_work_group5_team1.exeption.AdsNotFoundException;
import pro.sky.graduate_work_group5_team1.exeption.UserNotFoundException;
import pro.sky.graduate_work_group5_team1.mapper.AdsCommentMapper;
import pro.sky.graduate_work_group5_team1.mapper.AdsListMapper;
import pro.sky.graduate_work_group5_team1.mapper.AdsMapper;
import pro.sky.graduate_work_group5_team1.model.Ads;
import pro.sky.graduate_work_group5_team1.model.AdsComment;
import pro.sky.graduate_work_group5_team1.model.User;
import pro.sky.graduate_work_group5_team1.model.dto.*;
import pro.sky.graduate_work_group5_team1.repository.AdsRepository;
import pro.sky.graduate_work_group5_team1.repository.AdsCommentRepository;
import pro.sky.graduate_work_group5_team1.repository.UserRepository;
import pro.sky.graduate_work_group5_team1.service.AdsService;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsMapper adsMapper;
    private final AdsListMapper adsListMapper;
    private final AdsCommentMapper adsCommentMapper;
    private final AdsRepository adsRepository;
    private final AdsCommentRepository adsCommentRepository;
    private final UserRepository userRepository;


    @Override
    public AdsCommentDto addAdsComments(Integer adPk, AdsCommentDto adsCommentDto) {
        log.debug("Добавляем комментарий {}, принадлежащий объявлению с ключом {}", adsCommentDto, adPk);
        AdsComment adsComment = adsCommentMapper.toModel(adsCommentDto);
        adsCommentRepository.save(adsComment);
        return adsCommentMapper.toDto(adsComment);
    }

    @Override
    public AdsDto addAds(CreateAds createAds) {
        log.debug("Добавляем объявление с title {}:", createAds.getTitle());
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
    public AdsCommentDto deleteAdsComment(Integer adPk, Integer id) {
        log.info("Удаляем комментарий с id {}, относящийся к объявлению с ключом {}", id, adPk);
        AdsComment adsComment = adsCommentRepository.findAdsCommentByPkAndId(adPk, id)
                .orElseThrow(AdsCommentNotFoundException::new);
        if (adsComment != null) {
            adsCommentRepository.deleteById(adsComment.getId());
            log.warn("Комментарий с id {} удален", id);
        }
        return adsCommentMapper.toDto(adsComment);
    }

    @Override
    public ResponseWrapperAds getALLAds() {
        log.info("Ищем все объявления");
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        List<AdsDto> results = adsListMapper.toDtoList(adsRepository.findAll());
        results.stream().sorted(Comparator.comparing(AdsDto::getAuthor));
        responseWrapperAds.setResults(results);
        responseWrapperAds.setCount(results.size());
        return responseWrapperAds;
    }

    @Override
    public AdsCommentDto getAdsComment(Integer adPk, Integer id) {
        log.info("Получаем комментарий с id {}, относящийся к объявлению с ключом {}", id, adPk);
        AdsComment adsComment = adsCommentRepository.findAdsCommentByPkAndId(adPk, id)
                .orElseThrow(AdsCommentNotFoundException::new);
        return adsCommentMapper.toDto(adsComment);
    }

    @Override
    public ResponseWrapperAdsComment getAdsComments(Integer adPk) {
        log.info("Получаем все комментарии одного объявления с pk " + adPk);
        List<AdsCommentDto> adsCommentList = adsCommentRepository.findAll().stream()
                .map(adsCommentMapper::toDto)
                .sorted(Comparator.comparing(AdsCommentDto::getCreatedAt))
                .toList();
        ResponseWrapperAdsComment responseWrapperAdsComment = new ResponseWrapperAdsComment();
        if (!adsCommentList.isEmpty()) {
            log.info("В списке {} комментариев ", adsCommentList.size());
            responseWrapperAdsComment.setResults(adsCommentList);
            responseWrapperAdsComment.setCount(adsCommentList.size());
        }
        return responseWrapperAdsComment;
    }

    @Override
    public ResponseWrapperAds getAdsMe(Boolean authenticated,
                                       String authorities0Authority,
                                       Object credentials,
                                       Object details,
                                       Object principal) {
        return null;
    }

    @Override
    public FullAds getAds(Integer id) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsCommentNotFoundException::new);
        User user = userRepository.findById(ads.getAuthor().getId()).orElseThrow(UserNotFoundException::new);
        FullAds fullAds = new FullAds();
        fullAds.setAuthorFirstName(user.getFirstName());
        fullAds.setAuthorLastName(user.getLastName());
        fullAds.setDescription(ads.getDescription());
        fullAds.setEmail(user.getEmail());
        fullAds.setImage(ads.getImage());
        fullAds.setPhone(user.getPhone());
        fullAds.setPk(ads.getPk());
        fullAds.setPrice(ads.getPrice());
        fullAds.setTitle(ads.getTitle());
        log.info("Создаём FullAds {}", fullAds);
        return fullAds;
    }

    @Override
    public AdsDto removeAds(Integer id) {
        log.info("Удаляем объявление с id {}:", id);
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        if (ads != null) {
            adsRepository.deleteById(id);
            log.warn("Объявление с id {} удалено", id);
        }
        return adsMapper.toDto(ads);
    }

    @Override
    public AdsCommentDto updateAdsComment(Integer adPk, Integer id, AdsCommentDto adsCommentDto) {
        AdsComment adsComment = adsCommentRepository.findAdsCommentByPkAndId(adPk, id)
                .orElseThrow(AdsCommentNotFoundException::new);
        log.debug("Изменяем комментарий {}, принадлежащее пользователю с id {} и относящийся к объявлению с ключом {}",
                adsComment, id, adPk);
        adsComment.setAuthor(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
        adsComment.setPk(adsRepository.findById(adPk).orElseThrow(AdsNotFoundException::new));
        adsComment.setText(adsComment.getText());
        adsComment.setCreatedAt(adsCommentDto.getCreatedAt());
        adsCommentRepository.save(adsComment);
        return adsCommentDto;
    }

    @Override
    public AdsDto updateAds(Integer id, AdsDto adsDto) {
        log.debug("Изменяем объявление {}, принадлежащее пользователю с id {}", adsDto, id);
        Ads ads = adsMapper.toModel(adsDto);
        ads.setPk(id);
        adsRepository.save(ads);
        return adsDto;
    }
}
