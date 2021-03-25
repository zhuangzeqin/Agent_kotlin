package com.eeepay.zzq.lambda.kt;

import java.io.Serializable;

/**
 * 描述：抽象出后台接口返回的数据格式
 * {
 * "code": 200,
 * "message": "",
 * "data": {
 * "userName": "前海移联直营",
 * "password": null,
 * "agentOem": null,
 * "loginToken": "9fbcde8d-68c0-49a0-9b6d-f2c370cf0bd2",
 * "userId": "1000000000000002897",
 * "agentNo": "1446",
 * "agentNode": "0-1446-",
 * "agentName": "E前海移联直营read"
 * },
 * "count": 0,
 * "success": true
 * }
 * 作者：zhuangzeqin
 * 时间: 2019年5月15日11:21:11
 * 邮箱：zzq@eeepay.cn
 */
public class Result<T> implements Serializable {
    public int code;// 标识码
    public String message;//错误提示语
    public T data;//泛型T 数据
    public int count;//数据数量
    public boolean success;//是否成功； true 代表成功； false 代表不成功


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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", count=" + count +
                ", success=" + success +
                '}';
    }
}
