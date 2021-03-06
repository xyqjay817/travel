package cn.no7player.model;

import cn.no7player.dao.base.bean.Po;
import cn.no7player.dao.base.po.FieldName;
import cn.no7player.dao.base.po.TableName;
import lombok.Data;


/**
 * 权限（增删改查等）
 *
 */
@TableName(name = "permission")
@Data
public class Permission extends Po{
    @FieldName(name = "PERMISSION_NAME")
    private String permissionName;
    @FieldName(name = "ROLE_ID")
    private Integer roleId;


}