package com.neoniou.bot.mvc.pojo;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Neo.Zzj
 * @date 2020/11/30
 */
@Data
public class User implements Serializable {

    private String username;

    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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