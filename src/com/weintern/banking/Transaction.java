package com.weintern.banking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Immutable record of a posted account transaction. */
public final class Transaction {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final LocalDateTime time = LocalDateTime.now();
    private final String type;
    private final double amount;
    private final double balanceAfter;
    public Transaction(String type, double amount, double balanceAfter) { this.type = type; this.amount = amount; this.balanceAfter = balanceAfter; }
    @Override public String toString() { return String.format("%s | %-16s | %10.2f | balance %10.2f", time.format(FORMAT), type, amount, balanceAfter); }
}
