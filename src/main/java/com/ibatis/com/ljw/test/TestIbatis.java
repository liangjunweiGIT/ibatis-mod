package com.ibatis.com.ljw.test;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.Reader;
import java.util.List;

/**
 * @Description
 * @Author Create by junwei.liang on 2018/7/20
 */
public class TestIbatis {
    public static void main(String arg[]) throws Exception {
        String resource = "ibatisConfig.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
        List list = sqlMap.queryForList("getAllUsers", "1");
        System.out.println("Selected " + list.size() + " records.");
        for (Object aList : list) {
            System.out.println(aList);
        }
    }
}
