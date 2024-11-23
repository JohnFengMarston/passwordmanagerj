package com.yq.passwordmanager.controller;

import com.yq.passwordmanager.model.Key;
import com.yq.passwordmanager.model.Result;
import com.yq.passwordmanager.service.KeyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("keys")
@AllArgsConstructor
public class KeyController {
    private final KeyService keyService;

    @PostMapping("/{userId}")
    public Result<Boolean> addKeyByUserId(@PathVariable Long userId) {
        return keyService.addKeyByUserId(userId);
    }

    @GetMapping("/{userId}")
    public Result<Key> getKeyByUserId(@PathVariable Long userId) {
        return keyService.getKeyByUserId(userId);
    }

    @DeleteMapping("/{userId}")
    public Result<Boolean> deleteKeyByUserId(@PathVariable Long userId) {
        return keyService.deleteKeyByUserId(userId);
    }
}
