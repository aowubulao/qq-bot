package com.neoniou.bot.mirai.core;

import com.neoniou.bot.mirai.handler.other.LiveMessageHandler;
import com.neoniou.bot.mirai.pojo.ImageIndex;
import com.neoniou.bot.mirai.pojo.MessageRule;
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
public class BotCoreConfig {

    public static Long qq;

    public static String password;

    public static String botNick;

    public static boolean isActive = false;

    public static MessageRule messageRule;

    public static ImageIndex imageIndex;

    static {
        readBotInfoProps();
        getMessageRule();
        getImageIndex();
    }

    public static void updateMessageRule(MessageRule newRule) {
        messageRule = messageRule.write(newRule);
    }

    public static void startMonitorLive() {
        new LiveMessageHandler().startMonitor();
    }

    private static void readBotInfoProps() {
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

    private static void getMessageRule() {
        messageRule = new MessageRule();
        messageRule = messageRule.load();
    }

    private static void getImageIndex() {
        imageIndex = new ImageIndex();
        imageIndex = imageIndex.load();
    }
}
