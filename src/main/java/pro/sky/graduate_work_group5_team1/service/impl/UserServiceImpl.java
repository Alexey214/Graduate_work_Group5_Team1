package pro.sky.graduate_work_group5_team1.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.graduate_work_group5_team1.model.dto.*;
import pro.sky.graduate_work_group5_team1.repository.UserRepository;
import pro.sky.graduate_work_group5_team1.mapper.AdsCommentMapper;
import pro.sky.graduate_work_group5_team1.mapper.AdsMapper;
import pro.sky.graduate_work_group5_team1.mapper.UserMapper;
import pro.sky.graduate_work_group5_team1.model.Ads;
import pro.sky.graduate_work_group5_team1.model.AdsComment;
import pro.sky.graduate_work_group5_team1.model.User;
import pro.sky.graduate_work_group5_team1.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }


    @Override
    public UserDto getUser(Integer id) {
        return null;
    }

    @Override
    public ResponseWrapperUser getUsers() {
        return null;
    }

    @Override
    public NewPassword setPassword(NewPassword newPassword) {
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return null;
    }
}
