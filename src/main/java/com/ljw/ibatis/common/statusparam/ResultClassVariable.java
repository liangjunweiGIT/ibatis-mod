package com.ljw.ibatis.common.statusparam;

/**
 * @Description
 * @Author Create by junwei.liang on 2018/7/20
 */
public class ResultClassVariable {
    public static Class<?> resultClass = null;

    public static Class<?> getResultClass() {
        return resultClass;
    }

    public static void setResultClass(Class<?> resultClass) {
        ResultClassVariable.resultClass = resultClass;
    }
}
