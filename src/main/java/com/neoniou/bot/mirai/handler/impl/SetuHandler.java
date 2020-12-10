package com.neoniou.bot.mirai.handler.impl;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.http.HttpRequest;
import com.neoniou.bot.mirai.core.BotCoreConfig;
import com.neoniou.bot.mirai.handler.MessageHandler;
import com.neoniou.bot.mirai.pojo.ImageIndex;
import com.neoniou.bot.mirai.pojo.ImageInfo;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.QuoteReply;

import java.io.InputStream;

/**
 * @author Neo.Zzj
 * @date 2020/12/8
 */
public class SetuHandler implements MessageHandler {

    public static final long RECALL_TIME = 60 * 1000;

    private static final String OVERTIME_MESSAGE = "别冲了，休息一会吧";

    private static final String DE = "的";

    private static final String ZHANG = "张";

    /**
     * Time Cache
     * 用于缓存用户，每30s才能请求一次涩图
     */
    private static final TimedCache<Member, String> TIME_CACHE = CacheUtil.newTimedCache(30 * 1000);

    @Override
    public void handleMessage(GroupMessageEvent event, String sendMessage) {
        if (TIME_CACHE.containsKey(event.getSender())) {
            final QuoteReply quote = new QuoteReply(event.getSource());
            event.getGroup().sendMessage(quote.plus(OVERTIME_MESSAGE));
        } else {
            ImageInfo imageInfo = getSetu(event.getMessage().toString());
            if (imageInfo == null) {
                event.getGroup().sendMessage("没有相关的涩图");
                return;
            }
            event.getGroup().sendMessage("标题：" + imageInfo.getTitle() +
                    "\n图片链接：" + imageInfo.getUrl());

            final Image image = event.getGroup().uploadImage(getImageFile(imageInfo.getUrl()));
            event.getGroup().sendMessage(image).recallIn(RECALL_TIME);
        }
        TIME_CACHE.put(event.getSender(), "");
    }

    private ImageInfo getSetu(String message) {
        ImageIndex imageIndex = BotCoreConfig.imageIndex;
        if (message.contains(DE)) {
            return imageIndex.getRandomImageByTag(getTagName(message));
        } else {
            return imageIndex.getRandomImage();
        }
    }

    private String getTagName(String message) {
        return message.substring(message.indexOf(ZHANG) + 1, message.indexOf(DE));
    }

    private InputStream getImageFile(String url) {
        return HttpRequest.get(url)
                .execute()
                .bodyStream();
    }
}
