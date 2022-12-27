package com.bitcamp.mylist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

  ArrayList todoList = new ArrayList();

  @GetMapping("/todo/list")
  public Object list() {
    return ArrayList.toArray(todoList);
  }

  @GetMapping("/todo/add")
  public Object add(Todo todo) {
    ArrayList.add(todoList, todo);
    return todoList.size;
  }

  @GetMapping("/todo/update")
  public Object update(int index, Todo todo) {
    if(index < 0 || index >= todoList.size) {
      return 0;
    }
    return ArrayList.set(todoList, index, todo) == null ? 0 : 1;
  }

  @GetMapping("/todo/check")
  public Object check(int index, boolean done) {
    if(index < 0 || index >= todoList.size) {
      return 0;
    }
    ((Todo)todoList.list[index]).done = done;
    return 1;
  }

  @GetMapping("/todo/delete")
  public Object delet(int index) {
    //    if(index < 0 || index >= todoList.size) {
    //      return 0;
    //    }
    ArrayList.remove(todoList, index);
    return 1;
  }
}
