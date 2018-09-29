package com.m17.damianogiusti.vg2018;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class BankAccount {

  public static final Currency CURRENCY = Currency.EURO;

  @NotNull private final String owner;
  @NotNull private Money total;

  public BankAccount(@NotNull String owner, @NotNull BigDecimal initialAmount) {
    this.owner = owner;
    this.total = new Money(initialAmount, CURRENCY);
  }

  @NotNull public String getOwner() {
    return owner;
  }

  @NotNull public Money getTotal() {
    return total;
  }

  public void setTotal(@NotNull Money total) {
    this.total = total;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BankAccount)) return false;

    BankAccount that = (BankAccount) o;

    return
      owner.equals(that.owner)
        && total.equals(that.total);
  }

  @Override public int hashCode() {
    int result = owner.hashCode();
    result = 31 * result + total.hashCode();
    return result;
  }

  @Override public String toString() {
    return "BankAccount(" +
      "owner=" + owner +
      ",total=" + total +
      ')';
  }
}
