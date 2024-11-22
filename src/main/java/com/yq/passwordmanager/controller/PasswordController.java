package com.yq.passwordmanager.controller;

import com.yq.passwordmanager.model.Password;
import com.yq.passwordmanager.model.Result;
import com.yq.passwordmanager.service.PasswordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
