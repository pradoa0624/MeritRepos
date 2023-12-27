package com.techelevator.view;

import java.math.BigDecimal;

public enum Coin {
    DOLLAR (new BigDecimal("1.00")),
    QUARTER (new BigDecimal("0.25")),
    DIME (new BigDecimal ("0.10")),
    NICKEL (new BigDecimal("0.05"));

    private final BigDecimal coinType;

    Coin(BigDecimal coinType) {
        this.coinType = coinType;
    }

    public BigDecimal getCoinType() {
        return coinType;
    }

}

