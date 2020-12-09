package com.neoniou.bot.mirai.pojo;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Neo.Zzj
 * @date 2020/12/9
 */
@Data
@Slf4j
public class ImageIndex implements Serializable {

    private static final String FILE = System.getProperty("user.dir") + "/config/setu/imageIndex.cer";

    private Map<String, ImageInfo> image;

    /**
     * 用于随机图片的类
     * Key: 用于随机
     * Value：image Map 的 Key
     */
    private Map<Integer, String> randomMap;

    /**
     * 用于寻找 Tag的随机图片类
     */
    private Map<String, Map<Integer, String>> tagMap;

    public ImageIndex() {
        image = new LinkedHashMap<>();
        generateRandomMap();
        tagMap = new HashMap<>();
    }

    public ImageIndex load() {
        try (FileInputStream fis = new FileInputStream(FILE); ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (ImageIndex) ois.readObject();
        } catch (Exception e) {
            log.info("读取imageIndex.cer错误：", e);
            return null;
        }
    }

    public ImageIndex write(ImageIndex newIndex) {
        try (FileOutputStream fileOut = new FileOutputStream(FILE); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            image = newIndex.getImage();
            generateRandomMap();
            tagMap = new HashMap<>(1024);
            out.writeObject(newIndex);
            return newIndex;
        } catch (Exception e) {
            log.info("写入imageIndex.cer错误：", e);
            return null;
        }
    }

    /**
     * 获取随机一张图片信息
     *
     * @return ImageInfo图片信息
     */
    public ImageInfo getRandomImage() {
        int randomNum = RandomUtil.randomInt(0, randomMap.size() - 1);
        return image.get(randomMap.get(randomNum));
    }

    /**
     * 根据 Tag 获取随机一张图片信息
     *
     * @param tagName 图片 Tag
     * @return ImageInfo图片信息
     */
    public ImageInfo getRandomImageByTag(String tagName) {
        if (!tagMap.containsKey(tagName)) {
            int count = 0;
            Map<Integer, String> map = new HashMap<>(image.size());
            for (Map.Entry<String, ImageInfo> entry : image.entrySet()) {
                if (entry.getValue().toString().contains(tagName)) {
                    map.put(count++, entry.getKey());
                }
            }
            tagMap.put(tagName, map);
        }
        Map<Integer, String> map = tagMap.get(tagName);
        if (map.size() == 0) {
            return null;
        }
        int randomNum = RandomUtil.randomInt(0, map.size() - 1);
        return image.get(map.get(randomNum));
    }

    public void put(String key, ImageInfo imageInfo) {
        image.put(key, imageInfo);
    }

    public ImageInfo get(String key) {
        return image.get(key);
    }

    public ImageInfo remove(String key) {
        return image.remove(key);
    }

    private void generateRandomMap() {
        int count = 0;
        randomMap = new HashMap<>(image.size());
        for (String key : image.keySet()) {
            randomMap.put(count++, key);
        }
    }
}