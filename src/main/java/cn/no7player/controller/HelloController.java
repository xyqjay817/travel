package cn.no7player.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController   {

    @RequestMapping("/hello")
    public String greeting(HttpServletRequest request) {
        request.setAttribute("str","123456789");
        return "index";
    }
}
