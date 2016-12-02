package cn.no7player.dao.base.po;

import java.lang.annotation.*;

/**
 * ��Service�����������־
 * @author AnLuTong
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
	String action() default "null";
	
}
