package cn.no7player.dao;

import cn.no7player.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Faith on 2016/12/1.
 */
@Repository
public class UserDao extends BaseDao{


    public List<User> list(){
        return sqlSession.selectList("USER.list");
    }
}
