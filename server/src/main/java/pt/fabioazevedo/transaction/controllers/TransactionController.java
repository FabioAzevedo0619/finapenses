package pt.fabioazevedo.transaction.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import pt.fabioazevedo.transaction.dto.Transaction;
import pt.fabioazevedo.transaction.services.CreateTransactionService;

@Path("/transactions")
@Produces("application/json")
@Consumes("application/json")
public class TransactionController {

    @Inject
    CreateTransactionService createTransactionService;

    @POST
    public Response createTransaction(final Transaction transaction) {
        createTransactionService.createTransaction(transaction);
        return Response.status(Response.Status.CREATED).build();
    }
}
