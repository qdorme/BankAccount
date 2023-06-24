package qdo.kata;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.time.format.FormatStyle.MEDIUM;
import static qdo.kata.Operation.DEPOSIT;

public class PrintAccountService {

    public static final String PATTERN = " %1$10s %2$15s %3$15s € %4$15s €\n";
    private final PrintStream out;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( "MMM d uuuu", Locale.ENGLISH);

    public PrintAccountService(PrintStream out) {
        this.out = out;
    }


    public void printAccountHistory(Account account) {
        BigDecimal balance = account.balance()
                .subtract( account.history().stream()
                        .map(t ->
                                (t.operation() == DEPOSIT) ? t.amount()
                                        : t.amount().multiply(BigDecimal.valueOf(-1))
                        )
                        .reduce(BigDecimal.ZERO, BigDecimal::add));

        out.printf("Starting balance %1$15s €\n", NumberFormat.getInstance(Locale.ENGLISH).format(balance));

        out.printf(PATTERN,"Operation", "Date", "Amout", "Balance");

        for (Transaction t : account.history()) {
            balance = (t.operation() == DEPOSIT) ? balance.add(t.amount())
                    : balance.subtract( t.amount());
            out.printf(PATTERN, t.operation() ,
                    t.date().format(dateTimeFormatter),
                    NumberFormat.getInstance(Locale.ENGLISH).format(t.amount()) ,
                    NumberFormat.getInstance(Locale.ENGLISH).format(balance));
        }
    }
}
