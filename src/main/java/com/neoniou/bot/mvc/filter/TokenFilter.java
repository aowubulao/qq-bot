package com.neoniou.bot.mvc.filter;

import com.neoniou.bot.constant.HttpCode;
import com.neoniou.bot.exception.ExceptionResult;
import com.neoniou.bot.exception.NeoException;
import com.neoniou.bot.mvc.util.TokenUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Neo.Zzj
 */
public class TokenFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) {
        if (!TokenUtil.isToken(request.getHeader("token"))) {
            throw new NeoException(new ExceptionResult(HttpCode.FORBIDDEN, "请登录!"));
        }
        return true;
    }
}