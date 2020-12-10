package com.neoniou.bot.utils;

import net.mamoe.mirai.message.GroupMessageEvent;

/**
 * @author Neo.Zzj
 * @date 2020/12/10
 */
public class HandlerUtil {

    private static final String SUB_STR = "]";

    public static String getMessageBody(GroupMessageEvent event) {
        String msgString = event.getMessage().toString();
        return msgString.substring(msgString.indexOf(SUB_STR) + 1);
    }
}
