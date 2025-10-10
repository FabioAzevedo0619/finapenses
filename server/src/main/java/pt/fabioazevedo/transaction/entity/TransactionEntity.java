package pt.fabioazevedo.transaction.entity;

import jakarta.persistence.*;
import lombok.*;
import pt.fabioazevedo.transaction.dto.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transactions", schema = "finapenses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate date;
}
