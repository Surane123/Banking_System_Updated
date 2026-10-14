package com.weintern.banking;

/** Savings account that preserves its configured minimum balance. */
public final class SavingsAccount extends BankAccount {
    private final double minimumBalance;
    public SavingsAccount(String number, String holder, double openingBalance, double minimumBalance) {
        super(number, holder, openingBalance);
        if (!Double.isFinite(minimumBalance) || minimumBalance < 0 || openingBalance < minimumBalance) throw new IllegalArgumentException("Opening balance must meet the non-negative minimum balance.");
        this.minimumBalance = minimumBalance;
    }
    @Override protected boolean canWithdraw(double amount) { return getBalance() - amount >= minimumBalance; }
}
