package com.camel.telegram.bot;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.camel.component.telegram.model.IncomingInlineQuery;
import org.apache.camel.component.telegram.model.InlineQueryResult;
import org.apache.camel.component.telegram.model.OutgoingAnswerInlineQuery;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InlineQueryProcessor {

    @ConfigProperty(name = "apiKey")
    @Inject
    private String apiKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(InlineQueryProcessor.class);

    public OutgoingAnswerInlineQuery process(IncomingInlineQuery query) {
        if (!query.getQuery().isBlank()) {
            NewsApiResource newsApiResource = RestClientBuilder.newBuilder()
                .baseUri(URI.create("https://newsapi.org/v2/everything"))
                .sslContext(SslContext.getTrustfulSSlContext())
                .build(NewsApiResource.class);

            ArticleResults articleResults = newsApiResource.find(query.getQuery(), apiKey);

            List<InlineQueryResult> iqa = articleResults.getArticles().stream()
                .map(Article::toInlineQueryResultArticle)
                .collect(Collectors.toList());

            return OutgoingAnswerInlineQuery.builder()
                .results(iqa)
                .inlineQueryId(query.getId())
                .build();
        }
        return null;
    }

}
