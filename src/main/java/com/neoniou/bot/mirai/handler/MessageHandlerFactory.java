package com.neoniou.bot.mirai.handler;

import com.neoniou.bot.mirai.handler.impl.BiliMiniAppHandler;
import com.neoniou.bot.mirai.handler.impl.SimpleMessageHandler;

/**
 * @author Neo.Zzj
 * @date 2020/12/6
 */
public class MessageHandlerFactory {

    private static final String BILI_HANDLER = "BiliMiniAppHandler";

    private static final String SIMPLE_HANDLER = "SimpleMessageHandler";

    public static MessageHandler create(String handlerName) {
        if (BILI_HANDLER.equals(handlerName)) {
            return new BiliMiniAppHandler();
        } else if (SIMPLE_HANDLER.equals(handlerName)) {
            return new SimpleMessageHandler();
        }
        return null;
    }
}
