package springbook.learningtest.factorybean;

/**
 * @author Kj Nam
 * @since 2017-06-09
 */
public class Message {
    private String text;

    private Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static Message newMessage(String text) {
        return new Message(text);
    }
}
