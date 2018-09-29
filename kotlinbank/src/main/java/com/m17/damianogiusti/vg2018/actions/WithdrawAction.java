package com.m17.damianogiusti.vg2018.actions;

import com.m17.damianogiusti.vg2018.BankAccount;
import com.m17.damianogiusti.vg2018.Money;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class WithdrawAction implements UserAction {

  private final BankAccount bankAccount;
  private final PrintWriter writer;
  private final Scanner scanner;

  WithdrawAction(BankAccount bankAccount, PrintWriter writer, Scanner scanner) {
    this.bankAccount = bankAccount;
    this.writer = writer;
    this.scanner = scanner;
  }

  @Override public boolean execute() throws Exception {
    writer.println("Quanto vuoi prelevare (" + BankAccount.CURRENCY.getSymbol() + ")? ");
    final String amountString = scanner.nextLine().trim();
    final BigDecimal amount;
    try {
      amount = new BigDecimal(amountString)
        .setScale(2, RoundingMode.HALF_EVEN);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Il valore inserito (" + amountString + ") non è un numero!", e);
    }
    final Money money = new Money(amount, BankAccount.CURRENCY);
    if (withdraw(money)) {
      writer.println(String.format("Prelevati %s!", money));
    } else {
      writer.println("L'importo da prelevare è invalido!");
    }
    return true;
  }

  private boolean withdraw(Money money) {
    if (money == null) {
      throw new IllegalArgumentException("Impossibile prelevare! :(");
    }
    if (money.getAmount().signum() < 0) {
      return false;
    }

    final Money total = bankAccount.getTotal();
    if (money.getAmount().compareTo(total.getAmount()) > 0) {
      return false;
    }

    final BigDecimal amount = money.getAmount()
      .setScale(2, RoundingMode.HALF_EVEN);
    final BigDecimal newTotal = total.getAmount().subtract(amount);
    bankAccount.setTotal(new Money(newTotal, total.getCurrency()));
    return true;
  }
}
