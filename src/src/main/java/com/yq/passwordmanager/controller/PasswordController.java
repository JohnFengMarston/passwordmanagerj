package com.yq.passwordmanager.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yq.passwordmanager.model.Password;
import com.yq.passwordmanager.model.Result;
import com.yq.passwordmanager.service.PasswordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passwords")
@AllArgsConstructor
public class PasswordController {
    private final PasswordService passwordService;

    @PostMapping
    public Result<Boolean> addPassword(@RequestBody Password password) {
        return passwordService.addPassword(password);
    }

    @DeleteMapping("/{userId}")
    public Result<Boolean> deletePasswordByUserId(@PathVariable Long userId) {
        return passwordService.deletePasswordByUserId(userId);
    }

    @PutMapping
    public Result<Boolean> updatePassword(@RequestBody Password password) {
        return passwordService.updatePassword(password);
    }

    @GetMapping("/{userId}")
    public Result<List<Password>> getPasswordsByUserId(@PathVariable Long userId) {
        return passwordService.getPasswordsByUserId(userId);
    }

    @GetMapping("/pages/{current}/{size}")
    public Result<IPage<Password>> getPagePasswords(@PathVariable Integer current, @PathVariable Integer size) {
        return passwordService.getPagePasswords(current, size);
    }
}
