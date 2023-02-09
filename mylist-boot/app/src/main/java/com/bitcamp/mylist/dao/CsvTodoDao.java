package com.bitcamp.mylist.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import com.bitcamp.mylist.domain.Todo;

//@Repository
public class CsvTodoDao extends AbstractTodoDao {

  String filname = "todos.csv";

  public CsvTodoDao() throws Exception {
    try {
      BufferedReader in = new BufferedReader(new FileReader(filname));

      String csvStr;
      while ((csvStr = in.readLine()) != null) {
        todoList.add(Todo.valueOf(csvStr)); 
      }
      in.close();
    } catch (Exception e) {
      System.out.println("할 일 로딩 중 오류 발생");
    }
  }

  @Override
  protected void save() throws Exception {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filname)));

    for (int i = 0; i < todoList.size(); i++) {
      Todo todo = (Todo) todoList.get(i);
      out.println(todo.toCsvString());
    }
    out.flush();
    out.close();
  }
}
