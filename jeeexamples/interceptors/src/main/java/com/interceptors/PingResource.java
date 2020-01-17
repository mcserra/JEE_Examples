package com.interceptors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("ping")
@PingInterceptor
public class PingResource {

    @GET
    @PingInterceptor
    public Response ping(@QueryParam("person") final String person) {
        return Response.ok("Hello from: " + person).build();
    }
}
