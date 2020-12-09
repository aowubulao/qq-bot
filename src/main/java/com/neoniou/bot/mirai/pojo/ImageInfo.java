package com.neoniou.bot.mirai.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Neo.Zzj
 * @date 2020/12/9
 */
@Data
public class ImageInfo implements Serializable {

    private String pid;

    private String p;

    private String uid;

    private String title;

    private String author;

    private List<String> tags;

    private String url;
}
