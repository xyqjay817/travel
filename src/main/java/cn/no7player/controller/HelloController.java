package cn.no7player.controller;

import cn.no7player.model.User;
import cn.no7player.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController   {


    @Resource(name = "userService")
    private UserService userService;


    @RequestMapping("/home")
    public String greeting(HttpServletRequest request) {

        User user=new User();
        user.setPassword("123456");
        user.setUserName("dengrijin");
        userService.add(user);
        return "index";
    }
}
