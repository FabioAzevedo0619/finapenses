package pt.fabioazevedo.transaction.controllers;

import com.google.common.net.HttpHeaders;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.fabioazevedo.transaction.dto.Transaction;
import pt.fabioazevedo.transaction.dto.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(TransactionController.class)
public class TransactionControllerTests {

    @Test
    @DisplayName("Validate creating a expense transaction where name is not provided")
    void testCreateExpenseWithoutName() {
        final var transaction = Transaction.builder()
                .amount(BigDecimal.valueOf(150.22))
                .transactionType(TransactionType.EXPENSE)
                .date(LocalDate.now())
                .build();

        given()
                .body(transaction)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .when().post()
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Validate creating a expense transaction where valid name is not provided")
    void testCreateExpenseWithoutValidName() {
        final var transaction = Transaction.builder()
                .name("  ")
                .amount(BigDecimal.valueOf(150.22))
                .transactionType(TransactionType.EXPENSE)
                .date(LocalDate.now())
                .build();

        given()
                .body(transaction)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .when().post()
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Validate creating a expense transaction where amount is not provided")
    void testCreateExpenseWithoutAmount() {
        final var transaction = Transaction.builder()
                .name("Grocery Shopping")
                .transactionType(TransactionType.EXPENSE)
                .date(LocalDate.now())
                .build();

        given()
                .body(transaction)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .when().post()
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Validate creating a expense transaction where type is not provided")
    void testCreateExpenseWithoutType() {
        final var transaction = Transaction.builder()
                .name("Grocery Shopping")
                .amount(BigDecimal.valueOf(150.22))
                .date(LocalDate.now())
                .build();

        given()
                .body(transaction)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .when().post()
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Validate creating a expense transaction where date is not provided")
    void testCreateExpenseWithoutDate() {
        final var transaction = Transaction.builder()
                .name("Grocery Shopping")
                .amount(BigDecimal.valueOf(150.22))
                .transactionType(TransactionType.EXPENSE)
                .build();

        given()
                .body(transaction)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .when().post()
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Validate creating a expense transaction where amount is negative")
    void testCreateExpenseWithNegativeValue() {
        final var transaction = Transaction.builder()
                .name("Grocery Shopping")
                .amount(BigDecimal.valueOf(-150.22))
                .transactionType(TransactionType.EXPENSE)
                .date(LocalDate.now())
                .build();

        given()
                .body(transaction)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .when().post()
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Validate creating a valid expense transaction where all fields are correctly provided")
    void testCreateValidExpense() {
        final var transaction = Transaction.builder()
                .name("Grocery Shopping")
                .amount(BigDecimal.valueOf(150.22))
                .transactionType(TransactionType.EXPENSE)
                .date(LocalDate.now())
                .build();

        given()
                .body(transaction)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .when().post()
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }
}
