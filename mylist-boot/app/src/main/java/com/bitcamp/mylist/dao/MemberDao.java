package com.bitcamp.mylist.dao;

import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.mylist.domain.Member;

@Mapper
public interface MemberDao {

  //  List<Member> findAll();
  //
  int insert(Member member);
  //
  //  Member findByNo(int no);
  //
  //  int update(Member member);
  //
  //  int delete(int no); 
}
