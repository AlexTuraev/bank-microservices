package org.tasks.frontuiapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto {
    private String login;
    private String password;
    private String name;
    private LocalDate birthdate;
    private List<BankAccountDto> bankAccounts;

    public UserDto(String login, String password, String name, LocalDate birthdate) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.birthdate = birthdate;
    }
}
