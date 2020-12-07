package com.neoniou.bot.mirai.handler;

import com.neoniou.bot.mirai.handler.impl.BiliMiniAppHandler;
import com.neoniou.bot.mirai.handler.impl.SimpleMessageHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Neo.Zzj
 * @date 2020/12/6
 */
public class MessageHandlerFactory {

    private static final String BILI_HANDLER = "BiliMiniAppHandler";

    private static final String SIMPLE_HANDLER = "SimpleMessageHandler";

    private static final Map<String, MessageHandler> HANDLER_MAP = new HashMap<>();

    static {
        createHandlerMap();
    }

    public static MessageHandler create(String handlerName) {
        return HANDLER_MAP.get(handlerName);
    }

    private static void createHandlerMap() {
        HANDLER_MAP.put(BILI_HANDLER, new BiliMiniAppHandler());
        HANDLER_MAP.put(SIMPLE_HANDLER, new SimpleMessageHandler());
    }
}