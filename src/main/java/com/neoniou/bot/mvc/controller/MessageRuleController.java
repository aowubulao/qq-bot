package com.neoniou.bot.mvc.controller;

import com.neoniou.bot.mirai.pojo.Rule;
import com.neoniou.bot.mvc.pojo.MessageRulePage;
import com.neoniou.bot.mvc.service.MessageRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Neo.Zzj
 * @date 2020/12/4
 */
@RestController
@RequestMapping("/bot/api/messageRule")
public class MessageRuleController {

    @Autowired
    private MessageRuleService messageRuleService;

    @GetMapping("/all")
    public ResponseEntity<Map<Integer, List<Rule>>> getAllRules() {
        return ResponseEntity.ok().body(messageRuleService.getAllRules());
    }

    @GetMapping("/single")
    public ResponseEntity<List<Rule>> getSingleRule(@RequestParam("matching") int matching) {
        return ResponseEntity.ok().body(messageRuleService.getSingleRule(matching));
    }

    @GetMapping("/singlePage")
    public ResponseEntity<MessageRulePage> getSingleRuleByPage(@RequestParam("pageNo") int pageNo,
                                                               @RequestParam("pageSize") int pageSize,
                                                               @RequestParam("matching") int matching) {
        return ResponseEntity.ok().body(messageRuleService.getSingleRulePage(matching, pageNo, pageSize));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addRule(Rule rule,
                                        @RequestParam("matching") int matching) {
        messageRuleService.addRule(rule, matching);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateRule(Rule rule,
                                        @RequestParam("matching") int matching) {
        messageRuleService.updateRule(rule, matching);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteRule(Rule rule,
                                           @RequestParam("matching") int matching) {
        messageRuleService.deleteRule(rule, matching);
        return ResponseEntity.ok().build();
    }
}
