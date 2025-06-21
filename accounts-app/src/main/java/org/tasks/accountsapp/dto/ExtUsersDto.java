package org.tasks.accountsapp.dto;

import lombok.Getter;
import lombok.Setter;
import org.tasks.accountsapp.model.UserEntity;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ExtUsersDto {

    private String name;

    private LocalDate birthday;

    private List<UserDto> users;

}
