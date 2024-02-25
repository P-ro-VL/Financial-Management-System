package vn.neu.soa.fms.rest;

import junit.framework.TestResult;
import vn.neu.soa.fms.Test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/accounting")
public class AccountingController {

    @GET
    public String temp() {
        TestResult result = Test.test();
        return result.wasSuccessful() + "";
    }
}
