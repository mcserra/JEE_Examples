package com.pubsub;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.apache.camel.CamelContext;

public class CamelBootstrap {

    @Inject
    private CamelContext context;

    @PostConstruct
    public void init() {
        context.start();
    }

    @PreDestroy
    public void destroy() {
        context.stop();
    }
}
