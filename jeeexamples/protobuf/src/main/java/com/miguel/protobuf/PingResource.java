package com.miguel.protobuf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("ping")
public class PingResource {
    @GET
    @Produces("application/x-protobuf")
    public Response getPerson() {
        return Response.ok(
            Ping.PingMessage.newBuilder()
                .setId(1)
                .setName("Miguel")
                .setEmail("something@gmail.com")
                .addPhones(Ping.PingMessage.PhoneNumber.newBuilder()
                    .setNumber("123-123-123")
                    .setType(Ping.PingMessage.PhoneType.MOBILE)
                    .build())
                .build()
        ).build();
    }
}
