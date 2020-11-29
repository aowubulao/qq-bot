package com.neoniou.bot.mirai.core;

import net.mamoe.mirai.message.GroupMessageEvent;

/**
 * @author Neo.Zzj
 * @date 2020/11/29
 */
public class MessageHandler {

    public static final String SUB_STR = "]";

    public static void handleMessage(GroupMessageEvent event) {
        String msgString = event.getMessage().toString();
        String msgBody = msgString.substring(msgString.indexOf(SUB_STR) + 1);
    }
}
