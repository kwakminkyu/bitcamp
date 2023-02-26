package com.bitcamp.mylist.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bitcamp.mylist.domain.Member;

@Mapper
public interface MemberDao {

  int insert(Member member);

  Member findByEmailAndPassword(@Param("email") String email,@Param("password") String password);


}