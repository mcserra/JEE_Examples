package com.pubsub;

import java.time.Instant;

import org.apache.camel.builder.RouteBuilder;

public class JmsPubSub extends RouteBuilder{
    private static final String queue = "jms:queue:Stocks.Prices";

    @Override
    public void configure() {
        from("timer://foo?fixedRate=true&period=1000")
            .setBody(simple("Hello"))
            .log("${body} " + Instant.now())
            .to(queue);
        from(queue)
            .bean(ProcessorBean.class, "process")
            .log("ended: " + Instant.now());
    }
}
