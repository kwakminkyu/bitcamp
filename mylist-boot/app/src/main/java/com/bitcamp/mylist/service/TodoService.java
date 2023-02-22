package com.bitcamp.mylist.service;

import java.util.List;
import com.bitcamp.mylist.domain.Todo;

public interface TodoService {

  int add(Todo todo);

  List<Todo> list();

  int update(Todo todo);

  Object check(int no, boolean done);

  int delete(int no);
}
