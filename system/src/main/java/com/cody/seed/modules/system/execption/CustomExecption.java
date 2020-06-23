package com.cody.seed.modules.system.execption;

/**
 * @Description: 自定义业务异常
 * @date: 2020年06月16日 16:52
 */
public class CustomExecption extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    public CustomExecption(String message, String code) {
        super(message);
        this.code = code;
    }

    public CustomExecption(String message) {
        super(message);
    }

    public CustomExecption(Throwable cause) {
        super(cause);
    }

    public CustomExecption(String message, Throwable cause) {
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
