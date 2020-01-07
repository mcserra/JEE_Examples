package com.pubsub;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentAutoAcknowledge;

@Startup
@Singleton
public class JmsReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsReceiver.class);

    @Inject
    CamelContext context;

    @PostConstruct
    public void init() {
        final String queue = "jms:queue:Stocks.Prices";
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setBrokerURL("tcp://docker.for.mac.localhost:61616");
        context.addComponent("jms", jmsComponentAutoAcknowledge(connectionFactory));
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from("timer://foo?fixedRate=true&period=6000")
                        .setBody(simple("Hello"))
                        .log("${body}")
                        .to(queue);
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
