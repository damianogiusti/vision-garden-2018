package com.m17.damianogiusti.vg2018;

import com.m17.damianogiusti.vg2018.actions.ActionFactory;
import com.m17.damianogiusti.vg2018.actions.UserAction;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class KotlinBank {

  private final ActionFactory actionFactory;

  private boolean shouldTerminate = false;
  private BankAccount bankAccount;

  public KotlinBank(ActionFactory actionFactory) {
    this.actionFactory = actionFactory;
  }

  public void newRun() {
    final PrintWriter writer = new PrintWriter(System.out, true);
    final Scanner scanner = new Scanner(System.in);

    if (bankAccount == null) {
      writer.println("Benvenuto in JavaBank!");
      writer.println("Stiamo aprendo il tuo conto, ci servirebbero delle info...");
      writer.println("Come ti chiami?");
      final String name = scanner.nextLine().trim();
      writer.println(String.format("Piacere %s. Quanti soldi vuoi depositare in conto? (%s)", name, BankAccount.CURRENCY.getSymbol()));
      final String initialAmountString = scanner.nextLine().trim();
      final BigDecimal initialAmount = new BigDecimal(initialAmountString).setScale(2, RoundingMode.HALF_EVEN);
      bankAccount = new BankAccount(name, initialAmount);

      writer.println("Yeah, ora sei un nuovo cliente!");
    } else {
      writer.println(String.format("Bentornato in JavaBank, %s!", bankAccount.getOwner()));
      writer.println(String.format("Il tuo conto ammonta a %s", bankAccount.getTotal()));
      writer.println("Che cosa vuoi fare?");
      writer.println(ActionFactory.DEPOSIT + ") Deposita");
      writer.println(ActionFactory.WITHDRAW + ") Preleva");
      writer.println(ActionFactory.QUIT + ") Esci");

      final String action = scanner.nextLine();
      final UserAction userAction = actionFactory.get(action, bankAccount, writer, scanner);
      if (userAction == null) {
        // Invalid action!
        writer.println("Hai inserito un'azione invalida! :(");
      } else {
        try {
          shouldTerminate = !userAction.execute();
        } catch (Exception e) {
          writer.println("Si è verificato un errore! Vuoi vederne i dettagli (s/n)?");
          final String result = scanner.nextLine();
          if ("s".equalsIgnoreCase(result)) {
            e.printStackTrace();
          }
          shouldTerminate = true;
        }
      }

      if (BigDecimal.ZERO.compareTo(bankAccount.getTotal().getAmount()) == 0) {
        writer.println("Sei rimasto a secco!");
        shouldTerminate = true;
      }
    }

    writer.println();
  }

  public boolean shouldTerminate() {
    return shouldTerminate;
  }

  ///////////////////////////////////////////////////////////////////////////
  // Main
  ///////////////////////////////////////////////////////////////////////////

  public static void main(String[] args) {
    final KotlinBank program = new KotlinBank(new ActionFactory());

    do {
      program.newRun();
    } while (!program.shouldTerminate());
  }
}
