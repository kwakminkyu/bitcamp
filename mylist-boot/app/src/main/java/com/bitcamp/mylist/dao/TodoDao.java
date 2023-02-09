package com.bitcamp.mylist.dao;

import com.bitcamp.mylist.domain.Todo;

public interface TodoDao {

  int countAll();

  Object[] findAll();

  void insert(Todo todo) throws Exception;

  int update(int no, Todo todo) throws Exception;

  Object check(int no, boolean done);

  int delete(int no) throws Exception; 
}
