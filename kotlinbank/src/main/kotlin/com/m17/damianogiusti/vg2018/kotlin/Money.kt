package com.m17.damianogiusti.vg2018.kotlin

import java.math.BigDecimal

data class Money(val amount: BigDecimal, val currency: Currency) {

    override fun toString(): String {
        return amount.toPlainString() + currency.symbol
    }
}
