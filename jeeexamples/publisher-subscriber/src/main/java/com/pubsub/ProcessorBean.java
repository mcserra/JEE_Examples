package com.pubsub;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.FlowableEmitter;
import org.apache.camel.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class ProcessorBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorBean.class);

    @Inject
    @JmsProcessor
    FlowableEmitter<String> emitter;

    public void process(@Body String msg) {
        LOGGER.info("Received: " + msg);
        emitter.onNext(msg);
    }
}
