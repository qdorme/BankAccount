package qdo.kata;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AccountTest {

    @Test
    void accountBalanceShouldBeIncreasedByAmountDeposed() throws NegativeAmountException {
        // Given
        Account account = new Account();
        BigDecimal amount = BigDecimal.valueOf(123.45);

        // When
        account.deposit(amount);

        // Then
        assertThat(account.balance()).isEqualByComparingTo(amount);
    }

    @Test
    void accountShouldThrowNegativeAmountExceptionWhenDepose(){
        // Given
        Account account = new Account();
        BigDecimal amount = BigDecimal.valueOf(-123.45);

        // When - Then
        assertThatThrownBy(()-> account.deposit(amount)).isInstanceOf(NegativeAmountException.class);
    }

    @Test
    void accountBalanceShouldBeDecreasedByAmountWithdraw() throws NegativeAmountException, NotEnoughMoneyException {
        // Given
        Account account = new Account(BigDecimal.valueOf(2000));
        BigDecimal amount = BigDecimal.valueOf(500);

        // When
        account.withdraw(amount);

        // Then
        assertThat(account.balance()).isEqualByComparingTo(BigDecimal.valueOf(1500));
    }

    @Test
    void accountShouldThrowNegativeAmountExceptionWhenWithdraw(){
        // Given
        Account account = new Account();
        BigDecimal amount = BigDecimal.valueOf(-500);

        // When - Then
        assertThatThrownBy(()-> account.withdraw(amount)).isInstanceOf(NegativeAmountException.class);
    }
    @Test
    void accountShouldThrowNotEnoughMoneyException(){
        // Given
        Account account = new Account(BigDecimal.ONE);
        BigDecimal amount = BigDecimal.TEN;

        // When - Then
        assertThatThrownBy(()-> account.withdraw(amount)).isInstanceOf(NotEnoughMoneyException.class);
    }

}
