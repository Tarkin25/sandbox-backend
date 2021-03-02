package com.accenture.sandboxbackend;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class DummyController {

    @MessageMapping("/queue/commands")
    @SendTo("/queue/events")
    public Object handleMessage(Object object) {


        return object;
    }

}
