package pro.sky.graduate_work_group5_team1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import pro.sky.graduate_work_group5_team1.exeption.UserNotFoundException;
import pro.sky.graduate_work_group5_team1.model.dto.*;
import pro.sky.graduate_work_group5_team1.repository.UserRepository;
import pro.sky.graduate_work_group5_team1.mapper.UserMapper;
import pro.sky.graduate_work_group5_team1.model.User;
import pro.sky.graduate_work_group5_team1.service.UserService;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDto getUser(Integer id) {
        log.info("Получаем пользователя с id {}:", id);
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDto getUser(String email) {
        log.info("Получаем пользователя с email {}:", email);
        return userRepository.findByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public ResponseWrapperUser getUsers() {
        log.info("Получаем всех пользователей");
        List<UserDto> userDtos = userRepository.findAll().stream()
                .map(userMapper::toDto)
                .sorted(Comparator.comparing(UserDto::getId))
                .toList();
        ResponseWrapperUser responseWrapperUser = new ResponseWrapperUser();
        responseWrapperUser.setResults(userDtos);
        responseWrapperUser.setCount(userDtos.size());
        return responseWrapperUser;
    }

    @Override
    public NewPassword setPassword(NewPassword newPassword, String email) {
        log.info("Сохраняем пароль пользователя с email {}:", email);
        User userTmp = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), userTmp.getPassword())) {
            log.debug("Неверный пароль пользователя");
            throw new NotFoundException(newPassword.getCurrentPassword());
        }
        userTmp.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(userTmp);
        return newPassword;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        log.info("Изменяем пользователя " + userDto);
        User userDtoTmp = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(UserNotFoundException::new);
        userDtoTmp.setPhone(userDtoTmp.getPhone());
        userDtoTmp.setFirstName(userDtoTmp.getFirstName());
        userDtoTmp.setLastName(userDtoTmp.getLastName());
        return userMapper.toDto(userRepository.save(userDtoTmp));
    }
}
