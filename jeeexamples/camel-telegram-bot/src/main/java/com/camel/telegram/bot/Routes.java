package com.camel.telegram.bot;

import javax.inject.Inject;

import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.telegram.model.IncomingInlineQuery;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class Routes extends RouteBuilder {

    @Inject
    @ConfigProperty(name = "chatId")
    String chatId;

    @Inject
    @ConfigProperty(name = "authorizationToken")
    String authorizationToken;

    @Override
    public void configure() throws Exception {
        String bot = String.format("telegram:bots?authorizationToken=%s&chatId=%s", authorizationToken, chatId);

        from(bot)
            .choice()
            .when(bodyTypeIs(IncomingInlineQuery.class))
            .bean(InlineQueryProcessor.class, "process")
            .to(bot)
            .otherwise();
    }

    private Predicate bodyTypeIs(Class<?> o) {
        return e -> e.getIn().getBody().getClass().isAssignableFrom(o);
    }
}
