package com.bitcamp.mylist.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Todo;
import com.bitcamp.util.ArrayList;

@RestController
public class TodoController {

  ArrayList todoList;

  public TodoController() throws Exception {
    todoList = new ArrayList();
    System.out.println("TodoController() 호출됨!");

    ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("todos.ser")));

    todoList = (ArrayList) in.readObject();
    in.close();
  }

  @RequestMapping("/todo/list")
  public Object list() {
    return todoList.toArray();
  }

  @RequestMapping("/todo/add")
  public Object add(Todo todo) {
    todoList.add(todo);
    return todoList.size();
  }

  @RequestMapping("/todo/update")
  public Object update(int index, Todo todo) {
    if(index < 0 || index >= todoList.size()) {
      return 0;
    }
    Todo old = (Todo) todoList.get(index);
    todo.setDone(old.isDone());

    return todoList.set(index, todo) == null ? 0 : 1;
  }

  @RequestMapping("/todo/check")
  public Object check(int index, boolean done) {
    if(index < 0 || index >= todoList.size()) {
      return 0;
    }
    ((Todo)todoList.get(index)).setDone(done);
    return 1;
  }

  @RequestMapping("/todo/delete")
  public Object delet(int index) {
    //    if(index < 0 || index >= todoList.size) {
    //      return 0;
    //    }
    todoList.remove(index);
    return 1;
  }

  @RequestMapping("/todo/save")
  public Object save() throws Exception {
    ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("todos.ser")));

    out.writeObject(todoList);
    out.close();
    return todoList.size();
  }
}
