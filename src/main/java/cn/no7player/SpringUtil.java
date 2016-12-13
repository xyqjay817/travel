package cn.no7player;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Faith on 2016/12/13.
 */
@Configuration
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext=context;
    }

    public  static Object getBean(String classname) {
        return applicationContext.getBean(classname);
    }
}
