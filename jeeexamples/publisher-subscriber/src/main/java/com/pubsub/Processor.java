package com.pubsub;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentAutoAcknowledge;

@ApplicationScoped
public class Processor {

    private CamelContext context = new DefaultCamelContext();

    @PostConstruct
    public void init() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setBrokerURL("tcp://docker.for.mac.localhost:61616");
        context.addComponent("jms", jmsComponentAutoAcknowledge(connectionFactory));
    }

    @Produces
    @ApplicationScoped
    @JmsProcessor
    public CamelContext camelContext() {
        return context;
    }

    @PreDestroy
    void destroy() {
        context.stop();
    }
}
