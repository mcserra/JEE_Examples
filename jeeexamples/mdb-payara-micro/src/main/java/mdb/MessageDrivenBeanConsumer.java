package mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "${jms.test.queue}"),
    @ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "${jms.resource.adapter}")
})
public class MessageDrivenBeanConsumer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            final String mess = ((TextMessage) message).getText();
            System.out.println(mess);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
