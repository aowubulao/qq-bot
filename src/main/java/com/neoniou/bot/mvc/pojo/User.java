package com.neoniou.bot.mvc.pojo;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import lombok.Data;

import java.io.*;
import java.util.Objects;

/**
 * @author Neo.Zzj
 * @date 2020/11/30
 */
@Data
public class User implements Serializable {

    private String username;

    private String password;

    private static final String FILE = System.getProperty("user.dir") + "/config/user.cer";

    public User loadUser() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(FILE);
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user = (User) ois.readObject();
        ois.close();
        fis.close();
        return user;
    }

    public User updateUser(User user) throws IOException {
        user.setPassword(new Digester(DigestAlgorithm.SHA256).digestHex(user.password));
        FileOutputStream fileOut = new FileOutputStream(FILE);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(user);
        out.close();
        fileOut.close();
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