package com.yq.passwordmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;
    private Map<String, Object> extraData;

    /**
     * @param data
     * @param message
     * @param extraData
     * @param <T>
     * @return
     * @
     */
    public static <T> Result<T> success(T data, String message, Map<String, Object> extraData) {
        return new Result<>(Constant.SUCCESS_CODE, message, data, extraData);
    }

    public static <T> Result<T> failure(T data, String message, Map<String, Object> extraData) {
        return new Result<>(Constant.FAILURE_CODE, message, data, extraData);
    }

    public static <T> Result<T> error(String message, Map<String, Object> extraData) {
        return new Result<>(Constant.ERROR_CODE, message, null, extraData);
    }
}
