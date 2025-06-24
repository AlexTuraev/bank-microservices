package org.tasks.frontuiapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BankAccountDto {

    private long id;

    private long number;

    private long userId;

    private String currency;

    private BigDecimal value;

}
