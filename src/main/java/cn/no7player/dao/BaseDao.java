package cn.no7player.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Faith on 2016/12/1.
 */
public abstract class  BaseDao {
    @Autowired
    protected SqlSessionTemplate sqlSession;
}
