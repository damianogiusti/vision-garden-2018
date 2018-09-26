package com.m17.damianogiusti.vg2018

data class BankAccount(val owner: String, var total: Money) {

    companion object {
        val CURRENCY = Currency.EURO
    }
}
