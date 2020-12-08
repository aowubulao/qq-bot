package com.neoniou.bot.mvc.service.impl;

import com.neoniou.bot.mirai.sender.impl.FriendMessageSender;
import com.neoniou.bot.mirai.sender.impl.GroupMessageSender;
import com.neoniou.bot.mvc.pojo.SendMessage;
import com.neoniou.bot.mvc.service.SendMessageService;
import org.springframework.stereotype.Service;

/**
 * @author Neo.Zzj
 * @date 2020/12/8
 */
@Service
public class SendMessageServiceImpl implements SendMessageService {

    @Override
    public void sendGroupMessage(SendMessage message) {
        if (message.getRecallTime() == 0) {
            new GroupMessageSender().sendMessage(message.getId(), message.getMessage());
        } else {
            new GroupMessageSender().sendMessageRecall(message.getId(), message.getMessage(), message.getRecallTime());
        }
    }

    @Override
    public void sendFriendMessage(SendMessage message) {
        if (message.getRecallTime() == 0) {
            new FriendMessageSender().sendMessage(message.getId(), message.getMessage());
        } else {
            new FriendMessageSender().sendMessageRecall(message.getId(), message.getMessage(), message.getRecallTime());
        }
    }
}
