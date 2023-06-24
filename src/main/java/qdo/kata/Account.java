package qdo.kata;

import java.math.BigDecimal;

public class Account {

    private BigDecimal amount = BigDecimal.ZERO;

    public void deposit(BigDecimal deposit) {
        amount = amount.add(deposit);
    }

    public BigDecimal balance() {
        return amount;
    }
}
