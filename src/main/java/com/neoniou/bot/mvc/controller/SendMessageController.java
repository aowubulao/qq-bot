package com.neoniou.bot.mvc.controller;

import com.neoniou.bot.mvc.pojo.SendMessage;
import com.neoniou.bot.mvc.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Neo.Zzj
 * @date 2020/12/8
 */
@RestController
@RequestMapping("/bot/api/sendMessage")
public class SendMessageController {

    @Autowired
    private SendMessageService sendMessageService;

    @PostMapping("/group")
    public ResponseEntity<Void> sendGroupMessage(SendMessage message) {
        System.out.println(message.toString());
        sendMessageService.sendGroupMessage(message);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/friend")
    public ResponseEntity<Void> sendFriendMessage(SendMessage message) {
        sendMessageService.sendFriendMessage(message);
        return ResponseEntity.ok().build();
    }
}
