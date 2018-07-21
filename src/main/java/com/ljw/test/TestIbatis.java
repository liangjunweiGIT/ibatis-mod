package com.ljw.test;

import com.ljw.test.dao.TestDao;
import com.ljw.test.dao.impl.TestQueryDaoImpl;
import com.ljw.test.pojo.User;

import java.util.List;

/**
 * @Description
 * @Author Create by junwei.liang on 2018/7/20
 */
public class TestIbatis {
    public static void main(String arg[]) throws Exception {
        TestDao testDao = new TestQueryDaoImpl();
        User query = new User();
        List<User> list = testDao.queryUserList(query);
        System.out.println("----queryUserList-------");
        System.out.println("Selected " + list.size() + " records.");
        for (User user : list) {
            System.out.println(user);
        }

        User user = testDao.queryUserById(1L);
        System.out.println("----queryUserById-------");
        System.out.println(user);

    }
}
