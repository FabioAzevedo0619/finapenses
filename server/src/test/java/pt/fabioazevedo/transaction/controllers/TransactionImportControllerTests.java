package pt.fabioazevedo.transaction.controllers;

import com.google.common.net.HttpHeaders;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(TransactionImportController.class)
public class TransactionImportControllerTests {

    @Test
    @DisplayName("Validate importing transactions from a null CSV file")
    void testImportTransactionsFromNullCsv() {
        given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA)
                .when().post()
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Validate importing transactions from a empty CSV file")
    void testImportTransactionsFromEmptyCsv() {
        final InputStream is = getClass().getClassLoader().getResourceAsStream("files/transaction-import/empty-import.csv");

        given()
                .multiPart("file", "empty-import.csv", is, "text/csv")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA)
                .when().post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Validate importing transactions from a CSV file with invalid data")
    void testImportTransactionsFromInvalidCsv() {
        final InputStream is = getClass().getClassLoader().getResourceAsStream("files/transaction-import/invalid-value-import.csv");

        given()
                .multiPart("file", "empty-import.csv", is, "text/csv")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA)
                .when().post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Validate importing transactions from a valid CSV file")
    void testImportTransactionsFromValidCsv() {
        final InputStream is = getClass().getClassLoader().getResourceAsStream("files/transaction-import/valid-import.csv");

        given()
                .multiPart("file", "valid-import.csv", is, "text/csv")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA)
                .when().post()
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }
}
