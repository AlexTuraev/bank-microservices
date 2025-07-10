package org.tasks.accountsapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePswDto {
    private String login;
    private String passwordHash;

}
