package pt.fabioazevedo.transaction.services;

import com.opencsv.CSVReader;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.BadRequestException;
import pt.fabioazevedo.transaction.dto.Transaction;
import pt.fabioazevedo.transaction.entity.TransactionEntity;
import pt.fabioazevedo.transaction.entity.TransactionRepository;
import pt.fabioazevedo.transaction.mappers.TransactionMapper;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ImportTransactionService {

    @Inject
    Validator validator;

    @Inject
    TransactionRepository transactionRepository;

    @Transactional
    public void importTransactions(@NotNull final InputStream stream) {
        Log.infof("Started importing transactions");
        try (CSVReader reader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            final List<String[]> rows = reader.readAll();
            if (rows.isEmpty()) {
                throw new BadRequestException("Tried to import empty CSV file");
            }

            final List<TransactionEntity> transactions = new ArrayList<>();
            for (final String[] row : rows) {
                final Transaction transaction = TransactionMapper.mapTransactionFromRow(row);
                validateTransaction(transaction);

                final TransactionEntity transactionEntity = TransactionMapper.mapTransaction(transaction);
                transactions.add(transactionEntity);
            }
            transactionRepository.persist(transactions);
        } catch (Exception exception) {
            Log.error("Failed to import transactions from CSV", exception);
            throw new BadRequestException(exception);
        }
        Log.infof("Finished importing transactions");
    }

    private void validateTransaction(final Transaction transaction) {
        final Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);
        if (!violations.isEmpty()) {
            throw new BadRequestException("Tried to import invalid transaction: " + violations);
        }
    }
}
