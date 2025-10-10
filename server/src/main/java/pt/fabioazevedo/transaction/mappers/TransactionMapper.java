package pt.fabioazevedo.transaction.mappers;

import jakarta.validation.Valid;
import pt.fabioazevedo.transaction.dto.Transaction;
import pt.fabioazevedo.transaction.entity.TransactionEntity;

public class TransactionMapper {

    public static TransactionEntity mapTransaction(final Transaction transaction) {
        return TransactionEntity.builder()
                .id(transaction.id())
                .name(transaction.name())
                .amount(transaction.amount())
                .transactionType(transaction.transactionType())
                .date(transaction.date())
                .build();
    }
}
