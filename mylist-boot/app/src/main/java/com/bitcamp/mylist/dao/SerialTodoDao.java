package com.bitcamp.mylist.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.bitcamp.util.ArrayList;

//@Repository
public class SerialTodoDao extends AbstractTodoDao {

  String filename = "todos.ser";

  public SerialTodoDao() throws Exception {
    try {
      ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));

      todoList = (ArrayList) in.readObject();
      in.close();
    } catch (Exception e) {
      System.out.println("할 일 로딩 중 오류 발생");
    }
  }

  @Override
  protected void save() throws Exception {
    ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));

    out.writeObject(todoList);
    out.flush();
    out.close();
  }
}