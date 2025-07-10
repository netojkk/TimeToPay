package com.neto.TimeToPay.enums;

import lombok.Getter;

@Getter
public enum SubscriptionTimeEnum {
    MONTHLY(1),
    QUARTELY(3),
    SEMIANNUAL(6),
    ANNUALLY(12);

    private final Integer months;

    SubscriptionTimeEnum(Integer months){
        this.months = months;
    }

}
