package com.bitcamp.mylist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.dao.TodoDao;
import com.bitcamp.mylist.domain.Todo;

@RestController
public class TodoController {

  @Autowired
  TodoDao todoDao;

  public TodoController() throws Exception {
    System.out.println("TodoController() 호출됨!");
  }

  @RequestMapping("/todo/list")
  public Object list() {
    return todoDao.findAll();
  }

  @RequestMapping("/todo/add")
  public Object add(Todo todo) throws Exception {
    todoDao.insert(todo);
    return todoDao.countAll();
  }

  @RequestMapping("/todo/update")
  public Object update(int index, Todo todo) throws Exception {
    return todoDao.update(index, todo);
  }

  @RequestMapping("/todo/check")
  public Object check(int index, boolean done) {
    return todoDao.check(index, done);
  }

  @RequestMapping("/todo/delete")
  public Object delete(int index) throws Exception {
    return todoDao.delete(index);
  }
}
