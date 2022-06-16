package com.msc.neo4jfirst.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mSc
 * @version 1.0
 * @Package com.msc.neo4jfirst.common
 * @Description: 统一数据格式返回
 */
@Data
@Accessors(chain = true)
public class Result {
    private int code;
    private String msg;
    private Long count; // 返回数据的条数
    private Object data; // 数据
    private Map<String, Object> hashData = new HashMap<>();

    public static Result ok() {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(ResultCode.ERROR);
        return result;
    }

    public Result data(Object obj) {
        this.setData(obj);
        return this;
    }

    public Result hashData(String key, Object value) {
        this.hashData.put(key, value);
        return this;
    }

    public Result count(Long count) {
        this.setCount(count);
        return this;
    }

    public Result msg(String msg) {
        this.setMsg(msg);
        return this;
    }
}
