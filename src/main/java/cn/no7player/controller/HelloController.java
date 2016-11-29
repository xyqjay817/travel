package cn.no7player.controller;

import cn.no7player.config.FreeMarkerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @Autowired
    FreeMarkerConfig fc;

    @RequestMapping("/hello")
    public String greeting(HttpServletRequest request) {
        request.setAttribute("str","hahha");
        return "index";
    }
    
}
