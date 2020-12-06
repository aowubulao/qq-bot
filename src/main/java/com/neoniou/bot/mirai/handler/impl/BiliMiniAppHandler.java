package com.neoniou.bot.mirai.handler.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.neoniou.bot.mirai.handler.MessageHandler;
import net.mamoe.mirai.message.GroupMessageEvent;

/**
 * @author Neo.Zzj
 * @date 2020/12/4
 */
public class BiliMiniAppHandler implements MessageHandler {

    private static final String REPLACE_STR_1 = "[[QQ小程序]哔哩哔哩]请使用最新版本手机QQ查看";

    private static final String REPLACE_STR_2 = "当前版本不支持该消息类型，请使用最新版本手机QQ查看";

    private static final String REPLACE_STR_3 = "[mirai:app:";

    private static final String META = "meta";

    private static final String DETAIL_1 = "detail_1";

    private static final String DESC = "desc";

    private static final String QQ_DOC_URL = "qqdocurl";

    private static final String QM = "?";

    private static final String SUB_STR = "]";

    @Override
    public void handleMessage(GroupMessageEvent event, String sendMessage) {
        String msgString = event.getMessage().toString();
        String msgBody = msgString.substring(msgString.indexOf(SUB_STR) + 1);
        event.getGroup().sendMessage(generateMsg(parse2Json(msgBody)));
    }

    private static String generateMsg(JSONObject json) {
        StringBuilder sb = new StringBuilder("B站小程序分享\n");
        sb.append("标题：");
        sb.append(json.get(DESC));
        sb.append("\n");
        sb.append("链接：");
        String url = json.get(QQ_DOC_URL).toString();
        sb.append(url, 0, url.indexOf(QM));

        return sb.toString();
    }

    private static JSONObject parse2Json(String msgBody) {
        msgBody = msgBody.replace(REPLACE_STR_1, "");
        msgBody = msgBody.replace(REPLACE_STR_2, "");
        msgBody = msgBody.replace(REPLACE_STR_3, "");
        msgBody = msgBody.substring(0, msgBody.length() - 1);
        Object meta = JSONUtil.parseObj(msgBody).get(META);
        Object detail1 = JSONUtil.parseObj(meta).get(DETAIL_1);
        return JSONUtil.parseObj(detail1);
    }
}
