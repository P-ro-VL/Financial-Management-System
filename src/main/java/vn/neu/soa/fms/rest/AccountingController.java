package vn.neu.soa.fms.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/accounting")
public class AccountingController {

    @GET
    public String temp() {
        return "chao ban <3";
    }
}
