package com.weintern.banking;

import java.util.*;

/** Interactive console demonstration of account operations. */
public final class Main {
    private final Scanner in = new Scanner(System.in);
    private final Bank bank = new Bank();
    public static void main(String[] args) { new Main().run(); }
    private void run() {
        boolean running = true;
        while (running) {
            System.out.println("\nBANK\n1 Create savings  2 Create current  3 Deposit  4 Withdraw  5 Transfer  6 Accounts  7 Statement  0 Exit");
            try {
                switch (integer("Choice: ")) {
                    case 1: createSavings(); break;
                    case 2: createCurrent(); break;
                    case 3: find().deposit(decimal("Amount: ")); System.out.println("Deposit posted."); break;
                    case 4: find().withdraw(decimal("Amount: ")); System.out.println("Withdrawal posted."); break;
                    case 5: String from = text("From account: "); String to = text("To account: "); bank.transfer(from, to, decimal("Amount: ")); System.out.println("Transfer posted."); break;
                    case 6: for (BankAccount a : bank.all()) System.out.println(a); break;
                    case 7: System.out.println(find().statement()); break;
                    case 0: running = false; break;
                    default: System.out.println("Choose a listed option.");
                }
            } catch (IllegalArgumentException ex) { System.out.println("Operation failed: " + ex.getMessage()); }
        }
    }
    private void createSavings() { BankAccount a = bank.createSavings(text("Holder: "), decimal("Opening balance: "), decimal("Minimum balance: ")); System.out.println("Created " + a); }
    private void createCurrent() { BankAccount a = bank.createCurrent(text("Holder: "), decimal("Opening balance: "), decimal("Overdraft limit: ")); System.out.println("Created " + a); }
    private BankAccount find() { String number = text("Account number: "); BankAccount a = bank.find(number); if (a == null) throw new IllegalArgumentException("Account not found: " + number); return a; }
    private String text(String prompt) { System.out.print(prompt); return in.nextLine().trim(); }
    private int integer(String prompt) { try { return Integer.parseInt(text(prompt)); } catch (NumberFormatException ex) { throw new IllegalArgumentException("Enter a whole number."); } }
    private double decimal(String prompt) { try { return Double.parseDouble(text(prompt)); } catch (NumberFormatException ex) { throw new IllegalArgumentException("Enter a valid number."); } }
}
