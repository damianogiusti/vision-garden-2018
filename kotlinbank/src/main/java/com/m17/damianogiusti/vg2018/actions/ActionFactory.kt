package com.m17.damianogiusti.vg2018.actions

import com.m17.damianogiusti.vg2018.BankAccount

class ActionFactory {

    operator fun invoke(command: String?, bankAccount: BankAccount): UserAction? {
        return when (command?.toLowerCase()) {
            ActionFactory.DEPOSIT -> DepositAction(bankAccount)
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
