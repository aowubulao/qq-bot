package com.neoniou.bot.mvc.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Neo.Zzj
 * @date 2020/11/30
 */
@Configuration
public class FilterConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new TokenFilter());
        registration.addPathPatterns("/bot/api/**");
        registration.excludePathPatterns("/bot/api/login");
    }
}
