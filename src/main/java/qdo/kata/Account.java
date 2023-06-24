package qdo.kata;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class Account {

    private BigDecimal balance;

    public Account(){
        balance = ZERO;
    }
    public Account(BigDecimal startingBalance) {
        balance =  startingBalance;
    }

    public void deposit(BigDecimal deposit) throws NegativeAmountException {
        verifyAmountIsNotNegative(deposit);

        balance = balance.add(deposit);
    }

    public BigDecimal balance() {
        return balance;
    }

    public void withdraw(BigDecimal withdraw) throws NegativeAmountException {
        verifyAmountIsNotNegative(withdraw);

        balance = balance.subtract(withdraw);
    }

    private static void verifyAmountIsNotNegative(BigDecimal deposit) throws NegativeAmountException {
        if(deposit.compareTo(ZERO) <= 0){
            throw new NegativeAmountException();
        }
    }
}
