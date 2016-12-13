package cn.no7player.webSocket;

import cn.no7player.SpringUtil;
import cn.no7player.service.UserService;
import cn.no7player.service.WebSocketService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Faith on 2016/12/5.
 */
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {


    //终于注入bean了，不容易啊
    private WebSocketService webSocketService= (WebSocketService) SpringUtil.getBean("webSocketService");

    private String sessionId;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.sessionId=session.getId();
        webSocketService.newUserJoin(session);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketService.userQuit(sessionId);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        JSONObject jsonObject=JSONObject.parseObject(message);
    }

    /**
     * 发生错误时调用
     @OnError*/
    @OnError
     public void onError(Session session, Throwable error) {
     System.out.println("发生错误");
     error.printStackTrace();
     }

}