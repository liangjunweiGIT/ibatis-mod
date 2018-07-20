package com.ibatis.com.ljw.ibatis.common.daoImpl;

import com.ibatis.com.ljw.ibatis.common.statusparam.ResultClassVariable;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

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
public class IbatisDaoImpl {
    private static SqlMapClient sqlMapClient;

    static {
        String resource = "ibatisConfig.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
    }

    public static SqlMapClient getSqlMapClient() throws Exception {
        return sqlMapClient;
    }

    public List queryForList(String id, Object paramObject) {
        List list = null;
        setReturnType();
        try {
            list = sqlMapClient.queryForList(id, paramObject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取调用方法的返回类型，
     * 要求：1.调用类的方法不可重载 2.返回值使用泛型时只允许一个泛型
     */
    private void setReturnType() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        //直接选出是第三个方法（方法结构改变之后需重新判断，或者直接打开注释使用下面的循环自动判断）
        StackTraceElement log = stackTrace[3];
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
        Method[] methods = this.getClass().getMethods();
        for (Method method : methods) {
            if(method.toString().contains(tag)){
                Type type = method.getGenericReturnType();// 获取返回值类型
                if (type instanceof ParameterizedType) { // 判断获取的类型是否是参数类型
                    Type[] types = ((ParameterizedType) type).getActualTypeArguments();// 强制转型为带参数的泛型类型，
                    // getActualTypeArguments()方法获取类型中的实际类型，如map<String,Integer>中的
                    // 在Dao中泛型不应该使用是多个，所以不使用数组
                    try {
                        ResultClassVariable.setResultClass(Class.forName(types[0].toString().replace("class ","")));
                        return;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                ResultClassVariable.setResultClass(method.getReturnType());
                return;
            }
        }
        ResultClassVariable.setResultClass(null);
    }
}
