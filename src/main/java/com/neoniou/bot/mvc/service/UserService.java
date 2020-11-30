package com.neoniou.bot.mvc.service;

import com.neoniou.bot.mvc.pojo.User;

import java.io.IOException;

/**
 * @author Neo.Zzj
 * @date 2020/11/30
 */
public interface UserService {

    /**
     * 登录
     * @param user User
     * @return 登录成功返回token
     */
    String login(User user);

    /**
     * 更新 User信息
     * @param user User
     */
    void update(User user) throws IOException;

    /**
     * 退出登录，删除 token
     */
    void logout();
}
