package com.neoniou.bot.mvc.service;

import com.neoniou.bot.mirai.pojo.Rule;

import java.util.List;

/**
 * @author Neo.Zzj
 * @date 2020/12/7
 */
public interface MessageRuleService {

    /**
     * 获取单个规则下的消息匹配信息
     * @param matching 1:正则，2：相等，3：包含
     * @return List<Rule>
     */
    List<Rule> getSingleRule(int matching);

    /**
     * 添加单个规则
     * @param rule 规则类
     * @param matching 所属消息匹配规则
     */
    void addRule(Rule rule, int matching);
}
