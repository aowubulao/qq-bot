package com.neoniou.bot.mvc.service;

import org.springframework.stereotype.Service;

/**
 * @author Neo.Zzj
 * @date 2020/11/29
 */
public interface BotService {

    /**
     * 登录 Bot
     */
    void start();

    /**
     * 关闭 Bot
     */
    void close();

    /**
     * 重启（关闭再开启）Bot
     */
    void restart();

    /**
     * 获取 Bot状态
     * @return True:在线，False:离线
     */
    boolean getBotStatus();
}
