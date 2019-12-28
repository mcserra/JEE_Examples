package com.camel;

import javax.inject.Named;

import org.apache.camel.Body;
import org.apache.camel.component.telegram.model.OutgoingStickerMessage;

@Named
public class OutgoingStickerMessageGenerator {
    public OutgoingStickerMessage sendMessage(@Body String stickerId) {
        OutgoingStickerMessage om =  new OutgoingStickerMessage();
        om.setSticker("CAADAgADOQADfyesDlKEqOOd72VKAg"); // stickerId
        return om;
    }
}
