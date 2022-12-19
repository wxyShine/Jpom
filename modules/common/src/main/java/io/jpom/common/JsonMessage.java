/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Code Technology Studio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.jpom.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 通用的json 数据格式
 *
 * @author jiangzeyin
 * @since 2017/2/6.
 * @since 1.0
 */
@Data
@NoArgsConstructor
public class JsonMessage<T> implements Serializable {
    public static final String CODE = "code";
    public static final String MSG = "msg";
    public static final String DATA = "data";

    public static int DEFAULT_SUCCESS_CODE = 200;

    static {
        // long 类型自动转String
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(long.class, ToStringSerializer.instance);
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
    }

    private int code;
    private String msg;
    private T data;


    public JsonMessage(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JsonMessage(int code, String msg) {
        this(code, msg, null);
    }

    /**
     * 将data 转换为对应实体
     *
     * @param tClass 类
     * @param <E>    泛型
     * @return Object
     */
    public <E> E getData(Class<E> tClass) {
        if (data == null) {
            return null;
        }
        if (tClass.isAssignableFrom(data.getClass())) {
            return (E) data;
        }
        return JSONObject.parseObject(data.toString(), tClass);
    }

    /**
     * @return json
     * @author jiangzeyin
     */
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public JSONObject toJson() {
        return (JSONObject) JSONObject.toJSON(this);
    }

    public static JSONObject toJson(int code, String msg) {
        return toJson(code, msg, null);
    }

    public static JSONObject toJson(int code, String msg, Object data) {
        JsonMessage<Object> jsonMessage = new JsonMessage<>(code, msg, data);
        return jsonMessage.toJson();
    }

    /**
     * @param code code
     * @param msg  msg
     * @return json
     * @author jiangzeyin
     */
    public static String getString(int code, String msg) {
        return getString(code, msg, null);
    }

    public static <T> JsonMessage<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> JsonMessage<T> success(String msg, T data) {
        return new JsonMessage<>(DEFAULT_SUCCESS_CODE, msg, data);
    }

    /**
     * @param code code
     * @param msg  msg
     * @param data data
     * @return json
     * @author jiangzeyin
     */
    public static String getString(int code, String msg, Object data) {
        return toJson(code, msg, data).toString();
    }
}