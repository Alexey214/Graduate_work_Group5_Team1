package pro.sky.graduate_work_group5_team1.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.graduate_work_group5_team1.Repositories.UserRepository;
import pro.sky.graduate_work_group5_team1.mapper.AdsCommentMapper;
import pro.sky.graduate_work_group5_team1.mapper.AdsMapper;
import pro.sky.graduate_work_group5_team1.mapper.UserMapper;
import pro.sky.graduate_work_group5_team1.model.Ads;
import pro.sky.graduate_work_group5_team1.model.AdsComment;
import pro.sky.graduate_work_group5_team1.model.User;
import pro.sky.graduate_work_group5_team1.model.dto.AdsCommentDto;
import pro.sky.graduate_work_group5_team1.model.dto.AdsDto;
import pro.sky.graduate_work_group5_team1.model.dto.RegReq;
import pro.sky.graduate_work_group5_team1.model.dto.UserDto;
import pro.sky.graduate_work_group5_team1.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final AdsCommentMapper adsCommentMapper;
    private final AdsMapper adsMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, AdsCommentMapper adsCommentMapper, AdsMapper adsMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.adsCommentMapper = adsCommentMapper;
        this.adsMapper = adsMapper;
        this.userRepository = userRepository;
    }

    public void testMapper() {
        Ads ads = new Ads();
        User user = new User();
        user.setId(1);
        user.setPhone("123");
        user.setLastName("qwe");
        user.setEmail("zxc");
        user.setAds(List.of(ads));
        user.setAdsComment(new ArrayList<>());
        user.setPassword("007");
        user.setFirstName("[[[");
        user.setRoleEnum(RegReq.RoleEnum.USER);
        System.out.println(user);

        UserDto userDto = userMapper.toDto(user);
        System.out.println(userDto);
        User user1 = userMapper.toModel(userDto);
        System.out.println(user1);

        ads.setAuthor(user);
        ads.setTitle("qqq");
        ads.setPrice(123);
        ads.setDescription("aaa");
        ads.setPk(1);
        ads.setImage("zzz");
        System.out.println(ads);

        AdsDto adsDto = adsMapper.toDto(ads);
        System.out.println(adsDto);

        ads = adsMapper.toModel(adsDto);
        System.out.println(ads);

        AdsComment adsComment = new AdsComment();
        adsComment.setPk(ads);
        adsComment.setAuthor(user);
        adsComment.setId(1);
        adsComment.setText("qwertyuiop[]");
        adsComment.setCreatedAt(LocalDateTime.now());
        System.out.println("\n" + adsComment);

        AdsCommentDto commentDto = adsCommentMapper.toDto(adsComment);
        System.out.println("\n" + commentDto);

        AdsComment adsComment1 = adsCommentMapper.toModel(commentDto);
        System.out.println(adsComment1);
    }
}
