package com.bitcamp.mylist.service;

import com.bitcamp.mylist.domain.Member;

public interface MemberService {

  int add(Member member);

  Member get(String email, String password);

  Member get(String email);
}
