package qdo.kata;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;

public class Account {

    private BigDecimal balance;
    private List<Transaction> transactions = new ArrayList<>();

    public Account(){
        balance = ZERO;
    }

    public Account(BigDecimal startingBalance) {
        balance =  startingBalance;
    }

    public void deposit(BigDecimal deposit) throws NegativeAmountException {
        checkThatAmoutIsPositive(deposit);

        balance = balance.add(deposit);

        transactions.add(new Transaction(Operation.DEPOSIT, LocalDate.now(), deposit));
    }

    public BigDecimal balance() {
        return balance;
    }

    public void withdraw(BigDecimal withdraw) throws NegativeAmountException, NotEnoughMoneyException {
        checkThatAmoutIsPositive(withdraw);

        checkThatBalanceIsEnough(withdraw);

        balance = balance.subtract(withdraw);

        transactions.add(new Transaction(Operation.WITHDRAWAL, LocalDate.now(), withdraw));
    }

    private void checkThatBalanceIsEnough(BigDecimal withdraw) throws NotEnoughMoneyException {
        if(withdraw.compareTo(balance)> 0){
            throw new NotEnoughMoneyException();
        }
    }

    private static void checkThatAmoutIsPositive(BigDecimal deposit) throws NegativeAmountException {
        if(deposit.compareTo(ZERO) <= 0){
            throw new NegativeAmountException();
        }
    }

    public List<Transaction> history() {
        return transactions;
    }
}
