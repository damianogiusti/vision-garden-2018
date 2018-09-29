package com.m17.damianogiusti.vg2018.actions;

import com.m17.damianogiusti.vg2018.BankAccount;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.util.Scanner;

public class ActionFactory {

  public static final String DEPOSIT = "1";
  public static final String WITHDRAW = "2";
  public static final String QUIT = "q";

  public UserAction get(@Nullable String command, @NotNull BankAccount bankAccount, PrintWriter writer, Scanner scanner) {
    if (command == null) return null;
    switch (command.toLowerCase()) {
      case ActionFactory.DEPOSIT:
        return new DepositAction(bankAccount, writer, scanner);
      case WITHDRAW:
        return new WithdrawAction(bankAccount, writer, scanner);
      case QUIT:
        return new QuitAction();
      default:
        return null;
    }
  }
}
