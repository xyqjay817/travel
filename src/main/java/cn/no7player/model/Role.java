package cn.no7player.model;

import cn.no7player.dao.base.bean.Po;
import cn.no7player.dao.base.po.FieldName;
import cn.no7player.dao.base.po.TableName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * 角色（管理员，普通用户等）
 */
@TableName(name = "role")
@Data
public class Role extends Po{

    @FieldName(name = "ROLE_NAME")
    private String roleName;
}