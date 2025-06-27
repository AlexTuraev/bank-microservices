package org.tasks.frontuiapp.enums;

import lombok.Getter;

@Getter
public enum CurrencyType {
    rub("rub", 643),
    dollar("dollar", 840),
    euro("euro", 978),
    ;

    private final String title;
    private final Integer name;

    CurrencyType(String title, Integer name) {
        this.title = title;
        this.name = name;
    }

}
