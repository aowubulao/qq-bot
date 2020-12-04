package com.neoniou.bot.mirai.core;

import kotlin.coroutines.CoroutineContext;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import org.jetbrains.annotations.NotNull;

/**
 * @author Neo.Zzj
 * @date 2020/11/29
 */
@Slf4j
public class MiraiBot {

    private static Bot bot;

    public static void start() {
        log.info("Mirai Bot开始启动...");
        //启动Bot
        bot = BotFactoryJvm.newBot(BotInfo.qq, BotInfo.password, new BotConfiguration() {
            {
                fileBasedDeviceInfo(System.getProperty("user.dir") + "/config/deviceInfo.json");
            }
        });
        bot.login();

        //监听群消息事件
        Events.registerEvents(bot, new SimpleListenerHost() {
            @EventHandler
            public ListeningStatus onGroupMessage(GroupMessageEvent event) {
                CommonMessageHandler.handleMessage(event);

                return ListeningStatus.LISTENING;
            }

            @Override
            public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
                throw new RuntimeException("在事件处理中发生异常", exception);
            }
        });
        BotInfo.isActive = true;
        bot.join();
    }

    public static void close() {
        BotInfo.isActive = false;
        bot.close(new Throwable());
    }

    public static boolean botStatus() {
        return BotInfo.isActive;
    }
}
