package com.neoniou.bot.mvc.service;

import java.io.IOException;

/**
 * @author Neo.Zzj
 * @date 2020/12/03
 */
public interface BotInfoService {

    /**
     * 更新配置文件的 qq 和 密码
     * @param qq qq账号
     * @param password 密码
     */
    void updateQQInfo(String qq, String password) throws IOException;

    /**
     * 更新 Bot 的昵称
     * @param botNick 昵称
     */
    void updateBotNick(String botNick) throws IOException;
}
