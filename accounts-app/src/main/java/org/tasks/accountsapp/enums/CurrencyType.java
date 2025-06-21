package org.tasks.accountsapp.enums;

import lombok.Getter;

@Getter
public enum CurrencyType {
    rub("rub"),
    dollar("dollar"),
    euro("euro"),
    ;

    private final String value;

    CurrencyType(String value) {
        this.value = value;
    }

}
