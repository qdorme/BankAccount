package qdo.kata;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(Operation operation,
                          LocalDate date,
                          BigDecimal amount){

}
