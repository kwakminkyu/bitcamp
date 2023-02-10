package com.bitcamp.mylist.dao;

import java.util.ArrayList;
import com.bitcamp.mylist.domain.Todo;

public abstract class AbstractTodoDao implements TodoDao {

  protected ArrayList<Todo> todoList = new ArrayList<>();

  protected abstract void save() throws Exception;

  @Override
  public int countAll() {
    return todoList.size();
  }

  @Override
  public Object[] findAll() {
    return todoList.toArray();
  }

  @Override
  public void insert(Todo todo) throws Exception {
    todoList.add(todo);
    save();
  }

  @Override
  public int update(int no, Todo todo) throws Exception {
    if(no < 0 || no >= todoList.size()) {
      return 0;
    }
    Todo old = todoList.get(no);
    todo.setDone(old.isDone());
    todoList.set(no, todo);
    save();
    return 1;
  }

  @Override
  public Object check(int no, boolean done) {
    if(no < 0 || no >= todoList.size()) {
      return 0;
    }
    todoList.get(no).setDone(done);
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    if(no < 0 || no >= todoList.size()) {
      return 0;
    }
    todoList.remove(no);
    save();
    return 1;
  }
}
