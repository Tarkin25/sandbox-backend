package com.accenture.sandboxbackend.core.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class ChangeUsernameCommand {

    @TargetAggregateIdentifier
    private String userId;

    private String newUsername;

}
