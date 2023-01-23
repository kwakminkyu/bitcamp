package com.bitcamp.mylist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Todo;
import com.bitcamp.util.ArrayList;

@RestController
public class TodoController {

  ArrayList todoList = new ArrayList();

  public TodoController() {
    System.out.println("TodoController() 호출됨!");
  }

  @GetMapping("/todo/list")
  public Object list() {
    return todoList.toArray();
  }

  @GetMapping("/todo/add")
  public Object add(Todo todo) {
    todoList.add(todo);
    return todoList.size();
  }

  @GetMapping("/todo/update")
  public Object update(int index, Todo todo) {
    if(index < 0 || index >= todoList.size()) {
      return 0;
    }
    Todo old = (Todo) todoList.get(index);
    todo.setDone(old.isDone());

    return todoList.set(index, todo) == null ? 0 : 1;
  }

  @GetMapping("/todo/check")
  public Object check(int index, boolean done) {
    if(index < 0 || index >= todoList.size()) {
      return 0;
    }
    ((Todo)todoList.get(index)).setDone(done);
    return 1;
  }

  @GetMapping("/todo/delete")
  public Object delet(int index) {
    //    if(index < 0 || index >= todoList.size) {
    //      return 0;
    //    }
    todoList.remove(index);
    return 1;
  }
}
