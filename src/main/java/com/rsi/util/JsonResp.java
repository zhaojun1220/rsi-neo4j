package com.rsi.util;

import org.springframework.util.Assert;

/**
 *
 * @ClassName: JsonResp
 * @Description: 通用json返回处理
 * @author: zhaojun
 * @date: 2017年8月21日 上午8:06:40
 */
public class JsonResp {

    public static final int SUCCESS_STATUS = 0;

    public static final int DEFAULT_FAIL_STATUS = -1;

    /**
     * 0-成功，其它-失败
     */
    private int errcode;

    private Object data;

    private long count;

    private String errmsg;

    public JsonResp(int errcode, Object data) {
        this.errcode = errcode;
        this.data = data;
    }

    public JsonResp(int errcode, Object data, long count) {
        this.errcode = errcode;
        this.data = data;
        this.count = count;
    }

    public JsonResp(int errcode, Object data, String errmsg) {
        this.errcode = errcode;
        this.data = data;
        this.errmsg = errmsg;
    }

    public JsonResp(int errcode, Object data, long count, String errmsg) {
        this.errcode = errcode;
        this.data = data;
        this.count = count;
        this.errmsg = errmsg;
    }

    public static JsonResp success() {
        return new JsonResp(SUCCESS_STATUS, null);
    }

    public static JsonResp success(Object data) {
        return new JsonResp(SUCCESS_STATUS, data);
    }

    public static JsonResp success(Object data, long count) {
        return new JsonResp(SUCCESS_STATUS, data, count);
    }

    public static JsonResp fail(String errmsg) {
        return fail(DEFAULT_FAIL_STATUS, errmsg);
    }

    public static JsonResp fail(int errcode, String errmsg) {
        Assert.isTrue(errcode != SUCCESS_STATUS, "Must be not success errcode: " + errcode);
        return new JsonResp(errcode, null, errmsg);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @return the count
     */
    public long getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(long count) {
        this.count = count;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
