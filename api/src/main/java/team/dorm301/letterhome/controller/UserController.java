package team.dorm301.letterhome.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.dorm301.letterhome.entity.User;
import team.dorm301.letterhome.serialize.UserJsonView;
import team.dorm301.letterhome.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void register(@RequestBody User user) {
        userService.register(user);
    }

    @PutMapping
    public void updateCurrentLoginUser(@RequestBody User user) {
        userService.update(user);
    }

    @GetMapping("currentLoginUser")
    @JsonView(UserJsonView.getCurrentLoginUser.class)
    public User getCurrentLoginUser() {
        return userService.getCurrentLoginUser();
    }
}
