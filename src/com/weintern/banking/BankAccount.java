package com.weintern.banking;

import java.util.*;

/** Shared account behavior; subclasses supply their withdrawal policy. */
public abstract class BankAccount {
    private final String number;
    private final String holder;
    private double balance;
    private final List<Transaction> history = new ArrayList<>();
    protected BankAccount(String number, String holder, double openingBalance) {
        if (holder == null || holder.trim().isEmpty()) throw new IllegalArgumentException("Account holder is required.");
        if (!Double.isFinite(openingBalance) || openingBalance < 0) throw new IllegalArgumentException("Opening balance must be non-negative.");
        this.number = number; this.holder = holder.trim(); this.balance = openingBalance;
        history.add(new Transaction("OPENING_BALANCE", openingBalance, balance));
    }
    public String getNumber() { return number; }
    public String getHolder() { return holder; }
    public double getBalance() { return balance; }
    public List<Transaction> getHistory() { return Collections.unmodifiableList(history); }
    public final void deposit(double amount) {
        validateAmount(amount); balance += amount; history.add(new Transaction("DEPOSIT", amount, balance));
    }
    public final void withdraw(double amount) {
        validateAmount(amount);
        if (!canWithdraw(amount)) throw new IllegalArgumentException("Withdrawal violates the account balance rule.");
        balance -= amount; history.add(new Transaction("WITHDRAWAL", amount, balance));
    }
    protected abstract boolean canWithdraw(double amount);
    protected static void validateAmount(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) throw new IllegalArgumentException("Amount must be a positive finite number.");
    }
    public final void recordTransferIn(double amount) { deposit(amount); }
    public final void recordTransferOut(double amount) {
        withdraw(amount);
        // Replace the last event type in history with a transfer label for statements.
        history.remove(history.size() - 1);
        history.add(new Transaction("TRANSFER_OUT", amount, balance));
    }
    public final String statement() {
        StringBuilder text = new StringBuilder("Statement for ").append(holder).append(" (account ").append(number).append(")\n");
        for (Transaction tx : history) text.append(tx).append('\n');
        return text.toString();
    }
    @Override public String toString() { return String.format("%-12s %-20s %-8s %10.2f", number, holder, getClass().getSimpleName(), balance); }
}
