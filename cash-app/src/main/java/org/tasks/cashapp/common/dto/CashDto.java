package org.tasks.cashapp.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.tasks.cashapp.common.enums.CurrencyType;

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
