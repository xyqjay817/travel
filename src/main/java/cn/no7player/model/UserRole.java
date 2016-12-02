package cn.no7player.model;

import cn.no7player.dao.base.bean.Po;
import cn.no7player.dao.base.po.FieldName;
import cn.no7player.dao.base.po.TableName;
import lombok.Data;


/**
 * Created by Faith on 2016/12/2.
 */
@Data
@TableName(name = "user_role")
public class UserRole extends Po{
    @FieldName(name = "USER_ID")
    private Integer userId;
    @FieldName(name = "ROLE_ID")
    private Integer roleId;
}
