package com.yq.passwordmanager.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserAddEvent extends ApplicationEvent {
    private final Long userId;

    public UserAddEvent(Object source, Long userId) {
        super(source);
        this.userId = userId;
    }
}
