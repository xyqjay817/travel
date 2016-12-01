package cn.no7player.service;

import cn.no7player.dao.UserDao;
import cn.no7player.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zl on 2015/8/27.
 */

@Service
public class UserService {


    @Resource(name = "userDao")
    private UserDao userDao;


    public  List<User>list(){
        return userDao.list();
    }

}
