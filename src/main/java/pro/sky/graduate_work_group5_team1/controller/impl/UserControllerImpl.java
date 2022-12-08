package pro.sky.graduate_work_group5_team1.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.graduate_work_group5_team1.controller.UserController;
import pro.sky.graduate_work_group5_team1.model.NewPassword;
import pro.sky.graduate_work_group5_team1.model.ResponseWrapperUser;
import pro.sky.graduate_work_group5_team1.model.User;
import pro.sky.graduate_work_group5_team1.service.UserService;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
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
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return null;
    }
}
