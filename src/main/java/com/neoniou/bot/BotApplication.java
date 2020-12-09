package com.neoniou.bot;

import com.neoniou.bot.mirai.core.MiraiBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * @author Neo.Zzj
 * @date 2020/11/29
 */
@Slf4j
@EnableAsync
@SpringBootApplication
public class BotApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(BotApplication.class, args);
        printFrontIpAddr(context);
        MiraiBot.start();
    }

    private static void printFrontIpAddr(ConfigurableApplicationContext context) throws Exception {
        Environment environment = context.getBean(Environment.class);
        InetAddress localHost = Inet4Address.getLocalHost();
        String ip = localHost.getHostAddress();
        log.info("网址：http://" + ip + ":" + environment.getProperty("server.port") + "(网络)访问前端控制界面");
        log.info("网址：http://127.0.0.1:" + environment.getProperty("server.port") + "(本地)访问前端控制界面");
    }
}
