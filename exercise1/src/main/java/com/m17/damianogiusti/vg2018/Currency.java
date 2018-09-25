package com.m17.damianogiusti.vg2018;

public enum Currency {
  EURO("€");

  private String symbol;

  Currency(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }
}
