package cn.no7player.dao.base;

import cn.no7player.dao.base.bean.*;
import cn.no7player.dao.base.po.FieldName;
import cn.no7player.dao.base.sql.where.C;
import cn.no7player.dao.base.sql.where.SqlUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Faith on 2016/12/1.
 */
public abstract class  BaseDao <T extends Po, PK extends Serializable>{
    @Autowired
    protected SqlSessionTemplate sqlSession;


    protected Logger logger = LoggerFactory.getLogger(this.getClass());



    private Class<T> entityClass;

    private String pkName;					//ʵ�����������

    private String idName;					//ʵ����ID�ֶ����

    private String seq;						//���������

    private String tableName;

    private List<Pram> sqlParms;


    private List<Pram> selectSqlParms;

    private SqlUtil<T> sqlUtil;

    @SuppressWarnings("unchecked")
    public BaseDao(){
        super();

        this.sqlUtil = new SqlUtil<T>();

        this.entityClass = (Class<T>) GenericsUtils.getSuperClassGenricType(this.getClass());

        this.sqlParms = this.sqlUtil.getPramList(this.entityClass);

        this.selectSqlParms = this.sqlUtil.getPramListOfSelect(this.entityClass);

        this.tableName = this.sqlUtil.getTableName(this.entityClass);

        this.pkName = "id";

        this.idName = "id";

        this.seq = "id";

    }


    public int addLocal(T po) {
        // TODO Auto-generated method stub

        String sql = "insert into " + tableName + " (";
        String prams = "";
        String values = "";

        List<Pram> pramList = SqlUtil.getPramListofStatic(po);

        int index = 0;
        for (int i = 0; i < pramList.size(); i++) {
            if (pramList.get(i).getValue() == null || (pramList.get(i).getValue() + "") .equals("0")) {
                continue;
            }else{
                if(index > 0){
                    prams += ",";
                    values += ",";
                }
                prams += pramList.get(i).getFile();
                Object value = pramList.get(i).getValue();
                if (value instanceof byte[] ) {
                    values += "'" + new String((byte[]) value) + "'";
                }else if(value instanceof Boolean){
                    values += "'" + ((boolean)value == true ? 1 : 0) + "'";
                }else{
                    values += "'" + value + "'";
                }

                index ++;
            }
        }
        sql += prams + ") value (" + values +");";

//        SqlUtil.setFileValue(po, "ID", nextId());

        logger.debug(sql);
        return sqlSession.insert("addLocal", sql);

    }

    public int add(T po) {
        // TODO Auto-generated method stub
        String sql = "insert into " + tableName + " (";
        String prams = "";
        String values = "";

        List<Pram> pramList = SqlUtil.getPramListofStatic(po);

        for (int i = 0; i < pramList.size(); i++) {
            prams += pramList.get(i).getFile();
            if (pramList.get(i).getValue() == null) {
                values += "null";
            }else if(pramList.get(i).getValue() instanceof Boolean){
                values += "'" + ((boolean)pramList.get(i).getValue() == true ? 1 : 0) + "'";
            }else{
                values += "'" + pramList.get(i).getValue() + "'";
            }

            if(i < pramList.size() -1){
                prams += ",";
                values += ",";
            }
        }
        sql += prams + ") value (" + values +");";

//        SqlUtil.setFileValue(po, "ID", nextId());

        return sqlSession.insert("add", sql);
    }


	/*@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public int add(List<T> pos) {
		// TODO Auto-generated method stub

		int insert = 0;						//insert结果

		int[] size = {0};					//sql缓冲区大小

		//初始化Sql缓冲区容器
		if (pos.size() < 50) {				//50为一组
			size = new int[]{pos.size()};
		}else{

			int length = pos.size() / 50;
			int temp = pos.size() % 50;

			if (temp > 0) {
				length ++;

				size = new int[length];
				for (int i = 0; i < size.length; i++) {
					size[i] = 50;
				}
				size[length - 1] = temp;
			}else{

				size = new int[length];

				for (int i = 0; i < size.length; i++) {
					size[i] = 50;
				}
			}

		}


		//填充SQL缓冲

		int index = 0;				//List的迭代索引

		long idIntex = nextId();

		for (int i : size) {
			String sql = "";

			for (int j = 0; j < i; j++) {
				sql += "insert into " + tableName + " (";
				String prams = "";
				String values = "";

				List<Pram> pramList = SqlUtil.getPramListofStatic(pos.get(index));

				for (int k = 0; k < pramList.size(); k++) {
					prams += pramList.get(k).getFile();
					if (pramList.get(k).getValue() == null) {
						values += "null";
					}else if(pramList.get(k).getValue() instanceof Boolean){
						values += "'" + ((boolean)pramList.get(i).getValue() == true ? 1 : 0) + "'";
					}else{
						values += "'" + pramList.get(k).getValue() + "'";
					}

					if(k < pramList.size() -1){
						prams += ",";
						values += ",";
					}
				}
				sql += prams + ") value (" + values +")";

				SqlUtil.setFileValue(pos.get(index), "id", idIntex + index);

				if (index < (pos.size() - 1)) {
					sql += ";";
					index ++;
				}


			}

			//填充完毕后执行一次缓冲区中的sql
			insert += sqlSession.insert("add", sql);

		}

		return insert;
	}*/

