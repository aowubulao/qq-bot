package com.neoniou.bot.mvc.pojo;

import lombok.Data;

/**
 * @author Neo.Zzj
 * @date 2020/12/8
 */
@Data
public class SendMessage {

    private Long id;

    private String message;

    private int recallTime;
}
