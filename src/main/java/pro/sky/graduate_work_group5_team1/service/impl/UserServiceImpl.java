package pro.sky.graduate_work_group5_team1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.sky.graduate_work_group5_team1.exeption.ForbiddenException;
import pro.sky.graduate_work_group5_team1.exeption.UnauthorizedException;
import pro.sky.graduate_work_group5_team1.exeption.UserNotFoundException;
import pro.sky.graduate_work_group5_team1.model.dto.*;
import pro.sky.graduate_work_group5_team1.repository.UserRepository;
import pro.sky.graduate_work_group5_team1.mapper.UserMapper;
import pro.sky.graduate_work_group5_team1.model.User;
import pro.sky.graduate_work_group5_team1.security.UtilSecurity;
import pro.sky.graduate_work_group5_team1.service.UserService;
import pro.sky.graduate_work_group5_team1.util.UtilClassGraduate;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService, UtilSecurity, UtilClassGraduate {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUser(Integer id) {
        log.info("{}. Получаем пользователя с id {}:", methodName(), id);
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDto getUser(String email) {
        log.info("{}. Получаем пользователя с email {}:", methodName(), email);
        return userRepository.findByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(UserNotFoundException::new);
    }


    @Override
    public ResponseWrapperUser getUsers() {
        log.info("{}. Получаем всех пользователей", methodName());
        List<UserDto> userDtos = userRepository.findAll().stream()
                .map(userMapper::toDto)
                .sorted(Comparator.comparing(UserDto::getId))
                .toList();
        ResponseWrapperUser responseWrapperUser = new ResponseWrapperUser();
        if (!userDtos.isEmpty()) {
            log.info("{}. В списке {} пользователей ", methodName(), userDtos.size());
            responseWrapperUser.setResults(userDtos);
            responseWrapperUser.setCount(userDtos.size());
        }
        if (responseWrapperUser.getCount() == 0) {
            throw new UserNotFoundException();
        }
        return responseWrapperUser;
    }

    @Override
    public NewPassword setPassword(NewPassword newPassword, String email) {
        log.info("{}. Сохраняем пароль пользователя с email {}:", methodName(), email);
        User userTmp = userRepository.findByEmail(email)
                .orElseThrow(ForbiddenException::new);
        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), userTmp.getPassword())) {
            log.debug("{}. Неверный пароль пользователя", methodName());
            throw new UnauthorizedException("Неверный пароль пользователя");
        }
        userTmp.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(userTmp);
        return newPassword;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        log.info("{}. Изменяем пользователя " + userDto, methodName());
        String login = login();
        User userDtoTmp = userRepository.findByEmail(login)
                .orElseThrow(UserNotFoundException::new);
        userDtoTmp.setPhone(userDto.getPhone());
        userDtoTmp.setFirstName(userDto.getFirstName());
        userDtoTmp.setLastName(userDto.getLastName());
        return userMapper.toDto(userRepository.save(userDtoTmp));
    }

}
