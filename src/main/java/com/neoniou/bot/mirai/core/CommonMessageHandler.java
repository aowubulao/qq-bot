package com.neoniou.bot.mirai.core;

import com.neoniou.bot.mirai.handler.MessageHandlerFactory;
import com.neoniou.bot.mirai.pojo.MessageRule;
import com.neoniou.bot.mirai.pojo.Rule;
import net.mamoe.mirai.message.GroupMessageEvent;

import java.util.Map;

/**
 * @author Neo.Zzj
 * @date 2020/11/29
 */
public class CommonMessageHandler {

    public static final String SUB_STR = "]";

    public static void handleMessage(GroupMessageEvent event) {
        String msgString = event.getMessage().toString();
        String msgBody = msgString.substring(msgString.indexOf(SUB_STR) + 1);

        selectHandler(event, msgBody);
    }

    private static void selectHandler(GroupMessageEvent event, String msgBody) {
        MessageRule messageRule = BotCoreConfig.messageRule;

        // 首先匹配正则
        Map<String, Rule> regularMap = messageRule.getRegularMap();
        for (Rule rule : regularMap.values()) {
            if (msgBody.matches(rule.getStr())) {
                MessageHandlerFactory.create(rule.getMessageHandler()).handleMessage(event, rule.getSendMessage());
                return;
            }
        }

        // 匹配相等
        Map<String, Rule> equalMap = messageRule.getEqualMap();
        for (Rule rule : equalMap.values()) {
            if (msgBody.equals(rule.getStr())) {
                MessageHandlerFactory.create(rule.getMessageHandler()).handleMessage(event, rule.getSendMessage());
                return;
            }
        }

        // 最后匹配包含
        Map<String, Rule> containsMap = messageRule.getContainsMap();
        for (Rule rule : containsMap.values()) {
            if (msgBody.contains(rule.getStr())) {
                MessageHandlerFactory.create(rule.getMessageHandler()).handleMessage(event, rule.getSendMessage());
                return;
            }
        }
    }
}