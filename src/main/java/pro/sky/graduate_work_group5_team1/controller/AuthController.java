package pro.sky.graduate_work_group5_team1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.graduate_work_group5_team1.api.AuthApi;
import pro.sky.graduate_work_group5_team1.model.dto.LoginReq;
import pro.sky.graduate_work_group5_team1.model.dto.RegReq;
import pro.sky.graduate_work_group5_team1.service.AuthService;

import static pro.sky.graduate_work_group5_team1.model.dto.RegReq.RoleEnum.USER;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<LoginReq> login(@RequestBody LoginReq loginReq) {
        if (authService.login(loginReq.getUsername(), loginReq.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<RegReq> register(@RequestBody RegReq regReq) {
        RegReq.RoleEnum role = regReq.getRole() == null ? USER : regReq.getRole();
        if (authService.register(regReq, role)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

