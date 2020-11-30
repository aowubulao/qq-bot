package com.neoniou.bot.mvc.util;

import cn.hutool.core.util.IdUtil;

/**
 * @author Neo.Zzj
 * @date 2020/11/30
 */
public class TokenUtil {

    private static String curToken = null;

    public static String generateToken() {
        curToken = IdUtil.simpleUUID();
        return curToken;
    }

    public static void deleteToken() {
        curToken = null;
    }

    public static boolean isToken(String token) {
        return token!= null && token.equals(curToken);
    }
}
