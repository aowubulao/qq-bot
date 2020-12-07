package com.neoniou.bot.mirai.handler.impl;

import com.neoniou.bot.mirai.handler.MessageHandler;
import net.mamoe.mirai.message.GroupMessageEvent;

import java.io.Serializable;

/**
 * @author Neo.Zzj
 * @date 2020/12/6
 */
public class SimpleMessageHandler implements MessageHandler, Serializable {

    @Override
    public void handleMessage(GroupMessageEvent event, String sendMessage) {
        event.getGroup().sendMessage(sendMessage);
    }

}
