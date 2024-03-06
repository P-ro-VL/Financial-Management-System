package vn.neu.soa.fms.rest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.ejb.EJB;
import jakarta.jms.Message;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.neu.soa.fms.api.accounting.AccountingCategory;
import vn.neu.soa.fms.impl.accounting.AccountingRecord;
import vn.neu.soa.fms.impl.accounting.FinancialReport;
import vn.neu.soa.fms.services.AccountingServiceLocal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Path("/accounting")
public class AccountingController {

    @EJB
    private AccountingServiceLocal accountingService;

    @GET
    @Path("/report")
    public String createFinancialReport() {
        System.out.println(accountingService);
        Random rand = new Random();

        for(AccountingCategory category : AccountingCategory.values()) {
            if(!category.getEquation().isEmpty()) continue;

            for (int i = 0; i < ThreadLocalRandom.current().nextInt(10) + 2; i++) {
                AccountingRecord rc = new AccountingRecord(0, null, null, null, null,
                        (int) (rand.nextDouble()*100),
                        (int) (rand.nextDouble()*100),
                        category);
                accountingService.addRecord(rc);
            }
        }

        return new FinancialReport(accountingService.getBook().getAll()).createReport().getAsJsonObject().toString();
    }

    @GET
    @Path("/record/{id}")
    public Response getRecord(@PathParam("id") int id) {
        System.out.println(id);
        AccountingRecord record = accountingService.getRecord(id);
        if(record == null) return Response.status(Response.Status.NOT_FOUND).build();

        return Response.status(Response.Status.OK).entity(record.toJson().toString()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add-record")
    public Response addRecord(String payload) {
        JsonObject payloadObject = JsonParser.parseString(payload).getAsJsonObject();

        Map<String, Object> data = new HashMap<>();
        payloadObject.entrySet().forEach((entry) -> {
            data.put(entry.getKey(), entry.getValue().getAsString());
        });

        try {
            AccountingRecord record = AccountingRecord.deserialize(data);
            accountingService.addRecord(record);
        } catch (Exception ex) {
            JsonObject error = new JsonObject();
            error.addProperty("success", "false");
            error.addProperty("error", "An unexpected error has occured while trying to add record.");
            error.addProperty("stack-trace", ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
        }

        JsonObject success = new JsonObject();
        success.addProperty("success", "true");
        success.addProperty("data", payloadObject.toString());

        return Response.status(Response.Status.OK).entity(success.toString()).build();
    }
}
