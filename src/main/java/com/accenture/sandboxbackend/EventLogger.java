package com.accenture.sandboxbackend;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventLogger {

    @EventHandler
    public void logEvent(Object event) {
        log.debug("Received Event {}", event);
    }

}
