package com.neoniou.bot.mvc.service;

import com.neoniou.bot.mvc.pojo.SendMessage;

/**
 * @author Neo.Zzj
 * @date 2020/12/8
 */
public interface SendMessageService {
    /**
     * 发送群消息
     * @param message SendMessage Pojo
     */
    void sendGroupMessage(SendMessage message);

    /**
     * 发送好友消息
     * @param message SendMessage Pojo
     */
    void sendFriendMessage(SendMessage message);
}
