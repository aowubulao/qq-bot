package com.neoniou.bot.mvc.controller;

import com.neoniou.bot.mvc.service.BotInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author Neo.Zzj
 * @date 2020/12/03
 */
@RestController
@RequestMapping("/bot/api/botInfo")
public class BotInfoController {

    @Autowired
    private BotInfoService botInfoService;

    @PostMapping("/updateQQInfo")
    public ResponseEntity<Void> updateQQInfo(@RequestParam("qq") String qq,
                                             @RequestParam("password") String password) throws IOException {
        botInfoService.updateQQInfo(qq, password);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/updateBotNick")
    public ResponseEntity<Void> updateBotNick(@RequestParam("botNick") String botNick) throws IOException {
        botInfoService.updateBotNick(botNick);
        return ResponseEntity.ok().build();
    }
}
