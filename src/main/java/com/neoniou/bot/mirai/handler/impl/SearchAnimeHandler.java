package com.neoniou.bot.mirai.handler.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.neoniou.bot.mirai.core.MiraiBot;
import com.neoniou.bot.mirai.handler.MessageHandler;
import com.neoniou.bot.utils.HandlerUtil;
import lombok.Data;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Neo.Zzj
 * @date 2020/12/10
 */
public class SearchAnimeHandler implements MessageHandler {

    private static final String ANIME_API = "https://trace.moe/api/search";

    private static final String IMAGE_MSG = "mirai:image:";

    private static final String PREFIX = "{";

    private static final String SUFFIX = "]";

    @Override
    public void handleMessage(GroupMessageEvent event, String sendMessage) {
        String msgBody = HandlerUtil.getMessageBody(event);
        if (!msgBody.contains(IMAGE_MSG)) {
            return;
        }
        String imageID = msgBody.substring(msgBody.indexOf(PREFIX), msgBody.indexOf(SUFFIX));
        Bot bot = MiraiBot.getBot();
        String imageUrl = bot.queryImageUrl(MessageUtils.newImage(imageID));
        event.getGroup().sendMessage("正在搜索中，请等待一会...");
        event.getGroup().sendMessage(getSendMessage(imageUrl));
    }

    private String getSendMessage(String imageUrl) {
        StringBuilder sb = new StringBuilder();
        SearchResult searchResult = getSearchResult(imageUrl);
        List<AnimeInfo> infos = searchResult.getDocs();

        sb.append("搜索结果：");
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i <= infos.size(); i++) {
            AnimeInfo animeInfo = infos.get(i - 1);
            String key = animeInfo.getTitle() + animeInfo.getEpisode();
            if (!map.containsKey(key)) {
                map.put(key, "");
                sb.append("\n第").append(map.size()).append("个：");
                generateMessage(animeInfo, sb);
            }
            if (map.size() >= 2) {
                break;
            }
        }

        return sb.toString();
    }

    private SearchResult getSearchResult(String imageUrl) {
        byte[] bytes = HttpRequest.get(imageUrl)
                .execute()
                .bodyBytes();

        String body = HttpRequest.post(ANIME_API)
                .form("image", Base64.encode(bytes))
                .execute()
                .body();

        return JSONUtil.toBean(JSONUtil.parseObj(body), SearchResult.class);
    }

    private void generateMessage(AnimeInfo animeInfo, StringBuilder sb) {
        String similarity = animeInfo.getSimilarity().substring(0, 5);
        sb.append("相似度：").append(similarity).append("\n");
        sb.append("动漫：").append(animeInfo.getTitle()).append("(").append(animeInfo.getTitleChinese()).append(")\n");
        double d = Double.parseDouble(animeInfo.getAt());
        int m = (int) (d / 60);
        int s = (int) (d - m * 60);
        sb.append("具体位置：");
        if (!"".equals(animeInfo.getEpisode())) {
            sb.append("第").append(animeInfo.getEpisode()).append("集 ");
        }
        sb.append(m).append("分").append(s).append("秒");
    }

    @Data
    private static class SearchResult {

        private List<AnimeInfo> docs;

    }

    @Data
    private static class AnimeInfo {

        private String at;

        private String title;

        private String titleChinese;

        private String similarity;

        private String episode;
    }
}
