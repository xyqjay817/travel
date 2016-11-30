package cn.no7player.controller;

import cn.no7player.model.User;
import cn.no7player.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HelloController   {


    @Autowired
    private UserService userService;


    @RequestMapping("/hello")
    public String greeting(HttpServletRequest request) {

//        PageHelper.startPage(1,3);
        List<User> users=userService.list();
        User user=userService.getUserInfo();
        request.setAttribute("str","123456789");
        return "index";
    }
}
