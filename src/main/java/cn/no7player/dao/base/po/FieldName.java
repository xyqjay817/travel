package cn.no7player.dao.base.po;

import java.lang.annotation.*;

/**
 * 实体类字段约束注解
 * 标有此注解的字段对应数据库中的字段名强制约束为该注解中的name值
 * @author 郭胜凯
 * @time 2016年2月26日下午3:32:40
 * @email 719348277@qq.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldName {

	String name() default PUBVALUE.FIELD_NAME_DEFAUL_VALUE;
}
