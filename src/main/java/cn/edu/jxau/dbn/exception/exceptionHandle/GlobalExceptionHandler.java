package cn.edu.jxau.dbn.exception.exceptionHandle;

import cn.edu.jxau.dbn.exception.BaseException;
import cn.edu.jxau.dbn.util.JsonResult;
import cn.edu.jxau.dbn.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

/**
 * @Author vector
 * @Since 1.0.0
 * @Date 2019/10/25 13:54
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value = { ConstraintViolationException.class})
    public JsonResult handleException(Exception e) {
        logger.info(e.toString());
        return new JsonResult<>(e.getMessage(), ResultCode.FAIL.getCode(),false);
    }


    @ExceptionHandler(value = { SQLException.class})
    public JsonResult handleSQLException(Exception e) {
        logger.info(e.toString());
        return new JsonResult<>(e.getMessage(), ResultCode.FAIL.getCode(),false);
    }

    @ExceptionHandler(value = { BaseException.class})
    public JsonResult handleBaseException(Exception e) {
        logger.info(e.toString());
        return new JsonResult<>(e.getMessage(), ResultCode.FAIL.getCode(),false);
    }
}
