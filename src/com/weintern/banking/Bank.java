package com.weintern.banking;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/** Account registry and validated transfer service. */
public final class Bank {
    private final Map<String, BankAccount> accounts = new LinkedHashMap<>();
    private final AtomicLong nextNumber = new AtomicLong(100001);
    public SavingsAccount createSavings(String holder, double opening, double minimum) {
        SavingsAccount account = new SavingsAccount(number(), holder, opening, minimum); accounts.put(account.getNumber(), account); return account;
    }
    public CurrentAccount createCurrent(String holder, double opening, double overdraft) {
        CurrentAccount account = new CurrentAccount(number(), holder, opening, overdraft); accounts.put(account.getNumber(), account); return account;
    }
    private String number() { return "AC" + nextNumber.getAndIncrement(); }
    public BankAccount find(String number) { return accounts.get(number); }
    public Collection<BankAccount> all() { return Collections.unmodifiableCollection(accounts.values()); }
    public void transfer(String fromNumber, String toNumber, double amount) {
        if (fromNumber.equals(toNumber)) throw new IllegalArgumentException("Source and destination must differ.");
        BankAccount from = require(fromNumber), to = require(toNumber);
        // Validate both sides before posting either leg, so a rejected transfer is atomic.
        BankAccount.validateAmount(amount);
        if (!from.canWithdraw(amount)) throw new IllegalArgumentException("Insufficient funds or account limit exceeded.");
        from.recordTransferOut(amount);
        to.recordTransferIn(amount);
    }
    private BankAccount require(String number) {
        BankAccount account = accounts.get(number);
        if (account == null) throw new IllegalArgumentException("Account not found: " + number);
        return account;
    }
}
