package com.m17.damianogiusti.vg2018

import com.m17.damianogiusti.vg2018.actions.ActionFactory
import java.math.BigDecimal
import java.math.RoundingMode

class Exercise1(private val actionFactory: ActionFactory) {

    private var shouldTerminate = false
    private var bankAccount: BankAccount? = null

    fun newRun() {
        val account = bankAccount

        if (account == null) {
            println("Benvenuto in JavaBank!")
            println("Stiamo aprendo il tuo conto, ci servirebbero delle info...")
            println("Come ti chiami?")
            val name = readLine()?.trim() ?: ""
            println("Piacere $name. Quanti soldi vuoi depositare in conto? (${BankAccount.CURRENCY.symbol})")
            val initialAmountString = readLine()?.trim() ?: ""
            bankAccount = initialAmountString.toBigDecimal()
                .setScale(2, RoundingMode.HALF_EVEN)
                .let { BankAccount(name, Money(it, BankAccount.CURRENCY)) }
            println("Yeah, ora sei un nuovo cliente!")
        } else {
            println("Bentornato in JavaBank, ${account.owner}!")
            println("Il tuo conto ammonta a ${account.total}")
            println("Che cosa vuoi fare?")
            println("${ActionFactory.DEPOSIT}) Deposita")
            println("${ActionFactory.WITHDRAW}) Preleva")
            println("${ActionFactory.QUIT}) Esci")

            val action = readLine()
            val userAction = actionFactory(action, account)
            if (userAction == null) {
                // Invalid action!
                println("Hai inserito un'azione invalida! :(")
            } else {
                shouldTerminate = try {
                    !userAction.execute()
                } catch (e: Exception) {
                    println("Si è verificato un errore! Vuoi vederne i dettagli (s/n)?")
                    val result = readLine() ?: ""
                    if ("s".equals(result, ignoreCase = true)) {
                        e.printStackTrace()
                    }
                    true
                }
            }

            if (account.total.amount <= BigDecimal.ZERO) {
                println("Sei rimasto a secco!")
                shouldTerminate = true
            }
        }
        println()
    }

    fun shouldTerminate(): Boolean {
        return shouldTerminate
    }
}

fun main(args: Array<String>) {
    with(Exercise1(ActionFactory())) {
        do newRun() while (!shouldTerminate())
    }
}
