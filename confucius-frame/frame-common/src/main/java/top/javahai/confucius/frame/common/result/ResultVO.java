package top.javahai.confucius.frame.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author helen
 * @since 2019/12/25
 */
@Data
@ApiModel(value = "ResultVO",description = "全局统一返回结果")
public class ResultVO<E> {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private E data;

    public ResultVO(){}


    public ResultVO(Boolean success, Integer code, String message, E data){
        this.setSuccess(success);
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public ResultVO(Boolean success, Integer code, String message){
        this.setSuccess(success);
        this.setCode(code);
        this.setMessage(message);
    }


    public static ResultVO ok(){
        ResultVO resultVO = new ResultVO();
        resultVO.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        resultVO.setCode(ResultCodeEnum.SUCCESS.getCode());
        return resultVO;
    }
    public static ResultVO ok(String message){
        return new ResultVO(ResultCodeEnum.SUCCESS.getSuccess(),ResultCodeEnum.SUCCESS.getCode(),message);
    }
    public static ResultVO error(){
        ResultVO resultVO = new ResultVO();
        resultVO.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
        resultVO.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        return resultVO;
    }

    public static ResultVO error(String message) {
        return new ResultVO(ResultCodeEnum.UNKNOWN_REASON.getSuccess(),ResultCodeEnum.UNKNOWN_REASON.getCode(),message);
    }

     public static ResultVO setResult(ResultCodeEnum resultCodeEnum){
        ResultVO resultVO = new ResultVO();
        resultVO.setSuccess(resultCodeEnum.getSuccess());
        resultVO.setCode(resultCodeEnum.getCode());
        resultVO.setMessage(resultCodeEnum.getMessage());
        return resultVO;
    }

    public ResultVO<E> success(){
        this.setCode(ResultCodeEnum.SUCCESS.getCode());
        this.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        return this;
    }

    public ResultVO<E> fail(){
        this.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        this.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
        return this;
    }

    public ResultVO<E> message(String message){
        this.setMessage(message);
        return this;
    }

    public ResultVO<E> code(Integer code){
        this.setCode(code);
        return this;
    }


    public ResultVO<E> data(E data){
        this.setData(data);
        return this;
    }



}
