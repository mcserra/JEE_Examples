package com.camel;

import javax.inject.Named;

import org.apache.camel.Body;
import org.apache.camel.component.telegram.model.EditMessageTextMessage;
import org.apache.camel.component.telegram.model.OutgoingStickerMessage;

@Named
public class OutgoingStickerMessageGenerator {
    public OutgoingStickerMessage sendMessage(@Body String stickerId) {
        return OutgoingStickerMessage.createWithFileId("CAADAgADOQADfyesDlKEqOOd72VKAg", null,null, null);
    }
}
