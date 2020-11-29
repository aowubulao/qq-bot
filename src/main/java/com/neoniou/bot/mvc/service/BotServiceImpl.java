package com.neoniou.bot.mvc.service;

import com.neoniou.bot.mirai.core.MiraiBot;
import org.springframework.stereotype.Service;

/**
 * @author Neo.Zzj
 * @date 2020/11/29
 */
@Service
public class BotServiceImpl implements BotService {

    public void start() {
        MiraiBot.start();
    }

    public void close() {
        MiraiBot.close();
    }

    public void restart() {
        this.close();
        this.start();
    }

    public boolean getBotStatus() {
        return MiraiBot.botStatus();
    }
}