    public T get(PK id) {
        // TODO Auto-generated method stub
        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if(i < selectSqlParms.size() -1){
                sql += ",";
            }else{
                sql += " ";
            }
        }
        sql += " from " + tableName + " where id='" + id + "';";
        Map<String, Object> resultMap = sqlSession.selectOne(
                "getById", sql);

        return handleResult(resultMap, this.entityClass);
    }

    public Serializable getField(PK id, String fileName) {
        // TODO Auto-generated method stub
        String field = fileName;
        String tabField = "";
        Field f = sqlUtil.getField(this.entityClass, fileName);
        if (null == f) {
            logger.error("查询字段失败(无法找到" + this.entityClass + "中的" + fileName + "字段)");
        }
        FieldName annotation = f.getAnnotation(FieldName.class);
        if (null == annotation) {
            tabField = sqlUtil.toTableString(fileName) + " as " + fileName;
        }else{
            tabField = annotation.name() + " as " + fileName;
        }

        String sql = "select ";
        sql += tabField + " from " + tableName + " where id='" + id + "';";
        Map<String, Object> resultMap = sqlSession.selectOne(
                "getFieldById", sql);
        return (Serializable) resultMap.get(fileName);
    }

    public T get(WherePrams where) {
        // TODO Auto-generated method stub
        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if(i < selectSqlParms.size() -1){
                sql += ",";
            }else{
                sql += " ";
            }
        }
        sql += "from " + tableName + where.getWherePrams() + ";";

        Map<String, Object> resultMap = sqlSession.selectOne(
                "getByParm", sql);

        return handleResult(resultMap, this.entityClass);
    }

    public Serializable getFile(WherePrams where, String fileName) {
        // TODO Auto-generated method stub
        String field = fileName;
        String tabField = "";
        Field f = sqlUtil.getField(this.entityClass, fileName);
        if (null == f) {
            logger.error("查询字段失败(无法找到" + this.entityClass + "中的" + fileName + "字段)");
        }
        FieldName annotation = f.getAnnotation(FieldName.class);
        if (null == annotation) {
            tabField = sqlUtil.toTableString(fileName) + " as " + fileName;
        }else{
            tabField = annotation.name() + " as " + fileName;
        }

        String sql = "select ";
        sql += tabField + " from " + tableName + where.getWherePrams() + ";";
        Map<String, Object> resultMap = sqlSession.selectOne(
                "getFieldByParm", sql);
        return (Serializable) resultMap.get(fileName);
    }

    public List<T> list(WherePrams where) {
        // TODO Auto-generated method stub

        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if(i < selectSqlParms.size() -1){
                sql += ",";
            }else{
                sql += " ";
            }
        }
        sql += "from " + tableName + where.getWherePrams() + ";";

        List<Map<String, Object>> selectList = sqlSession.selectList("selectList", sql);

        List<T> list = new ArrayList<>();
        for (Map<String, Object> map : selectList) {
            T t = handleResult(map, this.entityClass);
            list.add(t);
        }

        return list;

    }

    public Serializable[] listFile(WherePrams where, String fileName) {
        // TODO Auto-generated method stub

        String field = fileName;
        String tabField = "";
        Field f = sqlUtil.getField(this.entityClass, fileName);
        if (null == f) {
            logger.error("查询指定字段集失败(无法序列化" + this.entityClass + "中的" + fileName + "字段)");
        }
        FieldName annotation = f.getAnnotation(FieldName.class);
        if (null == annotation) {
            tabField = sqlUtil.toTableString(fileName) + " as " + fileName;
        }else{
            tabField = annotation.name() + " as " + fileName;
        }

        String sql = "select ";
        sql += tabField + " from " + tableName + where.getWherePrams() + ";";
        List<Map<String, Object>> resultMap = sqlSession.selectList("selectListField", sql);

        Serializable[] fields = new Serializable[resultMap.size()];

        for (int i = 0; i < resultMap.size(); i++) {
            if (null != resultMap.get(i)) {
                fields[i] =(Serializable) resultMap.get(i).get(fileName);
            }
        }

        return fields;
    }

    public List<Map<String, Serializable>> listFiles(WherePrams where, String[] files) {
        // TODO Auto-generated method stub
        String tabField = "";
        int index = 1;
        for (String field : files) {
            Field f = sqlUtil.getField(this.entityClass, field);
            if (null == f) {
                logger.error("查询指定字段集失败(无法序列化" + this.entityClass + "中的" + field + "字段)");
            }
            FieldName annotation = f.getAnnotation(FieldName.class);
            if (null == annotation) {
                tabField += sqlUtil.toTableString(field) + " as " + field;
            }else{
                tabField += annotation.name() + " as " + field;
            }
            if (index < files.length) {
                tabField += ",";
            }

            index ++;
        }

        String sql = "select ";
        sql += tabField + " from " + tableName + where.getWherePrams() + ";";
        List<Map<String, Serializable>> resultMap = sqlSession.selectList("selectListField", sql);

        return resultMap;
    }

    public int updateLocal(T po) {
        // TODO Auto-generated method stub

        Serializable id = sqlUtil.getFileValue(po, "id");

        if(null == id){
            return 0;
        }
        String sql = "update " + tableName + " set ";
        List<Pram> prams = sqlUtil.getPramList(po);
        for (int i = 0; i < prams.size(); i++) {
            if(null != prams.get(i).getValue()){
                sql += prams.get(i).getFile() + "=";
                Object value = prams.get(i).getValue();
                if (value instanceof byte[] ) {
                    sql += "'" + new String((byte[]) value) + "'";
                }else if(value instanceof Boolean){
                    sql += "'" + ((boolean)value == true ? 1 : 0) + "'";
                }else{
                    sql += "'" + value + "'";
                }

//				sql += prams.get(i).getFile() + "='" + prams.get(i).getValue() + "'";
                if (i < prams.size() -1) {
                    sql += ",";
                }
            }
        }
        sql += " where id='" + id +"';";

        return sqlSession.update("updateLocal", sql);
    }

    public int update(T po) {
        // TODO Auto-generated method stub

        Serializable id = sqlUtil.getFileValue(po, "id");

        if(null == id){
            return 0;
        }
        String sql = "update " + tableName + " set ";

        List<Pram> prams = sqlUtil.getPramList(po);

        for (int i = 0; i < prams.size(); i++) {
            if(null != prams.get(i).getValue()){
                sql += prams.get(i).getFile() + "=";
                Object value = prams.get(i).getValue();
                if (value instanceof byte[] ) {
                    sql += "'" + new String((byte[]) value) + "'";
                }else if(value instanceof Boolean){
                    sql += "'" + ((boolean)value == true ? 1 : 0) + "'";
                }else{
                    sql += "'" + value + "'";
                }
//				sql += prams.get(i).getFile() + "='" + prams.get(i).getValue() + "'";
                if (i < prams.size() -1) {
                    sql += ",";
                }
            }else{
                sql += prams.get(i).getFile() + "=null";
                if (i < prams.size() -1) {
                    sql += ",";
                }
            }
        }
        sql += " where id='" + id +"';";

        return sqlSession.update("update", sql);
    }

    public int updateLocal(T po, WherePrams where) {
        // TODO Auto-generated method stub

        String sql = "update " + tableName + " set ";
        List<Pram> prams = sqlUtil.getPramList(po);
        for (int i = 0; i < prams.size(); i++) {
            if(null != prams.get(i).getValue()){
                sql += prams.get(i).getFile() + "=";
                Object value = prams.get(i).getValue();
                if (value instanceof byte[] ) {
                    sql += "'" + new String((byte[]) value) + "'";
                }else if(value instanceof Boolean){
                    sql += "'" + ((boolean)value == true ? 1 : 0) + "'";
                }else{
                    sql += "'" + value + "'";
                }
//				sql += prams.get(i).getFile() + "='" + prams.get(i).getValue() + "'";
                if (i < prams.size() -1) {
                    sql += ",";
                }
            }
        }
        sql += where.getWherePrams() +"';";

        return sqlSession.update("updateLocalByPram", sql);

    }

    public int update(T po, WherePrams where) {
        // TODO Auto-generated method stub

        String sql = "update " + tableName + " set ";
        Object[] o = new Object[sqlParms.size()];
        for (int i = 0; i < sqlParms.size(); i++) {
            if(null != sqlParms.get(i).getValue()){
                if(sqlParms.get(i).getValue() instanceof Boolean){
                    sql += sqlParms.get(i).getFile() + "='" + (((boolean)sqlParms.get(i).getValue()) == true ? 1 : 0) + "'";
                }else{
                    sql += sqlParms.get(i).getFile() + "='" + sqlParms.get(i).getValue() + "'";
                }


                if (i < sqlParms.size() -1) {
                    sql += ",";
                }
            }else{
                sql += sqlParms.get(i).getFile() + "=null";
                if (i < sqlParms.size() -1) {
                    sql += ",";
                }
            }
        }
        sql += where.getWherePrams() + "';";

        return sqlSession.update("updateByPram", sql);

    }

    public int del(PK id) {
        // TODO Auto-generated method stub
        String sql = "delete from " + tableName + " where id=" + id;

        return sqlSession.delete("deleteById", sql);
    }

    public int del(WherePrams where) {
        // TODO Auto-generated method stub

        String sql = "delete from " + tableName + where.getWherePrams();

        return sqlSession.delete("deleteByparm", sql);
    }

    public List<Map<String, Object>> listBySql(String sql) {
        // TODO Auto-generated method stub

        List<Map<String, Object>> selectList = sqlSession.selectList("selectBySql", sql);
        return selectList;
    }

    public int excuse(String sql) {
        // TODO Auto-generated method stub
        return sqlSession.update("excuse", sql);
    }

    public long count(WherePrams where) {
        // TODO Auto-generated method stub

        String sql = "select count(*) from ";

        sql += tableName + where.getWherePrams();

        long count = sqlSession.selectOne("selectCountByParm", sql);

        return count;
    }

    public long size() {
        // TODO Auto-generated method stub
        String sql = "select count(*) from " + tableName;
        long count = sqlSession.selectOne("selectCount", sql);
        return count;
    }

    public boolean isExist(T po) {
        // TODO Auto-generated method stub
        WherePrams wherePrams = Method.createDefault();

        List<Pram> list = SqlUtil.getPramListofStatic(po);

        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                wherePrams = Method.where(list.get(i).getFile(), C.EQ, (Serializable)list.get(i).getValue());
            }else{
                wherePrams.and(list.get(i).getFile(), C.EQ, (Serializable)list.get(i).getValue());
            }
        }


        return count(wherePrams) > 0;
    }

    public boolean isExist(WherePrams where) {
        // TODO Auto-generated method stub
        return count(where) > 0;
    }

    public List<T> in(String fileName, Serializable[] values) {
        // TODO Auto-generated method stub

        String sql = "select ";
        for (int i = 0; i < sqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if(i < selectSqlParms.size() -1){
                sql += ",";
            }else{
                sql += " ";
            }
        }
        sql += "from " + tableName + " where " + fileName + " in ";
        String value = "(";
        for(int i = 0; i < values.length; i++){
            if(i < values.length -1){
                value += values[i] + ",";
            }else{
                value += values[i] + ");";
            }
        }
        sql += value;

        List<Map<String, Object>> selectList = sqlSession.selectList("selectIn", sql);

        List<T> list = new ArrayList<>();
        for (Map<String, Object> map : selectList) {
            T t = handleResult(map, this.entityClass);
            list.add(t);
        }

        return list;
    }

    private T handleResult(Map<String, Object> resultMap, Class<T> tClazz) {
        if (null == resultMap) {
            return null;
        }
        T t = null;
        try {
            t = tClazz.newInstance();
        } catch (InstantiationException e) {
            logger.error("/********************************");
            logger.error("实例化Bean失败(" + this.entityClass + ")!"
                    + e.getMessage());
            logger.error("/********************************");
        } catch (IllegalAccessException e) {
            logger.error("/********************************");
            logger.error("实例化Bean失败(" + this.entityClass + ")!"
                    + e.getMessage());
            logger.error("/********************************");
        }
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            String key = entry.getKey();
            Serializable val = (Serializable) entry.getValue();
            try {
                SqlUtil.setFileValue(t, key, val);
            } catch (Exception e) {
                // TODO: handle exception
                logger.error("/********************************");
                logger.error("/ʵ�л�����(" + this.entityClass + ")ʱ���ֶθ�ֵ�쳣(" + key + "):"
                        + e.getMessage());
                logger.error("/********************************");
            }
        }
        return t;
    }

    /**
     * �����һ�����е�ֵ
     */
    public long nextId(){
        String sql = "SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='" + tableName + "' AND TABLE_SCHEMA=(select database())";
        Long idVal = sqlSession.selectOne("fetchSeqNextval", sql);
        if (null == idVal) {
            logger.error("/********************************");
            logger.error("/��ȡ" + tableName + "�����һ������ֵʧ��");
            logger.error("/********************************");
            return 0;
        }
        return idVal;

    }


    public List<T> listFormat(WherePrams where, Formatter fmt) {
        // TODO Auto-generated method stub
        String sql = "SELECT ";

        String sqlTab = tableName + " as t_0";

        List<FmtParm> listFmtParm = fmt.listFmtParm();

        for (int i = 0; i < selectSqlParms.size(); i++) {

            String field = selectSqlParms.get(i).getFile();

            sql += "t_0." + field;

            if(i < selectSqlParms.size() -1){
                sql += ",";
            }else{
                sql += " ";
            }

        }


        //是需要格式化的
        int index = 1;

        //临时缓存已处理的关联表名
        List<String> tempFmt = new ArrayList<>();

        String tabWhere = "";

        for (FmtParm fmtParm : listFmtParm) {

            String tName = this.sqlUtil.getTableNameByClazz(fmtParm.getPo());

            sql += ", t_" + index + "." + fmtParm.getSelect() + " as " + fmtParm.getFieldName();

            sqlTab += ", " + tName + " as t_" + index;

            String wherePrams = fmtParm.getWhere().getWherePrams();

            Pattern p = Pattern.compile("'(\\[fmt.R.+)'");
            Matcher m = p.matcher(wherePrams);
            while (m.find()) {
                String temp = m.group();

                wherePrams = wherePrams.replace(temp, temp.replace("'", "").replace("[fmt.R", "t_" + index + ".").replace("[fmt.L", "t_" + index).replace("]", ""));
            }
            p = Pattern.compile("'(\\[fmt.L.+)'");
            m = p.matcher(wherePrams);
            while (m.find()) {
                String temp = m.group();
                wherePrams = wherePrams.replace(temp, temp.replace("'", "").replace("[fmt.R", "t_0.").replace("[fmt.L", "t_0").replace("]", ""));
            }
            wherePrams = wherePrams.replace("[fmt.R]", "t_" + index).replace("[fmt.L]", "t_0");

            if (tabWhere.length() < 1) {
                tabWhere += wherePrams;
            }else{
                tabWhere += (wherePrams.replace("where", "AND").replace("WHERE", "AND"));
            }


            //增加别名索引
            if (!isExcTab(tempFmt, tName)) {
                index ++;
            }

        }

        if (where.getWherePrams().length() < 1) {
            sql += " FROM " + sqlTab + tabWhere;
        }else{

            String leftWhere = where.getWherePrams();

            Pattern p = Pattern.compile(" (.+<|>|<>|like|LIKE|in|IN|=)");
            Matcher m = p.matcher(leftWhere);
            while (m.find()) {
                String temp = m.group().replaceAll("(WHERE|where|and|AND|or|OR) ", "").replace(" ", "");

                leftWhere = leftWhere.replace(temp, "t_0." + temp);
            }


            sql += " FROM " + sqlTab + tabWhere + (leftWhere.replace("where", "AND").replace("WHERE", "AND"));
        }

        List<Map<String, Object>> selectList = sqlSession.selectList("selectList", sql);

        List<T> list = new ArrayList<>();
        for (Map<String, Object> map : selectList) {
            T t = handleResult(map, this.entityClass);
            list.add(t);
        }

        return list;
    }

    /**
     * 是否为SQL表达符号
     * @param c
     * @return
     */
    private boolean isC(String c){
        switch (c) {
            case "=":
                return true;
            case "<":
                return true;
            case ">":
                return true;
            case "<>":
                return true;
            case "like":
                return true;
            case "in":
                return true;
            default:
                return false;
        }
    }

    /**
     * 从List<String>集合中检查是否有存在的元素
     * @param list
     * @param tabName
     * @return
     */
    private boolean isExcTab (List<String> list, String tabName){
        for (String string : list) {
            if (tabName .equals( string)) {
                return true;
            }
        }
        return false;
    }



}
