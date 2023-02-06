package com.bitcamp.mylist.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Todo;
import com.bitcamp.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TodoController {

  ArrayList todoList;

  public TodoController() throws Exception {
    todoList = new ArrayList();
    System.out.println("TodoController() 호출됨!");

    try {
      BufferedReader in = (new BufferedReader(new FileReader("todos.json")));

      ObjectMapper mapper =new ObjectMapper();
      String jsonStr = in.readLine();
      Todo[] todos = mapper.readValue(jsonStr, Todo[].class);

      for (Todo todo : todos) {
        todoList.add(todo);
      }

      System.out.println(jsonStr);

      in.close();

    } catch (Exception e) {
      System.out.println("할 일 로딩 중 오류 발생");
    }
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
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("todos.json")));

    ObjectMapper mapper = new ObjectMapper();
    String jsonStr = mapper.writeValueAsString(todoList.toArray());
    out.println(jsonStr);

    out.close();
    return todoList.size();
  }
}
