# Banking System

A Java 11+ console simulation of savings and current accounts. It demonstrates abstract classes, inheritance, polymorphism, transaction history, validation, and transfers.

## Features

- Create savings accounts with a minimum-balance rule
- Create current accounts with a configurable overdraft limit
- Deposit, withdraw, transfer, list accounts, and print statements
- Record dated transactions and balances after each transaction
- Reject invalid amounts, missing accounts, and withdrawals beyond account rules

## Requirements

- JDK 11 or newer
- Eclipse, IntelliJ IDEA, or a terminal with `javac` and `java` on `PATH`

## Run from a terminal

From this project directory, in PowerShell:

```powershell
javac -d out (Get-ChildItem src -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out com.weintern.banking.Main
```

In Eclipse, import this directory using **File → Import → General → Existing Projects into Workspace** and run `com.weintern.banking.Main`.

Account and transaction information is kept in memory and resets when the application exits; this project is an educational simulation, not a production banking system.

## Project structure

```text
src/com/weintern/banking/
  Bank.java             Account registry and transfer service
  BankAccount.java      Shared rules and transaction history
  SavingsAccount.java   Minimum-balance policy
  CurrentAccount.java  Overdraft policy
  Transaction.java      Immutable transaction record
  Main.java             Console menu
class-diagram.puml      UML class diagram source
```
