package com.neoniou.bot.mvc.pojo;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.neoniou.bot.mirai.pojo.MessageRule;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Objects;

/**
 * @author Neo.Zzj
 * @date 2020/11/30
 */
@Data
@Slf4j
public class User implements Serializable {

    private String username;

    private String password;

    private static final String FILE = System.getProperty("user.dir") + "/config/user.cer";

    public User loadUser() {
        try (FileInputStream fis = new FileInputStream(FILE); ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (User) ois.readObject();
        } catch (Exception e) {
            log.info("读取user.cer错误：", e);
            return null;
        }
    }

    public User updateUser(User user) {
        user.setPassword(new Digester(DigestAlgorithm.SHA256).digestHex(user.password));
        try (FileOutputStream fileOut = new FileOutputStream(FILE); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(user);
        } catch (Exception e) {
            log.info("写入user.cer错误：", e);
        }
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return username.equals(user.username) &&
                user.password != null &&
                password.equals(new Digester(DigestAlgorithm.SHA256).digestHex(user.password));
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}