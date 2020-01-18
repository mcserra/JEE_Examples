package com.camel.telegram.bot;

import java.util.List;

import org.apache.camel.component.telegram.model.InlineQueryResultArticle;

public class ArticleResults {
    private String status;
    private Long totalResults;
    private List<Article> articles;

    public ArticleResults() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
