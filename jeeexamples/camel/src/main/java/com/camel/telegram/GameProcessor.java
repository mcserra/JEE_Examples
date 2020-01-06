package com.camel.telegram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Named;

import org.apache.camel.Body;
import org.apache.camel.component.telegram.model.CallbackGame;
import org.apache.camel.component.telegram.model.IncomingCallbackQuery;
import org.apache.camel.component.telegram.model.IncomingMessage;
import org.apache.camel.component.telegram.model.InlineKeyboardButton;
import org.apache.camel.component.telegram.model.InlineKeyboardMarkup;
import org.apache.camel.component.telegram.model.OutgoingCallbackQueryMessage;
import org.apache.camel.component.telegram.model.OutgoingGameMessage;

/**
 * Simple test for game bot.
 */
@Named
public class GameProcessor {

    public Object processGame(@Body Object update) {
        if (update instanceof IncomingMessage) {
            IncomingMessage incomingMessage = (IncomingMessage) update;
            return processIncomingMessage(incomingMessage);
        } else if(update instanceof IncomingCallbackQuery) {
            IncomingCallbackQuery query = (IncomingCallbackQuery) update;
            return processCallback(query);
        }
        return "unknown option";
    }

    private OutgoingCallbackQueryMessage processCallback(IncomingCallbackQuery query) {
        OutgoingCallbackQueryMessage c = new OutgoingCallbackQueryMessage();
        c.setCallbackQueryId(query.getId());
        c.setUrl( "https://test-dinosaur-telegram-game.herokuapp.com");
        c.setShowAlert(true);
        c.setText("game");
        return c;
    }

    private Object processIncomingMessage(IncomingMessage incomingMessage) {
        if ("new-game".equals(incomingMessage.getText())) {
            return sendGame();
        } else {
            return "send 'new-game' to play dinosaur";
        }
    }

    private OutgoingGameMessage sendGame() {
        String gameShortName = "tets";

        InlineKeyboardButton ib = new InlineKeyboardButton();
        ib.setCallbackGame(new CallbackGame(gameShortName));
        ib.setText("Start Game");
        List<List<InlineKeyboardButton>> iButtons = new ArrayList<>();
        iButtons.add(new ArrayList<>(Collections.singletonList(ib)));

        OutgoingGameMessage outgoingGameMessage = new OutgoingGameMessage();
        outgoingGameMessage.setGameShortName(gameShortName);
        outgoingGameMessage.setReplyMarkup(new InlineKeyboardMarkup(iButtons));

        return outgoingGameMessage;
    }
}
