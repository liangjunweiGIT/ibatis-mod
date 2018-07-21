package com.ljw.test.dao;

import com.ljw.test.pojo.User;

import java.util.List;

/**
 * @Description
 * @Author Create by junwei.liang on 2018/7/20
 */
public interface TestDao {
    List<User> queryUserList(User query);

    User queryUserById(Long id);

    void testVoid();
}
