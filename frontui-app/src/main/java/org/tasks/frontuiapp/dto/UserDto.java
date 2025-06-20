package org.tasks.frontuiapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto {
    private String login;
    private String password;
    private String name;
    private LocalDate birthdate;
}
