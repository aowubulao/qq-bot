package com.neoniou.bot.mvc.service.impl;

import cn.hutool.core.text.UnicodeUtil;
import com.neoniou.bot.mvc.service.BotInfoService;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Neo.Zzj
 * @date 2020/12/03
 */
@Service
public class BotInfoServiceImpl implements BotInfoService {

    private static final String FILE = System.getProperty("user.dir") + "/config/bot.properties";

    @Override
    public void updateQQInfo(String qq, String password) throws IOException {
        Properties props = readProps();
        props.setProperty("qq", qq);
        props.setProperty("password", password);
        writeProps(props);
    }

    @Override
    public void updateBotNick(String botNick) throws IOException {
        Properties props = readProps();
        props.setProperty("botNick", botNick);
        writeProps(props);
    }

    private Properties readProps() throws IOException {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(FILE);
        props.load(fis);
        fis.close();
        return props;
    }

    private void writeProps(Properties props) throws IOException {
        String botNick = props.getProperty("botNick");
        props.setProperty("botNick", UnicodeUtil.toUnicode(botNick));

        FileWriter writer = new FileWriter(FILE);
        props.store(writer, "Bot Config");
        writer.close();
    }
}
