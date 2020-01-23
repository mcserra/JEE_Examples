package com.pubsub;

import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);

    public void init(@Observes String event) {
        LOGGER.info("OBSERVED: {}", event);
    }
}
