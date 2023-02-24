package com.bitcamp.mylist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.mylist.dao.MemberDao;
import com.bitcamp.mylist.domain.Member;

@Service
public class DefaulMemberService {
  @Autowired
  MemberDao memberDao;

  public int add(Member member) {
    return memberDao.insert(member);
  }
}
