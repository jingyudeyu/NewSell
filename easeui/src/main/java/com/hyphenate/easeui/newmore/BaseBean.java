package com.hyphenate.easeui.newmore;


import java.io.Serializable;

/**
 * Created by Administrator on 2017-7-10.
 */
public class BaseBean<E> implements Serializable{

    private int code;
    private String message;
    private E data;

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
