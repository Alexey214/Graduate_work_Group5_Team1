package pro.sky.graduate_work_group5_team1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
import pro.sky.graduate_work_group5_team1.security.UtilSecurity;
import pro.sky.graduate_work_group5_team1.service.AdsPhotoService;
import pro.sky.graduate_work_group5_team1.service.AdsService;
import pro.sky.graduate_work_group5_team1.util.UtilClassGraduate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService, UtilSecurity, UtilClassGraduate {

    private final AdsMapper adsMapper;
    private final AdsListMapper adsListMapper;
    private final AdsCommentMapper adsCommentMapper;
    private final AdsRepository adsRepository;
    private final AdsCommentRepository adsCommentRepository;
    private final UserRepository userRepository;

    private final AdsPhotoService adsPhotoService;

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @Override
    public AdsCommentDto addAdsComments(Integer adPk, AdsCommentDto adsCommentDto) {
        log.debug("{}. Добавляем комментарий {}, принадлежащий объявлению с ключом {}", methodName(), adsCommentDto, adPk);
        adsCommentDto.setAuthor(userRepository.findByEmail(login()).get().getId());
        adsCommentDto.setCreatedAt(LocalDateTime.now());
        adsCommentDto.setPk(adPk);
        AdsComment adsComment = adsCommentMapper.toModel(adsCommentDto);
        adsCommentRepository.save(adsComment);
        return adsCommentMapper.toDto(adsComment);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @Override
    public AdsDto addAds(CreateAds createAds, MultipartFile file, User user) {
        log.debug("{}. Добавляем объявление с title {}:", methodName(), createAds.getTitle());
        Ads adsToCommit = new Ads();

        adsToCommit.setDescription(createAds.getDescription());
        adsToCommit.setPrice(createAds.getPrice());
        adsToCommit.setTitle(createAds.getTitle());
        String photoPath = adsPhotoService.savePhoto(file);
        adsToCommit.setImage(photoPath);
        adsToCommit.setAuthor(user);

        adsRepository.save(adsToCommit);
        return adsMapper.toDto(adsToCommit);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @Override
    public AdsCommentDto deleteAdsComment(Integer adPk, Integer id) {
        log.info("{}. Удаляем комментарий с id {}, относящийся к объявлению с ключом {}", methodName(), id, adPk);
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
        log.info("{}. Ищем все объявления", methodName());
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        List<AdsDto> results = adsListMapper.toDtoList(adsRepository.findAll());
        results.stream().sorted(Comparator.comparing(AdsDto::getAuthor));
        responseWrapperAds.setResults(results);
        responseWrapperAds.setCount(results.size());
        return responseWrapperAds;
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @Override
    public AdsCommentDto getAdsComment(Integer adPk, Integer id) {
        log.info("{}. Получаем комментарий с id {}, относящийся к объявлению с ключом {}", methodName(), id, adPk);
        AdsComment adsComment = adsCommentRepository.findAdsCommentByPkAndId(adPk, id)
                .orElseThrow(AdsCommentNotFoundException::new);
        return adsCommentMapper.toDto(adsComment);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @Override
    public ResponseWrapperAdsComment getAdsComments(Integer adPk) {
        log.info("{}. Получаем все комментарии одного объявления с pk " + adPk, methodName());
//        List<AdsCommentDto> adsCommentList = adsCommentRepository.findAll().stream()
//                .map(adsCommentMapper::toDto)
//                .sorted(Comparator.comparing(AdsCommentDto::getCreatedAt))
//                .toList();
//        ResponseWrapperAdsComment responseWrapperAdsComment = new ResponseWrapperAdsComment();
        List<AdsComment> adsCommentList = adsCommentRepository.findAll();
        List<AdsCommentDto> adsCommentDtoList = new ArrayList<AdsCommentDto>(adsCommentList.size());
        for ( AdsComment adsComment : adsCommentList ) {
            adsCommentDtoList.add( adsCommentMapper.toDto( adsComment ) );
        }
//        if (!adsCommentList.isEmpty()) {
//            log.info("{}. В списке {} комментариев ", methodName(), adsCommentList.size());
//            responseWrapperAdsComment.setResults(adsCommentList);
//            responseWrapperAdsComment.setCount(adsCommentList.size());
//        }
        ResponseWrapperAdsComment responseWrapperAdsComment = new ResponseWrapperAdsComment();
        responseWrapperAdsComment.setCount(adsCommentDtoList.size());
        responseWrapperAdsComment.setResults(adsCommentDtoList);
        return responseWrapperAdsComment;
    }


    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @Override
    public ResponseWrapperAds getAdsMe() {
        String login = login();
        User user = userRepository.findByEmail(login).orElseThrow(() -> new UserNotFoundException(login));
        log.info("{}. Получаем все объявления одного пользователя" + user, methodName());
        List<Ads> adsList = adsRepository.findAllByAuthor(user);
        List<AdsDto> adsDtoList = adsListMapper.toDtoList(adsList);
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(adsDtoList.size());
        responseWrapperAds.setResults(adsDtoList);
        return responseWrapperAds;

    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
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
        log.info("{}. Создаём FullAds {}", methodName(), fullAds);
        return fullAds;
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @Override
    public AdsDto removeAds(Integer id) {
        log.info("{}. Удаляем объявление с id {}:", methodName(), id);
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        if (ads != null) {
            adsRepository.deleteById(id);
            log.warn("{}. Объявление с id {} удалено", methodName(), id);
        }
        return adsMapper.toDto(ads);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @Override
    public AdsCommentDto updateAdsComment(Integer adPk, Integer id, AdsCommentDto adsCommentDto) {
        AdsComment adsComment = adsCommentRepository.findAdsCommentByPkAndId(adPk, id)
                .orElseThrow(AdsCommentNotFoundException::new);
        log.debug("{}. Изменяем комментарий {}, принадлежащее пользователю с id {} и относящийся к объявлению с ключом {}",
                methodName(), adsComment, id, adPk);
        adsComment.setAuthor(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
        adsComment.setPk(adsRepository.findById(adPk).orElseThrow(AdsNotFoundException::new));
        adsComment.setText(adsComment.getText());
        adsComment.setCreatedAt(adsCommentDto.getCreatedAt());
        adsCommentRepository.save(adsComment);
        return adsCommentDto;
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @Override
    public AdsDto updateAds(Integer id, AdsDto adsDto) {
        log.debug("{}. Изменяем объявление {}, принадлежащее пользователю с id {}", methodName(), adsDto, id);
        Ads ads = adsMapper.toModel(adsDto);
        ads.setPk(id);
        adsRepository.save(ads);
        return adsDto;
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @Override
    public void patchAdsImage(Integer id, MultipartFile file) {
        log.debug("{}. Изменяем картинку в объявлении {}", methodName(), id);
        Ads adsToPatch = adsRepository.findById(id).get();
        String[] imagePath = adsToPatch.getImage().split("/");
        Integer imageId = Integer.parseInt(imagePath[imagePath.length - 1]);
        adsPhotoService.patchPhoto(imageId, file);
    }
}
