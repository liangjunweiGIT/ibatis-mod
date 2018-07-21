package com.ljw.ibatis.common.daoImpl;

import com.ibatis.common.resources.Resources;
import com.ibatis.common.util.PaginatedList;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.mapping.result.ResultMap;
import com.ljw.ibatis.common.pojo.InitialResultClass;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description
 * @Author Create by junwei.liang on 2018/7/20
 */
public class IbatisQueryDaoImpl {
    private static final Logger LOGGER = LogManager.getLogger(IbatisQueryDaoImpl.class.getName());

    private static SqlMapClientImpl sqlMapClient;

    static {
        String resource = "ibatisConfig.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlMapClient = (SqlMapClientImpl) SqlMapClientBuilder.buildSqlMapClient(reader);
    }

    public static SqlMapClient getSqlMapClient() throws Exception {
        return sqlMapClient;
    }

    public Object queryForObject(String id, Object paramObject) {
        Object object = null;
        checkeAndSetResutlClass(id);
        try {
            object = sqlMapClient.queryForObject(id, paramObject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    public Object queryForObject(String id) {
        return queryForObject(id,null);
    }

    public Object queryForObject(String id, Object paramObject, Object resultObject) throws SQLException {
        return sqlMapClient.queryForObject(id, paramObject, resultObject);
    }

    public List queryForList(String id, Object paramObject) {
        List list = null;
        checkeAndSetResutlClass(id);
        try {
            list = sqlMapClient.queryForList(id, paramObject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List queryForList(String id) {
        return queryForList(id,null);
    }

    public List queryForList(String id, Object paramObject, int skip, int max) {
        List list = null;
        checkeAndSetResutlClass(id);
        try {
            list = sqlMapClient.queryForList(id, paramObject, skip, max);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List queryForList(String id, int skip, int max) {
        return queryForList(id, null, skip, max);
    }

    /**
     * @deprecated All paginated list features have been deprecated
     */
    public PaginatedList queryForPaginatedList(String id, Object paramObject, int pageSize) {
        PaginatedList paginatedList = null;
        checkeAndSetResutlClass(id);
        try {
            paginatedList = sqlMapClient.queryForPaginatedList(id, paramObject, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paginatedList;
    }

    /**
     * @deprecated All paginated list features have been deprecated
     */
    public PaginatedList queryForPaginatedList(String id, int pageSize) {
        return queryForPaginatedList(id, null, pageSize);
    }

    /**
     * 判断是否有sql中是否存在ResultClass，如果不存在则赋值
     * @param sqlId
     */
    private void checkeAndSetResutlClass(String sqlId){
        //TODO ljw 如果resultClass还是初始值则使用反射得到的类型给resultClass赋值
        ResultMap resultMap = sqlMapClient.delegate.getMappedStatement(sqlId).getResultMap();
        if (InitialResultClass.class.equals(resultMap.getResultClass())) {
            resultMap.setResultClass(getReturnType());
        }
    }

    /**
     * 获取调用方法的返回类型，
     * 要求：1.调用类的方法不可重载 2.返回值使用泛型时只允许一个泛型
     */
    private Class getReturnType() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        //直接选出是第三个方法（方法结构改变之后需重新判断，或者直接打开注释使用下面的循环自动判断）
        StackTraceElement log = stackTrace[4];
        String tag ;

        /*for (int i = 1; i < stackTrace.length; i++) {
            StackTraceElement e = stackTrace[i];
            if (!e.getClassName().equals(log.getClassName())) {
                tag = e.getClassName() + "." + e.getMethodName();
                break;
            }
        }
        if (tag == null) {
            tag = log.getClassName() + "." + log.getMethodName();

        }*/
        tag = log.getClassName() + "." + log.getMethodName();

        // return class and super class methods
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if(method.toString().contains(tag)){
                Type type = method.getGenericReturnType();// 获取返回值类型
                if (type instanceof ParameterizedType) { // 判断获取的类型是否是参数类型
                    Type[] types = ((ParameterizedType) type).getActualTypeArguments();// 强制转型为带参数的泛型类型，
                    // getActualTypeArguments()方法获取类型中的实际类型，如map<String,Integer>中的
                    // 在Dao中泛型不应该使用是多个，所以不使用数组
                    try {
                        return Class.forName(types[0].toString().replace("class ",""));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                if(method.getReturnType().equals(void.class)){
                    LOGGER.error("请在sql.xml中添加设置正确的resultClass，或者将方法返回值类型设置为结果类型");
                    return Object.class;
                }
                return method.getReturnType();
            }
        }
        return null;
    }
}
