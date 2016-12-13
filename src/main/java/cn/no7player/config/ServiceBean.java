package cn.no7player.config;

import cn.no7player.service.UserService;
import cn.no7player.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Faith on 2016/12/12.
 */
@Configuration
public class ServiceBean {


    @Bean(name = "userService1")
    public UserService getUserService(){

        return new UserServiceImpl();
    }
}
