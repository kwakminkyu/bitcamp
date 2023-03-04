package com.bitcamp.mylist.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.bitcamp.mylist.controller.ResultMap;
import com.bitcamp.mylist.domain.Member;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthInterceptor implements HandlerInterceptor{

  private static final Logger log = LogManager.getLogger(AuthInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    log.trace("preHandle 호출");
    HttpSession session =request.getSession();
    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(new ObjectMapper().writeValueAsString(new ResultMap()
          .setStatus(ResultMap.FAIL)
          .setData("로그인 하지 않았습니다")));
      return false;
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    log.trace("postHandle 호출");
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }
}