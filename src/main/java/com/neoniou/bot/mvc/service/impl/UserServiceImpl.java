package com.neoniou.bot.mvc.service.impl;

import com.neoniou.bot.constant.HttpCode;
import com.neoniou.bot.exception.ExceptionResult;
import com.neoniou.bot.exception.NeoException;
import com.neoniou.bot.mvc.pojo.User;
import com.neoniou.bot.mvc.service.UserService;
import com.neoniou.bot.mvc.util.TokenUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Neo.Zzj
 * @date 2020/11/30
 */
@Service
public class UserServiceImpl implements UserService {

    private static User localUser;

    static {
        try {
            loadUser();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void loadUser() throws IOException, ClassNotFoundException {
        localUser = new User().loadUser();
    }

    @Override
    public String login(User user) {
        if (localUser.equals(user)) {
            return TokenUtil.generateToken();
        } else {
            throw new NeoException(new ExceptionResult(HttpCode.FORBIDDEN, "登录失败!"));
        }
    }

    @Override
    public void update(User user) throws IOException {
        localUser = localUser.updateUser(user);
    }

    @Override
    public void logout() {
        TokenUtil.deleteToken();
    }
}
