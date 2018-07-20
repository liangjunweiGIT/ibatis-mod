package com.ibatis.com.ljw.test.util;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @Description
 * @Author Create by junwei.liang on 2018/7/20
 */
public class SqlUtil {
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
}
