package com.m17.damianogiusti.vg2018.kotlin.actions

import com.m17.damianogiusti.vg2018.kotlin.BankAccount
import com.m17.damianogiusti.vg2018.kotlin.Money
import java.math.RoundingMode

class WithdrawAction(private val bankAccount: BankAccount) : UserAction {

    override fun execute(): Boolean {
        println("Quanto vuoi prelevare (${BankAccount.CURRENCY.symbol})? ")
        val amountString = readLine()?.trim() ?: ""
        val amount = try {
            amountString.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Il valore inserito ($amountString) non è un numero!", e)
        }

        val money = Money(amount, BankAccount.CURRENCY)
        if (withdraw(money)) {
            println(String.format("Prelevati %s!", money))
        } else {
            println("L'importo da prelevare è invalido!")
        }
        return true
    }

    private fun withdraw(money: Money): Boolean {
        if (money.amount.signum() < 0) {
            println("Impossibile prelevare un importo negativo!")
            return false
        }

        return with(bankAccount) {
            if (money.amount > total.amount) {
                false
            } else {

                total = (total.amount - money.amount)
                    .setScale(2, RoundingMode.HALF_EVEN)
                    .let { total.copy(amount = it) }

                true
            }
        }
    }
}
