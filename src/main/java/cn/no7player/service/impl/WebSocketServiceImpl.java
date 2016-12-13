package cn.no7player.service.impl;

import cn.no7player.service.WebSocketService;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Faith on 2016/12/8.
 */
@Service("webSocketService")
public class WebSocketServiceImpl implements WebSocketService {



    //websocket 是多线程，会话存放集合 务必使用线程安全的集合
    private static Map<String,Session>sessionMap=new ConcurrentHashMap<>();





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

    @Override
    public void pushMessage(String message) throws IOException {
        for(String sessionId:sessionMap.keySet()){
            sessionMap.get(sessionId).getBasicRemote().sendText(message);
        }
    }
}
