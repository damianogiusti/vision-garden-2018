package com.m17.damianogiusti.vg2018;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class Money {

  @NotNull private BigDecimal amount;
  @NotNull private Currency currency;

  public Money(@NotNull BigDecimal amount, @NotNull Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  @NotNull public BigDecimal getAmount() {
    return amount;
  }

  @NotNull public Currency getCurrency() {
    return currency;
  }

  @Override public String toString() {
    return amount.toPlainString() + currency.getSymbol();
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Money)) return false;

    Money money = (Money) o;

    return
      amount.equals(money.amount)
        && currency.equals(money.currency);
  }

  @Override public int hashCode() {
    int result = amount.hashCode();
    result = 31 * result + currency.hashCode();
    return result;
  }
}
