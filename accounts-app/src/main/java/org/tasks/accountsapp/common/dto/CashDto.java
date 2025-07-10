package org.tasks.accountsapp.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.tasks.accountsapp.common.enums.CurrencyType;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CashDto {

    private String login;

    private String action;

    private BigDecimal value;

    private CurrencyType currency;

}
