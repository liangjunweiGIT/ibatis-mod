package com.ljw.test.dao.impl;

import com.ljw.ibatis.common.daoImpl.IbatisQueryDaoImpl;
import com.ljw.test.dao.TestDao;
import com.ljw.test.pojo.User;

import java.util.List;

/**
 * @Description
 * @Author Create by junwei.liang on 2018/7/20
 */
public class TestQueryDaoImpl extends IbatisQueryDaoImpl implements TestDao {
    public List<User> queryUserList(User query) {
        List<User> list = null;
        list = queryForList("getAllUsers", query);
        return list;
    }

    public User queryUserById(Long id) {
        return (User) queryForObject("getUserById",id);
    }

    public void testVoid() {
        queryForObject("getUserById",1);
    }
}
