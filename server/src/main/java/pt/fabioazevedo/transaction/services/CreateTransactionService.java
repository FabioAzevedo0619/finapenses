package pt.fabioazevedo.transaction.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import pt.fabioazevedo.transaction.dto.Transaction;
import pt.fabioazevedo.transaction.entity.TransactionEntity;
import pt.fabioazevedo.transaction.entity.TransactionRepository;
import pt.fabioazevedo.transaction.mappers.TransactionMapper;

@ApplicationScoped
public class CreateTransactionService {

    @Inject
    TransactionRepository transactionRepository;

    @Transactional
    public void createTransaction(@Valid final Transaction transaction) {
        final TransactionEntity transactionEntity = TransactionMapper.mapTransaction(transaction);
        transactionRepository.persist(transactionEntity);
    }
}
