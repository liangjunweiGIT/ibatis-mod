package com.ibatis.com.ljw.test.dao.impl;

import com.ibatis.com.ljw.ibatis.common.daoImpl.IbatisDaoImpl;
import com.ibatis.com.ljw.test.dao.TestDao;
import com.ibatis.com.ljw.test.pojo.User;
import com.ibatis.com.ljw.test.util.SqlUtil;

import java.util.List;

/**
 * @Description
 * @Author Create by junwei.liang on 2018/7/20
 */
public class TestDaoImpl extends IbatisDaoImpl implements TestDao {
    public List<User> queryUserList(User query) {
        List<User> list = null;
        list = queryForList("getAllUsers", query);
        return list;
    }
}
