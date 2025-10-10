package pt.fabioazevedo.transaction.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pt.fabioazevedo.transaction.services.ImportTransactionService;

import java.io.InputStream;

@Path("/transactions/import")
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class TransactionImportController {

    @Inject
    ImportTransactionService importTransactionService;

    @POST
    public Response importTransactions(@FormParam("file") final InputStream file) {
        importTransactionService.importTransactions(file);
        return Response.status(Response.Status.CREATED).build();
    }
}
