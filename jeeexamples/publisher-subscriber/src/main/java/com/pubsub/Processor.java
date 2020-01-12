package com.pubsub;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.reactivestreams.Publisher;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentAutoAcknowledge;

@ApplicationScoped
public class Processor {

    private ActiveMQConnectionFactory connectionFactory;
    private FlowableEmitter<String> emitter;
    private Flowable<String> flowable;
    private CamelContext context = new DefaultCamelContext();

    @PostConstruct
    public void init() {
        connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setBrokerURL("tcp://docker.for.mac.localhost:61616");
        context.addComponent("jms", jmsComponentAutoAcknowledge(connectionFactory));
        this.flowable = Flowable.create(e -> this.emitter = e, BackpressureStrategy.LATEST);
    }

    @Produces
    @ApplicationScoped
    @JmsProcessor
    public ActiveMQConnectionFactory connectionFactory() {
        return connectionFactory;
    }

    @Produces
    @ApplicationScoped
    @JmsProcessor
    public CamelContext camelContext;

    @Produces
    @ApplicationScoped
    @JmsProcessor
    public Publisher<String> flowable() {
        return flowable;
    }

    @Produces
    @ApplicationScoped
    @JmsProcessor
    public FlowableEmitter<String> emitter() {
        return emitter;
    }

    @PreDestroy
    void destroy() {
        camelContext.stop();
    }
}
