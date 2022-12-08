package pro.sky.graduate_work_group5_team1.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.graduate_work_group5_team1.controller.AuthController;
import pro.sky.graduate_work_group5_team1.model.LoginReq;
import pro.sky.graduate_work_group5_team1.model.RegReq;
import pro.sky.graduate_work_group5_team1.service.AuthService;

import static pro.sky.graduate_work_group5_team1.model.RegReq.RoleEnum.USER;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<LoginReq> login(LoginReq loginReq) {
        if (authService.login(loginReq.getUsername(), loginReq.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<RegReq> register(RegReq regReq) {
        RegReq.RoleEnum role = regReq.getRole() == null ? USER : regReq.getRole();
        if (authService.register(regReq, role)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

