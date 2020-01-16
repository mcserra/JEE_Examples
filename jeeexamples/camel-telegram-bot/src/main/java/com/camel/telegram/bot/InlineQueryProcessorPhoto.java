package com.camel.telegram.bot;

import java.util.List;

import org.apache.camel.Body;
import org.apache.camel.component.telegram.model.InlineQuery;
import org.apache.camel.component.telegram.model.InlineQueryResult;
import org.apache.camel.component.telegram.model.InlineQueryResultArticle;
import org.apache.camel.component.telegram.model.InlineQueryResultAudio;
import org.apache.camel.component.telegram.model.InlineQueryResultContact;
import org.apache.camel.component.telegram.model.InlineQueryResultDocument;
import org.apache.camel.component.telegram.model.InlineQueryResultGame;
import org.apache.camel.component.telegram.model.InlineQueryResultGif;
import org.apache.camel.component.telegram.model.InlineQueryResultLocation;
import org.apache.camel.component.telegram.model.InlineQueryResultMpeg4Gif;
import org.apache.camel.component.telegram.model.InlineQueryResultPhoto;
import org.apache.camel.component.telegram.model.InlineQueryResultVenue;
import org.apache.camel.component.telegram.model.InlineQueryResultVideo;
import org.apache.camel.component.telegram.model.InlineQueryResultVoice;
import org.apache.camel.component.telegram.model.InputMessageContent;
import org.apache.camel.component.telegram.model.InputMessageContentContact;
import org.apache.camel.component.telegram.model.InputMessageContentLocation;
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
            msg.setResults(List.of(photo(), article(), audio(), contact(), contact2(), doc(), game(), gif(),
                mpeg4gif(), venue(), video(), voice()));
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

    private InlineQueryResult contact() {

        return InlineQueryResultContact.builder()
            .id("contact")
            .phoneNumber("918552962")
            .firstName("Luis")
            .lastName("Santos")
            .thumbUrl("https://media-exp1.licdn.com/dms/image/C5603AQEB8XqzPJRqvw/profile-displayphoto-shrink_200_200/0?e=1584576000&v=beta&t=FUkxhAgeQLial2t2Fg2GCuiR8nfGYaRu_XQ_j5iAfec")
            .build();
    }

    private InlineQueryResult contact2() {

        InputMessageContent inputMessageContent = InputMessageContentContact.builder()
            .firstName("Stephane")
            .lastName("Cruz")
            .phoneNumber("918652962")
            .build();

        return InlineQueryResultContact.builder()
            .id("contact1")
            .firstName("Stephane")
            .phoneNumber("918652962")
            .inputMessageContext(inputMessageContent)
            .thumbUrl("https://media-exp1.licdn.com/dms/image/C5603AQHFM6QFUNIAaw/profile-displayphoto-shrink_200_200/0?e=1584576000&v=beta&t=JyzgAWdtzH9W5o40vN0Mb1TPQrZQw-hPwfuTacIGXhE")
            .build();
    }

    private InlineQueryResult doc() {

        return InlineQueryResultDocument.builder()
            .id("doc")
            .title("doc_title")
            .mimeType("application/pdf")
            .documentUrl("http://www.pdfpdf.com/samples/Sample1.PDF")
            .thumbUrl("https://onlinejpgtools.com/images/examples-onlinejpgtools/mouse.jpg")
            .build();
    }

    private InlineQueryResult game() {
        return InlineQueryResultGame.builder()
            .id("gma")
            .gameShortName("tets")
            .build();
    }

    private InlineQueryResult gif() {
        return InlineQueryResultGif.builder()
            .id("gif")
            .title("gif")
            .gifUrl("https://media.giphy.com/media/1TSUKOv4k56aIryKAP/giphy.gif")
            .thumbUrl("https://www.google.pt/imgres?imgurl=https%3A%2F%2Fw0.pngwave.com%2Fpng%2F112%2F293%2Fdonald-trump-png-clip-art-thumbnail.png&imgrefurl=https%3A%2F%2Fwww.pngwave.com%2Fpng-clip-art-wcjfs&docid=2W1iJeT1UIwOMM&tbnid=n2tW8zQdR9i7XM%3A&vet=10ahUKEwjPvrCcjIbnAhWNA2MBHbskAtIQMwhMKAYwBg..i&w=350&h=424&client=opera&bih=1066&biw=2088&q=thumbnail%20trump&ved=0ahUKEwjPvrCcjIbnAhWNA2MBHbskAtIQMwhMKAYwBg&iact=mrc&uact=8")
            .build();
    }

    //not work
    private InlineQueryResult location() {
        InputMessageContentLocation inputMessageContentLocation = InputMessageContentLocation.builder()
            .latitude(20f)
            .longitude(20f)
            .livePeriod(200)
            .build();

        return InlineQueryResultLocation.builder()
            .id("location")
            .latitude(20.0f)
            .inputMessageContext(inputMessageContentLocation)
            .longitude(20.3f)
            .title("location_tilte")
            .thumbUrl("https://www.google.pt/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=2ahUKEwjjoY7_v4bnAhXkA2MBHWLlC1EQjRx6BAgBEAQ&url=https%3A%2F%2Fpt.wikipedia.org%2Fwiki%2FSegunda_Guerra_Civil_da_Lib%25C3%25A9ria&psig=AOvVaw2j3wYSjNN-DeYfrzte6tiW&ust=1579208432677682")
            .build();
    }

    private InlineQueryResult mpeg4gif() {

        return InlineQueryResultMpeg4Gif.builder()
            .id("mpeg4gif")
            .mpeg4Url("https://media.giphy.com/media/lT4N7JiPGATIhVwR91/giphy.gif")
            .thumbUrl("https://media.giphy.com/media/lT4N7JiPGATIhVwR91/giphy.gif")
            .title("mpeg4gif")
            //.thumbUrl("https://www.google.pt/imgres?imgurl=https%3A%2F%2Fw0.pngwave.com%2Fpng%2F112%2F293%2Fdonald-trump-png-clip-art-thumbnail.png&imgrefurl=https%3A%2F%2Fwww.pngwave.com%2Fpng-clip-art-wcjfs&docid=2W1iJeT1UIwOMM&tbnid=n2tW8zQdR9i7XM%3A&vet=10ahUKEwjPvrCcjIbnAhWNA2MBHbskAtIQMwhMKAYwBg..i&w=350&h=424&client=opera&bih=1066&biw=2088&q=thumbnail%20trump&ved=0ahUKEwjPvrCcjIbnAhWNA2MBHbskAtIQMwhMKAYwBg&iact=mrc&uact=8")
            .build();
    }

    private InlineQueryResult venue() {

        return InlineQueryResultVenue.builder()
            .id("venue")
            .latitude(20.0f)
            .longitude(20.3f)
            .title("venue")
            .address("Capitao roby")
            .thumbUrl("https://www.google.pt/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=2ahUKEwjjoY7_v4bnAhXkA2MBHWLlC1EQjRx6BAgBEAQ&url=https%3A%2F%2Fpt.wikipedia.org%2Fwiki%2FSegunda_Guerra_Civil_da_Lib%25C3%25A9ria&psig=AOvVaw2j3wYSjNN-DeYfrzte6tiW&ust=1579208432677682")
            .build();
    }

    private InlineQueryResult video() {
        return InlineQueryResultVideo.builder()
            .id("video")
            .videoUrl("https://www.sample-videos.com/video123/mp4/720/big_buck_bunny_720p_1mb.mp4")
            .mimeType("video/mp4")
            .title("videoo")
            .thumbUrl("https://onlinejpgtools.com/images/examples-onlinejpgtools/mouse.jpg")
            .build();

    }

    private InlineQueryResult voice() {
        return InlineQueryResultVoice.builder()
            .title("TIT")
            .id("voice")
            .voiceUrl("http://opus-codec.org/static/examples/samples/speech_orig.wav")
            .build();
    }

}
