package cn.no7player.model;



import cn.no7player.dao.base.bean.Po;
import cn.no7player.dao.base.po.FieldName;
import cn.no7player.dao.base.po.TableName;
import lombok.Data;

/**
 * 用户
 *
 */
@TableName(name = "user")
@Data
public class User extends Po{
    @FieldName(name = "USER_NAME")
    private String userName;
    @FieldName(name = "PASSWORD")
    private String password;
    @FieldName(name = "AVAILABLE")
    private String  available;
}