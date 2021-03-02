package com.accenture.sandboxbackend.core.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
public class CreateUserCommand {

    @TargetAggregateIdentifier
    String userId = UUID.randomUUID().toString();

    String username;

}
