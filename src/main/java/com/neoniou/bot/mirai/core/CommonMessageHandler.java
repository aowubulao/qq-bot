package com.neoniou.bot.mirai.core;

import com.neoniou.bot.mirai.pojo.MessageRule;
import com.neoniou.bot.mirai.pojo.Rule;
import net.mamoe.mirai.message.GroupMessageEvent;

import java.util.List;
import java.util.Map;

/**
 * @author Neo.Zzj
 * @date 2020/11/29
 */
public class CommonMessageHandler {

    public static final String SUB_STR = "]";

    /**
     * 正则
     */
    private static final int REGULAR = 1;

    /**
     * 相等
     */
    private static final int EQUAL = 2;

    /**
     * 包含
     */
    private static final int CONTAINS = 3;

    public static void handleMessage(GroupMessageEvent event) {
        String msgString = event.getMessage().toString();
        String msgBody = msgString.substring(msgString.indexOf(SUB_STR) + 1);

        selectHandler(event, msgBody);
    }

    private static void selectHandler(GroupMessageEvent event, String msgBody) {
        MessageRule messageRule = BotCoreConfig.messageRule;

        Map<Integer, List<Rule>> ruleMap = messageRule.getRuleMap();

        // 首先匹配正则
        List<Rule> regulars = ruleMap.get(REGULAR);
        for (Rule rule : regulars) {
            if (rule.getIsActive() && msgBody.matches(rule.getStr())) {
                rule.getMessageHandler().handleMessage(event, rule.getSendMessage());
                return;
            }
        }

        // 匹配相等
        List<Rule> equals = ruleMap.get(EQUAL);
        for (Rule rule : equals) {
            if (rule.getIsActive() && msgBody.equals(rule.getStr())) {
                rule.getMessageHandler().handleMessage(event, rule.getSendMessage());
                return;
            }
        }

        // 最后匹配包含
        List<Rule> contains = ruleMap.get(CONTAINS);
        for (Rule rule : contains) {
            if (rule.getIsActive() && msgBody.contains(rule.getStr())) {
                rule.getMessageHandler().handleMessage(event, rule.getSendMessage());
                return;
            }
        }
    }
}