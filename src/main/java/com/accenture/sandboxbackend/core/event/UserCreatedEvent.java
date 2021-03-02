package com.accenture.sandboxbackend.core.event;

import lombok.Value;

@Value
public class UserCreatedEvent {

    String userId;
    String username;

}
