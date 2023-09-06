package com.zzyycc.common.core.exception;

/**
 * @author zhuyuechao
 * @version 1.0.0
 * @className MgException
 * @createTime 2022/2/25 16:01
 * @description
 */
public class MgException extends RuntimeException{


    private static final long serialVersionUID = 1L;

    public MgException(String message) {
        super(message);
    }

    public MgException(Throwable exception) {
        super(exception);
    }

    public MgException(String message, Throwable exception) {
        super(message, exception);
    }

    public MgException(String message, Throwable exception, boolean enableSuppression, boolean writableStackTrace) {
        super(message, exception, enableSuppression, writableStackTrace);
    }
}
