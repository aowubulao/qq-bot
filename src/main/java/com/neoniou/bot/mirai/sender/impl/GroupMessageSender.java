package com.neoniou.bot.mirai.sender.impl;

import com.neoniou.bot.mirai.core.MiraiBot;
import com.neoniou.bot.mirai.sender.MessageSender;

/**
 * @author Neo.Zzj
 * @date 2020/12/8
 */
public class GroupMessageSender implements MessageSender {

    @Override
    public void sendMessage(long id, String message) {
        MiraiBot.getBot().getGroup(id).sendMessage(message);
    }

    @Override
    public void sendMessageRecall(long id, String message, int recallTime) {
        MiraiBot.getBot().getGroup(id).sendMessage(message).recallIn(recallTime * 1000L);
    }
}