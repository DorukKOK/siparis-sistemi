package com.example.demo.Controller;

import com.example.demo.Model.UserEntity;
import com.example.demo.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @GetMapping()
    public List<UserEntity> getAll(){
        return userService.getAllUsers();
    }
    @PostMapping()
    public UserEntity createUser(@RequestBody UserEntity user){
        return userService.createUser(user);
    }
    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user){
        return userService.updateUser(id, user);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

    }
}
