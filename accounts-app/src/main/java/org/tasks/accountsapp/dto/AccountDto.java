package org.tasks.accountsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccountDto {
    private String login;
    private String password;
    private String name;
    private String birthdate;
}
