package cn.ch3nnn.desktopsubtitle;

import javax.websocket.*;
import java.io.IOException;

/**
 * @author chentong
 */
@ClientEndpoint
public class Websocket {

    Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println(message);

        StreamSpeechTask.setText(message);
        if (message.contains("\"errorCode\":\"304\"")) {
            onClose();
        }

    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnClose
    public void onClose() throws IOException {
        if (this.session.isOpen()) {
            this.session.close();
        }
        System.out.println("session close");
        System.exit(0);
    }

}
