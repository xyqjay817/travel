package cn.no7player.mapper;

import cn.no7player.model.User;

import java.util.List;

/**
 * Created by zl on 2015/8/27.
 */
public interface UserMapper {
    public User findUserInfo();

    List<User> list();


}
