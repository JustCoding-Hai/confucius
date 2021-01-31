package top.javahai.confucius.frame.base.service.exception;


import top.javahai.confucius.frame.common.result.ResultCodeEnum;

/**
 * @author Hai
 * @program: zhkuschool-frame
 * @description: 自定义异常基类
 * @create 2021/1/11 - 11:42
 **/
public class BaseException extends RuntimeException {

    /**
     * 状态码
     */
    private Integer code;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 接收异常信息和状态码
     * @param code
     * @param message
     */
    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型
     * @param resultCodeEnum
     */
    public BaseException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code=resultCodeEnum.getCode();
    }
}
