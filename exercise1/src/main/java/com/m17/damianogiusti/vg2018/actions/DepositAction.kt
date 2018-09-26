package com.m17.damianogiusti.vg2018.actions

import com.m17.damianogiusti.vg2018.BankAccount
import com.m17.damianogiusti.vg2018.Money
import java.math.RoundingMode

class DepositAction(private val bankAccount: BankAccount) : UserAction {

    override fun execute(): Boolean {
        println("Quanto vuoi depositare (${BankAccount.CURRENCY.symbol})?")
        val amountString = readLine()?.trim() ?: ""

        val amount = try {
            amountString.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Il valore inserito ($amountString) non è un numero!", e)
        }

        val money = Money(amount, BankAccount.CURRENCY)
        if (deposit(money)) {
            println("Depositati $money!")
        } else {
            println("Impossibile depositare $money!")
        }

        return true
    }

    private fun deposit(money: Money): Boolean {
        if (money.amount.signum() <= 0) {
            return false
        }

        with(bankAccount) {
            total = (total.amount + money.amount)
                .setScale(2, RoundingMode.HALF_EVEN)
                .let { total.copy(amount = it) }
        }
        return true
    }
}
