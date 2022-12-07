package pro.sky.graduate_work_group5_team1.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.graduate_work_group5_team1.controller.AuthController;
import pro.sky.graduate_work_group5_team1.model.LoginReq;
import pro.sky.graduate_work_group5_team1.model.RegReq;
import pro.sky.graduate_work_group5_team1.service.AuthService;

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
        return null;
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<RegReq> register(RegReq regReq) {
        return null;
    }
}
