package pt.fabioazevedo.transaction.mappers;

import pt.fabioazevedo.transaction.dto.Transaction;
import pt.fabioazevedo.transaction.dto.TransactionType;
import pt.fabioazevedo.transaction.entity.TransactionEntity;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TransactionMapper {

    private static final NumberFormat AMOUNT_FORMATTER = NumberFormat.getInstance(Locale.GERMANY);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static TransactionEntity mapTransaction(final Transaction transaction) {
        return TransactionEntity.builder()
                .id(transaction.id())
                .name(transaction.name())
                .amount(transaction.amount())
                .transactionType(transaction.transactionType())
                .date(transaction.date())
                .build();
    }

    public static Transaction mapTransactionFromRow(final String[] row) throws ParseException {
        final Number parsedAmount = AMOUNT_FORMATTER.parse(row[3]); // Remove "," from big amount ex: 1,500.34
        final BigDecimal amount = new BigDecimal(parsedAmount.toString());
        final TransactionType transactionType = amount.compareTo(BigDecimal.ZERO) > 0 ? TransactionType.INCOME : TransactionType.EXPENSE;
        final LocalDate date = LocalDate.parse(row[0], DATE_FORMATTER);

        return Transaction.builder()
                .name(row[2])
                .amount(amount.abs()) // Store only positive amounts
                .transactionType(transactionType)
                .date(date)
                .build();
    }
}
