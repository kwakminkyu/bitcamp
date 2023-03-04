package com.bitcamp.mylist.conf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.bitcamp.mylist.interceptor.AuthInterceptor;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer{

  private static final Logger log = LogManager.getLogger(MvcConfiguration.class);

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    log.debug("MvcConfiguration.addInterceptors() 호출됨");

    registry.addInterceptor(new AuthInterceptor())
    .addPathPatterns("/**/add", "/**/update", "/**/delete");
  }
}