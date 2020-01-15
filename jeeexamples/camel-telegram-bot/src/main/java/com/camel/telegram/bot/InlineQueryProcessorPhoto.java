package com.camel.telegram.bot;

import java.util.List;

import org.apache.camel.Body;
import org.apache.camel.component.telegram.model.IncomingPhotoSize;
import org.apache.camel.component.telegram.model.InlineQuery;
import org.apache.camel.component.telegram.model.InlineQueryResult;
import org.apache.camel.component.telegram.model.InlineQueryResultArticle;
import org.apache.camel.component.telegram.model.InlineQueryResultAudio;
import org.apache.camel.component.telegram.model.InlineQueryResultPhoto;
import org.apache.camel.component.telegram.model.InputMessageContent;
import org.apache.camel.component.telegram.model.InputMessageContentText;
import org.apache.camel.component.telegram.model.OutgoingAnswerInlineQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InlineQueryProcessorPhoto {

    private static final Logger LOGGER = LoggerFactory.getLogger(InlineQueryProcessorPhoto.class);

    public OutgoingAnswerInlineQuery process(@Body Object update) {
        if (update instanceof InlineQuery) {
            InlineQuery query = (InlineQuery) update;
            OutgoingAnswerInlineQuery msg = new OutgoingAnswerInlineQuery();
            msg.setInlineQueryId(query.getId());
            msg.setResults(List.of(photo(), article(), audio()));
            return msg;
        } else {
            LOGGER.info("NOPE");
        }
        return null;
    }

    private InlineQueryResult photo() {
        return InlineQueryResultPhoto.builder()
            .id("photo")
            .title("photo_title")
            .photoUrl("https://onlinejpgtools.com/images/examples-onlinejpgtools/mouse.jpg")
            .thumbUrl("https://onlinejpgtools.com/images/examples-onlinejpgtools/mouse.jpg")
            .build();
    }

    private InlineQueryResult article() {
        String uri = "https://camel.apache.org/manual/latest/contributing.html";
        InputMessageContent inputMessageContent = InputMessageContentText.builder()
            .messageText(String.format("<a href=\"%s\">%s</a>", uri, uri))
            .parseMode("HTML")
            .build();

        return InlineQueryResultArticle.builder()
            .id("article")
            .title("artile_title")
            .description("test-article")
            .url(uri)
            .thumbUrl("https://pbs.twimg.com/profile_images/1090189047367192577/xWt1RFo6.jpg")
            .inputMessageContext(inputMessageContent)
            .build();
    }

    private InlineQueryResult audio() {

        return InlineQueryResultAudio.builder()
            .id("audio")
            .title("audio_title")
            .audioUrl("https://file-examples.com/index.php/sample-audio-files/sample-mp3-download/")
            .build();
    }
}
