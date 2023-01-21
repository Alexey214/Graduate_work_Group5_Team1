package pro.sky.graduate_work_group5_team1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pro.sky.graduate_work_group5_team1.api.UserApi;
import pro.sky.graduate_work_group5_team1.model.dto.NewPassword;
import pro.sky.graduate_work_group5_team1.model.dto.ResponseWrapperUser;
import pro.sky.graduate_work_group5_team1.model.dto.UserDto;
import pro.sky.graduate_work_group5_team1.security.UtilSecurity;
import pro.sky.graduate_work_group5_team1.service.UserService;


@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
public class UserController implements UserApi, UtilSecurity {

    private final UserService userService;

    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @PostMapping("/setPassword")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.ok(userService.setPassword(newPassword, login()));
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperUser> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    //Этот запрос вообще приходит? - Я нигде не встречал. В сваггере он работает корректно.
    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
