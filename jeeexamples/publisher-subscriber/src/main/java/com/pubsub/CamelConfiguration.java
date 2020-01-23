package com.pubsub;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

/**
 * CamelConfiguration.
 */
public class CamelConfiguration {

    @Produces
    @ApplicationScoped
    @Named("jms")
    private JmsComponent jms() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setBrokerURL("tcp://docker.for.mac.localhost:61616");
        return JmsComponent.jmsComponentAutoAcknowledge(connectionFactory);
    }

}
