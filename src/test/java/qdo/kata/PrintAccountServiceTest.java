package qdo.kata;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.time.format.FormatStyle.MEDIUM;
import static org.assertj.core.api.Assertions.assertThat;

class PrintAccountServiceTest {

    @Test
    void accountHistoryShouldBePrinted() throws NegativeAmountException, NotEnoughMoneyException {
        // Given
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(byteArray);
        PrintAccountService service = new PrintAccountService(out);

        Account account = new Account(BigDecimal.valueOf(1500));
        account.deposit(BigDecimal.valueOf(200));
        account.withdraw(BigDecimal.valueOf(300));

        // When
        service.printAccountHistory(account);

        // Then
        String expectedPrintedText =
                String.format(
                """
                Starting balance            1500 \u20ac
                  Operation            Date           Amout \u20ac         Balance \u20ac
                    DEPOSIT    %S             200 \u20ac            1700 \u20ac
                 WITHDRAWAL    %S             300 \u20ac            1400 \u20ac
                """,
                        LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(MEDIUM)),
                        LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(MEDIUM)));


        assertThat(byteArray.toString(UTF_8)).isEqualTo(expectedPrintedText);
    }
}
