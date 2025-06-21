package org.tasks.frontuiapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MainDto {

    private String name;

    private LocalDate birthday;

    private List<UserDto> users;
}
