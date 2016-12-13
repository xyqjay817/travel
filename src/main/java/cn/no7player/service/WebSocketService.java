package cn.no7player.service;

import javax.websocket.Session;
import java.io.IOException;

/**
 * Created by Faith on 2016/12/8.
 */
public interface WebSocketService {

    void newUserJoin(Session session);

    void userQuit(String sessionId);

    void sendMessage(String senderSessionId,String recipientSessionId,String message);


    /**
     * 推送消息
     * */
    void pushMessage(String message) throws IOException;

}
