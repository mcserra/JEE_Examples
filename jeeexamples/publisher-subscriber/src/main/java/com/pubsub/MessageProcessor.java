package com.pubsub;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ejb.Singleton;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class MessageProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);

    @Inject
    @JmsProcessor
    private Publisher<String> publisher;

    @PostConstruct
    public void init() {
        LOGGER.info("PROCESSOR");
        Flowable.fromPublisher(publisher)
            .subscribeOn(Schedulers.io())
            .parallel()
            .doOnNext(a -> LOGGER.info("PROCESSED"))
            .sequential()
            .subscribe(
                val -> LOGGER.info("Subscriber received: {}", val),
                err -> LOGGER.error("Subscriber received error", err),
                () -> LOGGER.info("Subscriber got Completed event")
            );
    }

    @PreDestroy
    public void terminate() {
        // Perform termination
        System.out.println("Shut down in progress");
    }
}
