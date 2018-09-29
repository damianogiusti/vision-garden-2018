package com.m17.damianogiusti.vg2018.kotlin.actions

import com.m17.damianogiusti.vg2018.kotlin.BankAccount

class ActionFactory {

    operator fun invoke(command: String?, bankAccount: BankAccount): UserAction? {
        return when (command?.toLowerCase()) {
            DEPOSIT -> DepositAction(bankAccount)
            WITHDRAW -> WithdrawAction(bankAccount)
            QUIT -> QuitAction()
            else -> null
        }
    }

    companion object {
        const val DEPOSIT = "1"
        const val WITHDRAW = "2"
        const val QUIT = "q"
    }
}
