package top.javahai.confucius.frame.base.service.handler;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.javahai.confucius.frame.base.service.exception.BaseException;
import top.javahai.confucius.frame.common.result.ResultCodeEnum;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.frame.common.util.ExceptionUtils;

/**
 * @author Hai
 * @program: zhkuschool-frame
 * @description: 统一异常处理
 * @create 2020/11/15 - 18:58
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResultVO error(HttpMessageNotReadableException e){
        e.printStackTrace();
        log.error(ExceptionUtils.getMessage(e));
        return ResultVO.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public ResultVO error(BadSqlGrammarException e){
        e.printStackTrace();
        log.error(ExceptionUtils.getMessage(e));
        return ResultVO.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    public ResultVO error(ExpiredJwtException e){
        e.printStackTrace();
        log.error(ExceptionUtils.getMessage(e));
        return ResultVO.setResult(ResultCodeEnum.USER_TOKEN_EXPIRED);
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResultVO error(BaseException e){
        e.printStackTrace();
        log.error(ExceptionUtils.getMessage(e));
        return ResultVO.error(e.getMessage()).code(e.getCode());
    }



    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO error(Exception e){
        e.printStackTrace();
        log.error(ExceptionUtils.getMessage(e));
        return ResultVO.error().message(e.getMessage());
    }
}
