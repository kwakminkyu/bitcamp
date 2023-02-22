package com.bitcamp.mylist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Todo;
import com.bitcamp.mylist.service.TodoService;

@RestController
public class TodoController {

  @Autowired
  TodoService todoService;

  public TodoController() throws Exception {
    System.out.println("TodoController() 호출됨!");
  }

  @RequestMapping("/todo/list")
  public Object list() {
    return todoService.list();
  }

  @RequestMapping("/todo/add")
  public Object add(Todo todo) {
    return todoService.add(todo);
  }

  @RequestMapping("/todo/update")
  public Object update(Todo todo) {
    return todoService.update(todo);
  }

  @RequestMapping("/todo/check")
  public Object check(int no, boolean done) {
    return todoService.check(no, done);
  }

  @RequestMapping("/todo/delete")
  public Object delete(int no) {
    return todoService.delete(no);
  }
}
