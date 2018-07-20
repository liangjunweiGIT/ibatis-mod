package com.ibatis.com.ljw.test;

import com.ibatis.com.ljw.test.dao.TestDao;
import com.ibatis.com.ljw.test.dao.impl.TestDaoImpl;
import com.ibatis.com.ljw.test.pojo.User;
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
        TestDao testDao = new TestDaoImpl();
        User query = new User();
        List<User> list = testDao.queryUserList(query);
        System.out.println("Selected " + list.size() + " records.");
        for (User user : list) {
            System.out.println(user);
        }
        /*List<User> list2 = testDao.queryUserList(query);
        System.out.println("Selected " + list2.size() + " records.");
        for (User user : list2) {
            System.out.println(user);
        }*/
    }
}
