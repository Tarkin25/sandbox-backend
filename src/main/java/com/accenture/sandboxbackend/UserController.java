package com.accenture.sandboxbackend;

import com.accenture.sandboxbackend.core.command.ChangeUsernameCommand;
import com.accenture.sandboxbackend.core.command.CreateUserCommand;
import com.accenture.sandboxbackend.core.query.FindAllQuery;
import com.accenture.sandboxbackend.query.UserHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    CommandGateway commandGateway;
    QueryGateway queryGateway;

    @MessageMapping("/queue/commands/create-user")
    public void createUser(CreateUserCommand command) {
        commandGateway.send(command);
    }

    @MessageMapping("/queue/commands/change-username")
    public void changeUsername(ChangeUsernameCommand command) {
        commandGateway.send(command);
    }

    @GetMapping("/users")
    public ResponseEntity<Collection<UserHandler.User>> findAll() {
        Collection<UserHandler.User> users = queryGateway.query(new FindAllQuery(), ResponseTypes.multipleInstancesOf(UserHandler.User.class)).join();

        return ResponseEntity.ok(users);
    }

}
