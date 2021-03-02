package com.accenture.sandboxbackend.query;

import com.accenture.sandboxbackend.core.event.UserCreatedEvent;
import com.accenture.sandboxbackend.core.event.UsernameChangedEvent;
import com.accenture.sandboxbackend.core.query.FindAllQuery;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserHandler {

    SimpMessagingTemplate messagingTemplate;

    Map<String, User> users = new HashMap<>();

    @EventHandler
    public void on(UserCreatedEvent event) {

        users.put(event.getUserId(), new User(event.getUserId(), event.getUsername()));

        Map<String, Object> message = new LinkedHashMap<>();
        message.put("type", event.getClass().getSimpleName());
        message.put("payload", event);

        messagingTemplate.convertAndSend("/queue/events", message);
    }

    @EventHandler
    public void on(UsernameChangedEvent event) {
        users.get(event.getUserId()).setUsername(event.getNewUsername());
    }

    @QueryHandler
    public List<User> handle(FindAllQuery query) {
        return new ArrayList<>(users.values());
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @AllArgsConstructor
    public static class User {
        String userId;
        String username;
    }

}
