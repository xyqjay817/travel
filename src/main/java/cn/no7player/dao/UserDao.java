package cn.no7player.dao;

import cn.no7player.dao.base.BaseDao;
import cn.no7player.dao.base.bean.WherePrams;
import cn.no7player.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Faith on 2016/12/1.
 */
@Repository("userDao")
public class UserDao extends BaseDao <User,Integer> {


    public List<User> list(){
        return sqlSession.selectList("USER.list");
    }

    public User findByName(String name){
        return get(new WherePrams("USER_NAME","=",name));
    }
}
