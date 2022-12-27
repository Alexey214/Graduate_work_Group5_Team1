package pro.sky.graduate_work_group5_team1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.sky.graduate_work_group5_team1.exeption.UserNotFoundException;
import pro.sky.graduate_work_group5_team1.model.User;
import pro.sky.graduate_work_group5_team1.model.dto.RegReq;
import pro.sky.graduate_work_group5_team1.repository.UserRepository;
import pro.sky.graduate_work_group5_team1.service.AuthService;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService manager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;


    @Override
    public boolean login(String userName, String password) {
        log.info("Вход в систему пользователя userName: {}", userName);
        try {
            UserDetails userDetails = manager.loadUserByUsername(userName);
            String encryptedPassword = userDetails.getPassword();
            String databaseUserPassword = userRepository.findByEmail(userName).get().getPassword();
            return encryptedPassword.equals(databaseUserPassword);
        } catch (UsernameNotFoundException e) {
            log.warn("Неверное имя пользователя userName: {}", userName);
            return false;
        }
    }

    @Override
    public boolean register(RegReq registerReq, RegReq.RoleEnum role) {
        log.debug("Регистрация пользователя userName: {}", registerReq.getUsername());
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
        userRepository.save(userTmp);
        return true;
    }

}
