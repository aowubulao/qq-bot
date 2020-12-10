package com.neoniou.bot.mirai.handler.other;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.neoniou.bot.mirai.sender.impl.GroupMessageSender;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Neo.Zzj
 * @date 2020/12/9
 */
@Slf4j
public class LiveMessageHandler {

    private static final String ROOM_INIT = "http://api.live.bilibili.com/room/v1/Room/room_init?id=";

    private static final String DATA = "data";

    private static final String LIVE_STATUS = "live_status";

    private static String[] roomId;

    private static String[] alertGroup;

    static {
        readProps();
    }

    private static void readProps() {
        try {
            Properties props = new Properties();
            InputStream is = new FileInputStream(System.getProperty("user.dir") + "/config/live.properties");
            props.load(is);
            roomId = props.getProperty("roomId").split(";");
            alertGroup = props.getProperty("alertGroup").split(";");
        } catch (IOException e) {
            log.info("读取live.properties错误: ", e);
        }
    }

    public void startMonitor() {
        boolean[] b = new boolean[roomId.length];
        for (int i = 0; i < roomId.length; i++) {
            int fi = i;
            new Thread(() -> {
                while (true) {
                    boolean live = isLive(roomId[fi]);
                    log.info("{} 检测一次，状态：{}", roomId[fi], live);
                    if (!b[fi] && live) {
                        b[fi] = true;
                        String message = "开播了~\n直播间地址：\nhttps://live.bilibili.com/" + roomId[fi];
                        new GroupMessageSender().sendMessage(Long.parseLong(alertGroup[fi]), message);
                    }
                    if (b[fi] && !live) {
                        b[fi] = false;
                    }
                    ThreadUtil.sleep(60 * 1000);
                }
            }).start();
        }
    }

    private boolean isLive(String roomId) {
        try {
            String resBody = HttpRequest.get(ROOM_INIT + roomId)
                    .execute()
                    .body();

            JSONObject body = JSONUtil.parseObj(resBody);

            JSONObject data = JSONUtil.parseObj(body.get(DATA));
            return Integer.parseInt(data.get(LIVE_STATUS).toString()) == 1;
        } catch (Exception e) {
            log.error("[{}]Scanning room error: ", roomId, e);
            return false;
        }
    }
}
