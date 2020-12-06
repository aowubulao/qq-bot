package com.neoniou.bot.mirai.handler;

import net.mamoe.mirai.message.GroupMessageEvent;

/**
 * @author Neo.Zzj
 * @date 2020/12/4
 */
public interface MessageHandler {

    /**
     * 接收群消息，发送消息
     *
     * @param event GroupMessageEvent
     * @param sendMessage 发送的消息串
     */
    void handleMessage(GroupMessageEvent event, String sendMessage);
}
