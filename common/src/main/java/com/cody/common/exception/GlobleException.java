package com.cody.common.exception;

/**
 * @Description: 自定义业务异常
 * @date: 2020年06月16日 16:52
 */
public class GlobleException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    public GlobleException(String message, String code) {
        super(message);
        this.code = code;
    }

    public GlobleException(String message) {
        super(message);
    }

    public GlobleException(Throwable cause) {
        super(cause);
    }

    public GlobleException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "GlobleException(code=" + this.getCode() + ")";
    }

}
