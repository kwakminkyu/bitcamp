package com.bitcamp.mylist.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.mylist.dao.TodoDao;
import com.bitcamp.mylist.domain.Todo;
import com.bitcamp.mylist.service.TodoService;

@Service
public class DefaultTodoService implements TodoService {

  @Autowired
  TodoDao todoDao;

  @Override
  @Transactional
  public int add(Todo todo) {
    todoDao.insert(todo);
    return todoDao.countAll();
  }

  @Override
  public List<Todo> list() {
    return todoDao.findAll();
  }

  @Override
  public Todo get(int no) {
    return todoDao.findByNo(no);
  }

  @Override
  @Transactional
  public int update(Todo todo) {
    return todoDao.update(todo);
  }

  @Override
  @Transactional
  public int updateStatus(Todo todo) {
    return todoDao.updateDone(todo.getNo(), todo.isDone());
  }

  @Override
  @Transactional
  public int delete(int no) {
    return todoDao.delete(no);
  }
}
