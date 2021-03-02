package com.accenture.sandboxbackend.core.event;

import lombok.Value;

@Value
public class UsernameChangedEvent {

    String userId;
    String newUsername;

}
