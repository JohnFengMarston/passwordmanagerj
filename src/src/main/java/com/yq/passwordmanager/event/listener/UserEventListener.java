package com.yq.passwordmanager.event.listener;

import com.yq.passwordmanager.event.UserAddEvent;
import com.yq.passwordmanager.event.UserDeleteEvent;
import com.yq.passwordmanager.service.KeyService;
import com.yq.passwordmanager.service.PasswordService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserEventListener {
    private final PasswordService passwordService;
    private final KeyService keyService;

    @EventListener
    public void handleUserDeleteEvent(UserDeleteEvent event) {
        passwordService.deletePasswordByUserId(event.getUserId());
        keyService.deleteKeyByUserId(event.getUserId());
    }

    @EventListener
    public void handleAddUserEvent(UserAddEvent event) {
        keyService.addKeyByUserId(event.getUserId());
    }
}
