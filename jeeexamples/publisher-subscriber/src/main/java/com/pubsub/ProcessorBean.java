package com.pubsub;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.apache.camel.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessorBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorBean.class);

    @Inject
    private Event<String> emitter;

    public void process(@Body String msg) {
        LOGGER.info("Received: {}", msg);
        emitter.fire(msg);
    }
}
