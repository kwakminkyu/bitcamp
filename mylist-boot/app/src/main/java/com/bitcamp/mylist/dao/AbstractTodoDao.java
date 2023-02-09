package com.bitcamp.mylist.dao;

import com.bitcamp.mylist.domain.Todo;
import com.bitcamp.util.ArrayList;

public abstract class AbstractTodoDao implements TodoDao {

  protected ArrayList todoList = new ArrayList();

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
    Todo old = (Todo) todoList.get(no);
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
    ((Todo)todoList.get(no)).setDone(done);
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
