package com.ljw.ibatis.common.exception;

/**
 * @Description
 * @Author Create by junwei.liang on 2018/7/23
 */
public class SqlXmlRuntimeException extends RuntimeException{

    private static final long serialVersionUID = -1026406778301754346L;

    public SqlXmlRuntimeException(){
        super();
    }

    public SqlXmlRuntimeException(String message){
        super(message);
    }

    public SqlXmlRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlXmlRuntimeException(Throwable cause) {
        super(cause);
    }
}
