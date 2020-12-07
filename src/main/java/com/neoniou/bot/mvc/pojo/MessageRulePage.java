package com.neoniou.bot.mvc.pojo;

import com.neoniou.bot.mirai.pojo.Rule;
import lombok.Data;

import java.util.List;

/**
 * 查询单个MessageRule的分页类
 * @author Neo.Zzj
 * @date 2020/12/7
 */
@Data
public class MessageRulePage {

    private List<Rule> rules;

    private Integer pageNo;

    private Integer totalPage;

    private Integer pageSize;

    private Integer totalCount;

    public MessageRulePage() {
    }

    public MessageRulePage(List<Rule> rules, Integer pageNo, Integer totalPage, Integer pageSize, Integer totalCount) {
        this.rules = rules;
        this.pageNo = pageNo;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }
}
