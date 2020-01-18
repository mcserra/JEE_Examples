package com.camel.telegram.bot;

import java.util.UUID;

import org.apache.camel.component.telegram.model.InlineQueryResultArticle;
import org.apache.camel.component.telegram.model.InputTextMessageContent;

public class Article {
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;

    public Article() {
    }

    public InlineQueryResultArticle toInlineQueryResultArticle() {

        InputTextMessageContent itm = InputTextMessageContent.builder()
            .messageText(String.format("<a href=\"%s\">%s</a>", url, url))
            .parseMode("HTML")
            .build();
        return InlineQueryResultArticle.builder()
            .id(UUID.randomUUID().toString())
            .description(description)
            .title(title)
            .thumbUrl(urlToImage)
            .inputMessageContext(itm)
            .url(url)
            .build();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
