package com.camel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class BootstrapContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootstrapContext.class);

    @Inject
    CamelContext context;

    @Inject
    @ConfigProperty(name = "chatId")
    String chatId;

    @Inject
    @ConfigProperty(name = "authorizationToken")
    String authorizationToken;


    @PostConstruct
    public void init() {
        String bot = String.format("telegram:bots?authorizationToken=%s&chatId=%s", authorizationToken, chatId);

        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from(bot)
                        .log("${body}")
                        .setBody()
                        .simple("Camel")
                        .bean(OutgoingStickerMessageGenerator::new, "sendMessage")
                        .to(bot);
                }
            });
            context.start();
            LOGGER.info("Created CC and added route");
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
