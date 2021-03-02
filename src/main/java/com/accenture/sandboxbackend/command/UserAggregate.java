package com.accenture.sandboxbackend.command;

import com.accenture.sandboxbackend.core.command.ChangeUsernameCommand;
import com.accenture.sandboxbackend.core.command.CreateUserCommand;
import com.accenture.sandboxbackend.core.event.UserCreatedEvent;
import com.accenture.sandboxbackend.core.event.UsernameChangedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
@Getter
public class UserAggregate {

    @AggregateIdentifier
    private String userId;

    private String username;

    @CommandHandler
    public UserAggregate(CreateUserCommand command) {
        String id = UUID.randomUUID().toString();

        AggregateLifecycle.apply(new UserCreatedEvent(id, command.getUsername()));
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        this.userId = event.getUserId();
        this.username = event.getUsername();
    }

    @CommandHandler
    public void handle(ChangeUsernameCommand command) {
        AggregateLifecycle.apply(new UsernameChangedEvent(userId, command.getNewUsername()));
    }

    @EventSourcingHandler
    public void on(UsernameChangedEvent event) {
        this.username = event.getNewUsername();
    }

}
