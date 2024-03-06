package vn.neu.soa.fms.rest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;
import jakarta.json.Json;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.neu.soa.fms.impl.budget.Budget;
import vn.neu.soa.fms.impl.budget.BudgetPredictResult;
import vn.neu.soa.fms.impl.budget.BudgetType;
import vn.neu.soa.fms.services.BudgetServiceLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/budget")
public class BudgetController {

    @EJB
    private BudgetServiceLocal budgetService;

    @GET
    @Path("/test")
    public Response test() {
        Random rand = new Random();

        for(BudgetType type : BudgetType.values()) {
            for (int i = 0; i < 10; i++) {
                budgetService.addBudget(
                        new Budget(
                                (int)(rand.nextDouble()*100),
                                type,
                                UUID.randomUUID().toString(),
                                Math.round(rand.nextDouble()*1000),
                                Math.round(rand.nextDouble()*1000)
                        )
                );
            }
        }

        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/report")
    public Response getReport() {
        double totalExpected = budgetService.getAll().stream().mapToDouble(Budget::getExpectedAmount).sum();
        double totalSpent = budgetService.getAll().stream().mapToDouble(Budget::getSpentAmount).sum();

        JsonObject object = new JsonObject();
        object.addProperty("total_expected", totalExpected);
        object.addProperty("total_spent", totalSpent);

        JsonObject content = new JsonObject();
        budgetService.getAll().forEach((budget) -> {
            content.add(budget.getBudgetId() + "", budget.toJson());
        });

        object.add("content", content);
        return Response.status(Response.Status.OK).entity(object.toString()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response addBudget(String payload) {
        JsonObject payloadObject = JsonParser.parseString(payload).getAsJsonObject();

        Map<String, Object> data = new HashMap<>();
        payloadObject.asMap().forEach((key, value) -> data.put(key, value.getAsString()));

        try {
            Budget budget = Budget.deserialize(data);
            budgetService.addBudget(budget);
        } catch (Exception ex) {
            JsonObject error = new JsonObject();
            error.addProperty("success", "false");
            error.addProperty("error", "An unexpected error has occured while trying to add budget.");
            error.addProperty("stack-trace", ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
        }

        JsonObject success = new JsonObject();
        success.addProperty("success", "true");
        success.addProperty("data", payloadObject.toString());

        return Response.status(Response.Status.OK).entity(success.toString()).build();
    }

    @GET
    @Path("/analysis")
    public Response analysisBudget() {
        JsonObject jsonObject = new JsonObject();

        double totalExpected = budgetService.getAll().stream().mapToDouble(Budget::getExpectedAmount).sum();
        double totalSpent = budgetService.getAll().stream().mapToDouble(Budget::getSpentAmount).sum();
        double usedRatio = (double) Math.round((totalSpent / totalExpected) * 1000) / 10;

        jsonObject.addProperty("total-expected", totalExpected);
        jsonObject.addProperty("total-spent", totalSpent);
        jsonObject.addProperty("used-ratio", usedRatio + "%");
        jsonObject.addProperty("total-remaining", totalExpected - totalSpent);

        JsonObject groups = new JsonObject();

        for(BudgetType type : BudgetType.values()) {
            double _totalExpected = budgetService.getBudgets(type).stream().mapToDouble(Budget::getExpectedAmount).sum();
            double _totalSpent = budgetService.getBudgets(type).stream().mapToDouble(Budget::getSpentAmount).sum();
            double _usedRatio = (double) Math.round((_totalSpent / _totalExpected) * 1000) / 10;

            JsonObject typeObject = new JsonObject();
            double remaining = _totalExpected - _totalSpent;
            typeObject.addProperty("total-expected", _totalExpected);
            typeObject.addProperty("total-spent", _totalSpent);
            typeObject.addProperty("used-ratio", _usedRatio + "%");
            typeObject.addProperty("total-remaining", remaining);
            typeObject.addProperty("status", remaining > 0 ? BudgetPredictResult.REDUNDANT.toString() :
                    (remaining < 0 ? BudgetPredictResult.SHORTAGE.toString() : BudgetPredictResult.ENOUGH.toString()));

            groups.add(type.toString(), typeObject);
        }

        jsonObject.add("groups", groups);

        return Response.status(Response.Status.OK).entity(jsonObject.toString()).build();
    }
}
