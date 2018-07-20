package com.ibatis.com.ljw.test;

import com.ibatis.com.ljw.test.pojo.User;

import java.lang.reflect.*;

import java.util.*;

/**
 * @Description
 * @Author Create by junwei.liang on 2018/7/20
 */
public class ParmReturnType {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException {
        // 获取指定方法的返回值泛型信息
        Method[] method1 = ParmReturnType.class.getMethods();
        Method method = ParmReturnType.class.getMethod("test02", Integer.class);// 根据方法名和参数获取test02方法
        Type type = method.getGenericReturnType();// 获取返回值类型
        if (type instanceof ParameterizedType) { // 判断获取的类型是否是参数类型
            System.out.println(type);
            Type[] typesto = ((ParameterizedType) type).getActualTypeArguments();// 强制转型为带参数的泛型类型，
            // getActualTypeArguments()方法获取类型中的实际类型，如map<String,Integer>中的
            // String，integer因为可能是多个，所以使用数组
            for (Type type2 : typesto) {
                System.out.println("泛型类型" + type2);
            }
        }
    }

    // 带参数的方法Test01
    public static void test01(Map<String, Integer> map, List<User> list) {
    }

    // 带返回值的方法Test02
    public static List<User> test02(Integer integer) {
        return null;
    }

}