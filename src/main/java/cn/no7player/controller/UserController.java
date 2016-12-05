package cn.no7player.controller;

import cn.no7player.webSocket.MyWebSocket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Faith on 2016/12/5.
 */
@Controller
@RequestMapping("/web")
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String greeting(HttpServletRequest request) throws IOException {
        return "hello";
    }
}
