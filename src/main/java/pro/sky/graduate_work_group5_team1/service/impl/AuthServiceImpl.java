package pro.sky.graduate_work_group5_team1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import pro.sky.graduate_work_group5_team1.model.dto.RegReq;
import pro.sky.graduate_work_group5_team1.service.AuthService;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;


    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);
        return encoder.matches(password, encryptedPasswordWithoutEncryptionType);
    }

    @Override
    public boolean register(RegReq registerReq, RegReq.RoleEnum role) {
        if (manager.userExists(registerReq.getUsername())) {
            return false;
        }
        manager.createUser(User.withDefaultPasswordEncoder().password(registerReq.getPassword()).username(registerReq.getUsername()).roles(role.name()).build());
        return true;
    }

}
