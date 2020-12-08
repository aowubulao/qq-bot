package com.neoniou.bot.mirai.sender;

/**
 * @author Neo.Zzj
 * @date 2020/12/8
 */
public interface MessageSender {

    /**
     * 发送消息
     * @param id 好友或群号
     * @param message 发送的消息
     */
    void sendMessage(long id, String message);

    /**
     * 发送消息然后过一段时间撤回
     *
     * @param id 好友或群号
     * @param message 发送的消息
     * @param recallTime 过多久撤回，以秒为单位
     */
    void sendMessageRecall(long id, String message, int recallTime);
}
