package pro.sky.graduate_work_group5_team1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.graduate_work_group5_team1.api.UserApi;
import pro.sky.graduate_work_group5_team1.model.dto.NewPassword;
import pro.sky.graduate_work_group5_team1.model.dto.ResponseWrapperUser;
import pro.sky.graduate_work_group5_team1.model.dto.UserDto;
import pro.sky.graduate_work_group5_team1.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer id) {
        return null;
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperUser> getUsers() {
        return null;
    }

    @Override
    @PostMapping("/set_password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPassword) {
        return null;
    }

    @Override
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return null;
    }
}
