package com.neoniou.bot.mirai.core;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Neo.Zzj
 * @date 2020/11/29
 */
@Slf4j
public class BotInfo {

    public static Long qq;

    public static String password;

    public static String botNick;

    public static boolean isActive = false;

    static {
        try {
            Properties props = new Properties();
            InputStream is = new FileInputStream(System.getProperty("user.dir") + "/config/bot.properties");
            props.load(is);
            qq = Long.parseLong(props.getProperty("qq"));
            password = props.getProperty("password");
            botNick = props.getProperty("botNick");
        } catch (IOException e) {
            log.info("读取bot.properties错误: ", e);
        }
    }
}
