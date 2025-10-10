package pt.fabioazevedo.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record Transaction(
        UUID id,
        @NotNull @NotBlank String name,
        @NotNull @PositiveOrZero BigDecimal amount,
        @NotNull TransactionType transactionType,
        @NotNull LocalDate date
) {}
