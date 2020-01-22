package com.camel.telegram.bot;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class CamelContextBootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamelContextBootstrap.class);

    @Inject
    private CamelContext context;

    @PostConstruct
    public void init()  {
        context.start();
    }

    @PreDestroy
    public void shutdown() {
        context.stop();
        LOGGER.info("CC stopped");
    }
}
