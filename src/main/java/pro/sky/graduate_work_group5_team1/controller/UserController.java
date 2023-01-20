package pro.sky.graduate_work_group5_team1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pro.sky.graduate_work_group5_team1.api.UserApi;
import pro.sky.graduate_work_group5_team1.exeption.UnauthorizedException;
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
        try {
            NewPassword password = userService.setPassword(newPassword, login());
            return ResponseEntity.ok(password);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperUser> getUsers() {
        ResponseWrapperUser responseWrapperUser = userService.getUsers();
        if (responseWrapperUser.getCount() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(responseWrapperUser);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        UserDto user = userService.updateUser(userDto);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }

    //Этот запрос вообще приходит? - Я нигде не встречал. В свагере он работает корректно.
    @Override
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer id) {
        if (id < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        UserDto userDto = null;
        try {
            userDto = userService.getUser(id);
            if (userDto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(userDto);
    }
}
