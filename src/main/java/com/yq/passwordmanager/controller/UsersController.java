package com.yq.passwordmanager.controller;

import com.yq.passwordmanager.model.Result;
import com.yq.passwordmanager.model.Users;
import com.yq.passwordmanager.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/{userId}")
    public Result<Users> getUserById(@PathVariable Long userId) {
        return usersService.getUserById(userId);
    }

    @PostMapping
    public Result<Boolean> addUser(@RequestBody Users users) {
        return usersService.addUser(users);
    }

    @GetMapping
    public Result<List<Users>> getUsers() {
        return usersService.getUsers();
    }

    @PutMapping
    public Result<Boolean> updateUser(@RequestBody Users users) {
        return usersService.updateUser(users);
    }

    @DeleteMapping("/{userId}")
    public Result<Boolean> deleteUser(@PathVariable Long userId) {
        return usersService.deleteUser(userId);
    }

    @GetMapping("/email/{userEmail}")
    public Result<String> getUserAvatar(@PathVariable String userEmail) {
        return usersService.getUserAvatar(userEmail);
    }
}
