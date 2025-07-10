package org.tasks.accountsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tasks.commons.enums.CurrencyType;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BankAccountDto {

    private long id;

    private long number;

    private long userId;

    private CurrencyType currency;

    private BigDecimal value;

}
