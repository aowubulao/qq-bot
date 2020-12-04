package com.neoniou.bot.mirai.pojo;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import lombok.Data;

import java.io.*;
import java.util.HashMap;
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

    private Map<Integer, Rule> ruleMap = new HashMap<>();

    public MessageRule load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(FILE);
        ObjectInputStream ois = new ObjectInputStream(fis);
        MessageRule rule = (MessageRule) ois.readObject();
        ois.close();
        fis.close();
        return rule;
    }

    public MessageRule write(MessageRule rule) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(FILE);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(rule);
        out.close();
        fileOut.close();
        return rule;
    }
}
