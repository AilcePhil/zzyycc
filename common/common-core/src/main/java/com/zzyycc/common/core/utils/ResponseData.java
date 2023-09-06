package com.zzyycc.common.core.utils;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author zhuyuechao
 * @version 1.0.0
 * @className ResponseData
 * @createTime 2022/2/25 13:57
 * @description
 */
public class ResponseData<T> implements Serializable {
    private static final long serialVersionUID = -4397576456787945649L;

    @ApiModelProperty(value = "数据")
    private T rows;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "是否成功")
    private Boolean success = true;


    public static <T> ResponseData<T> success() {
        return responseData(null, true, null);
    }

    public static <T> ResponseData<T> success(T rows) {
        return responseData(rows, true, null);
    }

    public static <T> ResponseData<T> success(T rows, String message) {
        return responseData(rows, true, message);
    }

    public static <T> ResponseData<T> fail() {
        return responseData(null, false, null);
    }

    public static <T> ResponseData<T> fail(T rows) {
        return responseData(rows, false, null);
    }

    public static <T> ResponseData<T> fail(T rows, String message) {
        return responseData(rows, false, message);
    }


    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    private static <T> ResponseData<T> responseData(T rows, Boolean success, String message) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setRows(rows);
        responseData.setSuccess(success);
        responseData.setMessage(message);
        return responseData;
    }
}
