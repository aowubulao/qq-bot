package com.neoniou.bot.mirai.pojo;

import com.neoniou.bot.mirai.handler.MessageHandler;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Neo.Zzj
 * @date 2020/12/4
 */
@Data
public class Rule implements Serializable {

    /**
     * 匹配的字符串
     */
    private String str;

    /**
     * 要发送的消息
     */
    private String sendMessage;

    /**
     * 使用的 handler
     */
    private MessageHandler messageHandler;
}
