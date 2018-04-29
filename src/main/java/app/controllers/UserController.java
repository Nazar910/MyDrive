package app.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import java.util.Collection;

import app.user.UserService;
import app.models.User;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Collection<User> getList() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<Resource> postUser(@RequestBody User user) {
        this.userService.createUser(user);
        return ResponseEntity.ok().build();
    }
}