package com.neoniou.bot.exception;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import java.util.Date;

/**
 * @author neo.zzj
 */
@Data
public class ExceptionResult {

    private int status;
    private String message;
    private String timestamp;

    public ExceptionResult(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new DateTime(new Date()).toString();
    }
}