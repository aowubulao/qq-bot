package com.neoniou.bot.mirai.handler.impl;

import com.neoniou.bot.mirai.handler.MessageHandler;
import net.mamoe.mirai.message.GroupMessageEvent;

/**
 * @author Neo.Zzj
 * @date 2020/12/6
 */
public class SimpleMessageHandler implements MessageHandler {

    @Override
    public void handleMessage(GroupMessageEvent event, String sendMessage) {
        event.getGroup().sendMessage(sendMessage);
    }

}
