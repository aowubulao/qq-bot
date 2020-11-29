package com.neoniou.bot.exception;

import com.neoniou.bot.constant.HttpCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author neo.zzj
 */
@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    /**
     * NeoException 异常处理
     *
     * @param e NeoException
     * @return ResponseEntity
     */
    @ExceptionHandler(NeoException.class)
    public ResponseEntity<ExceptionResult> handleException(NeoException e) {
        return ResponseEntity.status(e.getExceptionResult().getStatus()).body(e.getExceptionResult());
    }

    /**
     * 全局异常处理，返回 ExceptionResult
     *
     * @param e Exception
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResult> handleException(Exception e) {
        return ResponseEntity.status(HttpCode.SERVER_ERROR).body(new ExceptionResult(HttpCode.SERVER_ERROR, e.getMessage()));
    }
}
