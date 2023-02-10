package com.bitcamp.mylist.dao;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.bitcamp.mylist.domain.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class JsonTodoDao extends AbstractTodoDao {

  String filename = "todos.json";

  public JsonTodoDao() throws Exception {
    try {
      ObjectMapper mapper = new ObjectMapper();
      todoList.addAll(mapper.readValue(new File(filename), 
          mapper.getTypeFactory().constructCollectionType(List.class, Todo.class)));
    } catch (Exception e) {
      System.out.println("할 일 로딩 중 오류 발생");
    }
  }

  @Override
  protected void save() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(new File(filename), todoList.toArray());
  }
}
