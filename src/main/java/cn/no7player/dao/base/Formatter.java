package cn.no7player.dao.base;

import cn.no7player.dao.base.bean.FmtParm;
import cn.no7player.dao.base.bean.Po;
import cn.no7player.dao.base.bean.WherePrams;

import java.util.List;


/**
 * 格式化查询工具类
 * @author 郭胜凯
 * @time 2016年6月14日下午4:19:47
 * @email 719348277@qq.com
 *
 */
public interface Formatter {

	/**
	 * 添加字段格式化
	 * @param fieldName 实体类中的字段
	 * @param select 关联表中的真实值得字段
	 * @param po 关联表对应的实体类
	 * @param where 关联条件
	 */
	void addFmt(String fieldName, String select, Class<? extends Po> po, WherePrams where);
	
	/**
	 * 添加字段格式化
	 * @param parm 格式化条件对象
	 */
	void addFmt(FmtParm parm);
	
	/**
	 * 获取格式化的条件
	 * @return
	 */
	List<FmtParm> listFmtParm();
}
