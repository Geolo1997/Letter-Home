package team.dorm301.letterhome.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @PutMapping("forget")
    public String forget(@RequestParam String username) {
        return userService.forget(username);
    }

    @PutMapping("password")
    public void reset(@RequestBody User user) {
        userService.reset(user.getUsername(), user.getPassword());
    }

    @PutMapping("avatar")
    public void uploadAvatar(@RequestParam MultipartFile avatar) throws Exception {
        userService.uploadAvatar(avatar);
    }

    @GetMapping("currentLoginUser")
    @JsonView(UserJsonView.getCurrentLoginUser.class)
    public User getCurrentLoginUser() {
        return userService.getCurrentLoginUser();
    }
}
