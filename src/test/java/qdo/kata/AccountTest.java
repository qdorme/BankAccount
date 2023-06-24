package qdo.kata;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AccountTest {

    @Test
    void accountBalanceShouldBeIncreasedByAmountDeposed(){
        // Given
        Account account = new Account();
        BigDecimal amount = BigDecimal.valueOf(123.45);

        // When
        account.deposit(amount);

        // Then
        assertThat(account.balance()).isEqualByComparingTo(amount);
    }

    @Test
    void accountShouldThrowNegativeAmountException(){
        // Given
        Account account = new Account();
        BigDecimal amount = BigDecimal.valueOf(-123.45);

        // When - Then
        assertThatThrownBy(()-> account.deposit(amount)).isInstanceOf(NegativeAmountException.class);
    }



}
