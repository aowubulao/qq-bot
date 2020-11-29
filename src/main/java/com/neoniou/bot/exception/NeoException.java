package com.neoniou.bot.exception;

import lombok.Getter;

/**
 * @author neo.zzj
 */
@Getter
public class NeoException extends RuntimeException{

    private ExceptionResult exceptionResult;

    public NeoException() {
    }

    public NeoException(ExceptionResult exceptionResult) {
        this.exceptionResult = exceptionResult;
    }
}