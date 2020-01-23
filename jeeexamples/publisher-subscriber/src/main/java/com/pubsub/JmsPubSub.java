package com.pubsub;

import java.time.Instant;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
public class JmsPubSub {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsPubSub.class);

    @Inject
    @JmsProcessor
    CamelContext context;

    @PostConstruct
    public void init() {

        LOGGER.info("PROCESSOR");
        final String queue = "jms:queue:Stocks.Prices";
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from("timer://foo?fixedRate=true&period=1000")
                        .setBody(simple("Hello"))
                        .log("${body} " + Instant.now())
                        .to(queue);
                }
            });
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from(queue)
                        .bean(ProcessorBean.class, "process")
                        .log("ended: " + Instant.now());
                }
            });
            context.start();
        } catch (Exception ex) {
            LOGGER.info(String.format("Error creating CC: %s", ex.getMessage()));
        }
    }

    @PreDestroy
    public void shutdown() {
        try {
            context.stop();
        } catch (Exception e) {
            System.out.println("didn't stop");
        }
        LOGGER.info("CC stopped");
    }
}
