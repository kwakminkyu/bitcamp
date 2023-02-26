package com.bitcamp.mylist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.mylist.dao.MemberDao;
import com.bitcamp.mylist.domain.Member;
import com.bitcamp.mylist.service.MemberService;

@Service
public class DefaulMemberService implements MemberService {
  @Autowired
  MemberDao memberDao;

  @Override
  public int add(Member member) {
    return memberDao.insert(member);
  }

  @Override
  public Member get(String email, String password) {
    return memberDao.findByEmailAndPassword(email, password);
  }
}
