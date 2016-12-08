package cn.no7player.service.impl;

import cn.no7player.service.WebSocketService;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Faith on 2016/12/8.
 */
public class WebSocketServiceImpl implements WebSocketService {

    private static Map<String,Session>sessionMap;

    @Override
    public void newUserJoin(Session session) {

        String sessionId=session.getId();
        if(!sessionMap.containsKey(sessionId)){
            sessionMap.put(sessionId,session);
        }
        //todo 需要检查是否有未接收到的消息在服务器数据库
    }

    @Override
    public void userQuit(String sessionId) {
        sessionMap.remove(sessionId);
    }

    @Override
    public void sendMessage(String senderSessionId, String recipientSessionId,String message) {
        try {
            sessionMap.get(recipientSessionId).getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
