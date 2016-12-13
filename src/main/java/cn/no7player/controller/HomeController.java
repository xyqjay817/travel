package cn.no7player.controller;

import cn.no7player.SpringUtil;
import cn.no7player.service.UserService;

import cn.no7player.service.WebSocketService;
import cn.no7player.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/web")
public class HomeController {


    private UserService userService;


    @Resource(name = "webSocketService")
    private WebSocketService webSocketService;


    @RequestMapping("/home")
    @ResponseBody
    public String greeting(HttpServletRequest request) throws IOException {
        webSocketService.pushMessage("hahaha");
        return "home";
    }

    @RequestMapping("/websocket")
    public String websocket(HttpServletRequest request) throws IOException {
        userService= (UserService) SpringUtil.getBean("userService");
//        MyWebSocket.sendInfo("你大爺");
        return "websocket";
    }

}
