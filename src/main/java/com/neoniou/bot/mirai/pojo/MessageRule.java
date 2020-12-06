package com.neoniou.bot.mirai.pojo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息规则类
 * 处理顺序：正则->相等->包含
 * 一条消息只会处理一次
 *
 * @author Neo.Zzj
 * @date 2020/12/4
 */
@Data
@Slf4j
public class MessageRule implements Serializable {

    /**
     * 正则
     */
    private static final int REGULAR = 1;

    /**
     * 相等
     */
    private static final int EQUAL = 2;

    /**
     * 包含
     */
    private static final int CONTAINS = 3;

    private static final String FILE = System.getProperty("user.dir") + "/config/messageRule.cer";

    private Map<Integer, List<Rule>> ruleMap = new HashMap<>();

    public MessageRule load() {
        try (FileInputStream fis = new FileInputStream(FILE); ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (MessageRule) ois.readObject();
        } catch (Exception e) {
            log.info("读取messageRule.cer错误：", e);
            return null;
        }
    }

    public MessageRule write(MessageRule rule) {
        try (FileOutputStream fileOut = new FileOutputStream(FILE); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(rule);
            return rule;
        } catch (Exception e) {
            log.info("写入messageRule.cer错误：", e);
            return null;
        }
    }
}
