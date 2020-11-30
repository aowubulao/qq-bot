package com.neoniou.bot.mvc.controller;

import com.neoniou.bot.mvc.pojo.User;
import com.neoniou.bot.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Neo.Zzj
 * @date 2020/11/30
 */
@RestController
@RequestMapping("/bot/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(User user) {
        return ResponseEntity.ok().body(userService.login(user));
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(User user) throws IOException {
        userService.update(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        userService.logout();
        return ResponseEntity.ok().build();
    }
}
