package vn.neu.soa.fms.rest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.neu.soa.fms.impl.investment.AbstractInvestable;
import vn.neu.soa.fms.impl.investment.ExternalInvestment;
import vn.neu.soa.fms.impl.investment.InternalInvestment;
import vn.neu.soa.fms.services.InvestmentServiceLocal;

import java.util.*;

@Path("/investment")
public class InvestmentController {

    @EJB
    private InvestmentServiceLocal investmentService;

    @GET
    @Path("/test")
    public Response test() {
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            investmentService.addInvestment(
                    rand.nextBoolean() ? new InternalInvestment(
                            UUID.randomUUID().toString(),
                            UUID.randomUUID().toString(),
                            new Date(),
                            new Date(),
                            Math.round(rand.nextDouble() * 1000),
                            (double) Math.round(rand.nextDouble() * 100) / 10
                    ) : new ExternalInvestment(
                            UUID.randomUUID().toString(),
                            UUID.randomUUID().toString(),
                            new Date(),
                            new Date(),
                            Math.round(rand.nextDouble() * 1000),
                            (double) Math.round(rand.nextDouble() * 100) / 10
                    )
            );
        }

        return Response.status(Response.Status.OK).build();
    }

    @GET
    public Response getAll() {
        JsonObject object = new JsonObject();
        object.addProperty("count", investmentService.getAll().size());

        JsonObject content = new JsonObject();
        investmentService.getAll().forEach((invest) -> {
            content.add(invest.getID(), invest.toJson());
        });

        object.add("content", content);
        return Response.status(Response.Status.OK).entity(object.toString()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response addInvestable(String payload) {
        JsonObject payloadObject = JsonParser.parseString(payload).getAsJsonObject();

        Map<String, Object> data = new HashMap<>();
        payloadObject.asMap().forEach((key, value) -> data.put(key, value.getAsString()));

        try {
            AbstractInvestable investable = AbstractInvestable.deserialize(data);
            investmentService.addInvestment(investable);
        } catch (Exception ex) {
            JsonObject error = new JsonObject();
            error.addProperty("success", "false");
            error.addProperty("error", "An unexpected error has occurred while trying to add investment.");
            error.addProperty("stack-trace", ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
        }

        JsonObject success = new JsonObject();
        success.addProperty("success", "true");
        success.addProperty("data", payloadObject.toString());

        return Response.status(Response.Status.OK).entity(success.toString()).build();
    }
}
