package com.neoniou.bot.mvc.service.impl;

import com.neoniou.bot.constant.HttpCode;
import com.neoniou.bot.exception.ExceptionResult;
import com.neoniou.bot.exception.NeoException;
import com.neoniou.bot.mirai.core.BotCoreConfig;
import com.neoniou.bot.mirai.pojo.MessageRule;
import com.neoniou.bot.mirai.pojo.Rule;
import com.neoniou.bot.mvc.service.MessageRuleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Neo.Zzj
 * @date 2020/12/7
 */
@Service
public class MessageRuleServiceImpl implements MessageRuleService {

    @Override
    public List<Rule> getSingleRule(int matching) {
        Map<String, Rule> ruleMap = BotCoreConfig.messageRule.getRuleMap(matching);
        return new ArrayList<>(ruleMap.values());
    }

    @Override
    public void addRule(Rule rule, int matching) {
        MessageRule messageRule = BotCoreConfig.messageRule;
        Map<String, Rule> ruleMap = messageRule.getRuleMap(matching);
        if (ruleMap.containsKey(rule.getStr())) {
            throw new NeoException(new ExceptionResult(HttpCode.SERVER_ERROR, "重复的匹配规则！"));
        }
        ruleMap.put(rule.getStr(), rule);
        messageRule.setRuleMap(matching, ruleMap);
        BotCoreConfig.updateMessageRule(messageRule);
    }
}
