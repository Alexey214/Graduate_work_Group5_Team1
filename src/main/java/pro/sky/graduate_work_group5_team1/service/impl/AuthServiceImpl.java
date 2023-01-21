package pro.sky.graduate_work_group5_team1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.sky.graduate_work_group5_team1.exception.UserNotFoundException;
import pro.sky.graduate_work_group5_team1.model.User;
import pro.sky.graduate_work_group5_team1.model.dto.RegReq;
import pro.sky.graduate_work_group5_team1.repository.UserRepository;
import pro.sky.graduate_work_group5_team1.service.AuthService;
import pro.sky.graduate_work_group5_team1.service.UserPhotoService;
import pro.sky.graduate_work_group5_team1.util.UtilClassGraduate;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UtilClassGraduate {

    private final UserDetailsService manager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    private final UserPhotoService userPhotoService;


    @Override
    public boolean login(String userName, String password) {
        log.info("{}. Вход в систему пользователя userName: {}", methodName(), userName);
        try {
            UserDetails userDetails = manager.loadUserByUsername(userName);
            String encryptedPassword = userDetails.getPassword();
            String databaseUserPassword = userRepository.findByEmail(userName).orElseThrow(UserNotFoundException::new).getPassword();
            return encryptedPassword.equals(databaseUserPassword);
        } catch (UsernameNotFoundException | NoSuchElementException e) {
            log.warn("{}. Неверное имя пользователя userName: {}", methodName(), userName);
            return false;
        }
    }

    @Override
    public boolean register(RegReq registerReq, RegReq.RoleEnum role) {
        log.debug("{}. Регистрация пользователя userName: {}", methodName(), registerReq.getUsername());
        Optional<User> user = userRepository.findByEmail(registerReq.getUsername());
        if (user.isPresent()) {
            log.warn("Пользователь с данным именем userName: {}, уже существует", registerReq.getUsername());
            throw new UserNotFoundException("Пользователь с данным именем userName: {}, уже существует" + registerReq.getUsername());
        }
        User userTmp = new User();
        String pass = encoder.encode(registerReq.getPassword());
        userTmp.setEmail(registerReq.getUsername());
        userTmp.setPassword(pass);
        userTmp.setRoleEnum(role);
        userTmp.setFirstName(registerReq.getFirstName());
        userTmp.setLastName(registerReq.getLastName());
        userTmp.setPhone(registerReq.getPhone());
        userTmp.setRegDate(LocalDateTime.now());
        userTmp.setImage(userPhotoService.createEmptyPhoto());
        userRepository.save(userTmp);
        return true;
    }

}
