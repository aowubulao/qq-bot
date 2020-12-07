package com.neoniou.bot.mvc.service;

import com.neoniou.bot.mirai.pojo.Rule;
import com.neoniou.bot.mvc.pojo.MessageRulePage;

import java.util.List;
import java.util.Map;

/**
 * @author Neo.Zzj
 * @date 2020/12/7
 */
public interface MessageRuleService {

    /**
     * 获取所有匹配下的规则
     * @return Map
     */
    Map<Integer, List<Rule>> getAllRules();

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

    /**
     * 更新单个规则
     * @param rule 规则类
     * @param matching 所属消息匹配规则
     */
    void updateRule(Rule rule, int matching);

    /**
     * 删除单个规则
     * @param rule 规则类
     * @param matching 所属消息匹配规则
     */
    void deleteRule(Rule rule, int matching);

    /**
     * 分页查询
     * @param pageNo 当前所在页
     * @param pageSize 一页展示条数
     * @return 分页信息
     */
    MessageRulePage getSingleRulePage(int matching, int pageNo, int pageSize);
}
