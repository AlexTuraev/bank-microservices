package org.tasks.commonsapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.tasks.commonsapp.enums.CurrencyType;

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
