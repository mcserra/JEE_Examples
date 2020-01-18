package com.camel.telegram.bot;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("")
@RegisterRestClient
public interface NewsApiResource {

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    ArticleResults find(@QueryParam("q") String keywords, @QueryParam("apiKey") String apiKey);
}
