package com.neoniou.bot.mvc.controller;

import com.neoniou.bot.mirai.pojo.Rule;
import com.neoniou.bot.mvc.service.MessageRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Neo.Zzj
 * @date 2020/12/4
 */
@RestController
@RequestMapping("/bot/api/messageRule")
public class MessageRuleController {

    @Autowired
    private MessageRuleService messageRuleService;

    @GetMapping("/single")
    public ResponseEntity<List<Rule>> getSingleRule(@RequestParam("matching") int matching) {
        return ResponseEntity.ok().body(messageRuleService.getSingleRule(matching));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addRule(Rule rule,
                                        @RequestParam("matching") int matching) {
        messageRuleService.addRule(rule, matching);
        return ResponseEntity.ok().build();
    }
}
