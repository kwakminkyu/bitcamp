package com.bitcamp.mylist.dao;

import com.bitcamp.mylist.domain.Board;

public interface BoardDao {

  int countAll();

  Object[] findAll();

  void insert(Board board) throws Exception;

  Board findByNo(int no);

  int update(int no, Board board) throws Exception;

  int delete(int no) throws Exception; 

  void increaseViewCount(int no) throws Exception;
}
