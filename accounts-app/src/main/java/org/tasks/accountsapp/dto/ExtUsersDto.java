package org.tasks.accountsapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExtUsersDto {

    private String name;

    private LocalDate birthday;


}
