package com.weintern.banking;

/** Current account with an explicitly configured overdraft limit. */
public final class CurrentAccount extends BankAccount {
    private final double overdraftLimit;
    public CurrentAccount(String number, String holder, double openingBalance, double overdraftLimit) {
        super(number, holder, openingBalance);
        if (!Double.isFinite(overdraftLimit) || overdraftLimit < 0) throw new IllegalArgumentException("Overdraft limit must be non-negative.");
        this.overdraftLimit = overdraftLimit;
    }
    @Override protected boolean canWithdraw(double amount) { return getBalance() - amount >= -overdraftLimit; }
}
