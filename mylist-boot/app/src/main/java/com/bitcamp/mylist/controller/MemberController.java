package com.bitcamp.mylist.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Member;
import com.bitcamp.mylist.service.MemberService;

@RestController
public class MemberController {

  MemberService memberService;

  @RequestMapping
  public Object signUp(Member member) {
    return memberService.add(member);
  }
}
