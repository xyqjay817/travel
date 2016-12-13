package cn.no7player.service.impl;

import cn.no7player.dao.UserDao;
import cn.no7player.model.User;
import cn.no7player.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zl on 2015/8/27.
 */

@Service("userService")
public class UserServiceImpl implements UserService {


    @Resource(name = "userDao")
    private UserDao userDao;


    @Override
    @Transactional
    public void add(User user) {
        //加密
        user.setPassword(new Md5Hash(user.getPassword(),user.getUserName()).toString());
        userDao.add(user);
    }
}
