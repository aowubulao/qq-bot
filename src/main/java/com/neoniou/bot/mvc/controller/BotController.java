package com.neoniou.bot.mvc.controller;

import com.neoniou.bot.mvc.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Neo.Zzj
 * @date 2020/11/29
 */
@RestController
@RequestMapping("/bot")
public class BotController {

    @Autowired
    private BotService botService;

    @Async
    @GetMapping("/start")
    public void startBot() {
        botService.start();
    }

    @GetMapping("/close")
    public ResponseEntity<Void> closeBot() {
        botService.close();
        return ResponseEntity.ok().build();
    }

    @Async
    @GetMapping("/restart")
    public void restartBot() {
        botService.restart();
    }

    @GetMapping("/status")
    public ResponseEntity<Boolean> getBotStatus() {
        return ResponseEntity.ok().body(botService.getBotStatus());
    }
}