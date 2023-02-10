package com.bitcamp.mylist.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//@Repository
public class SerialContactDao extends AbstractContactDao {

  String filename = "contacts.ser";

  public SerialContactDao() throws Exception {
    try {
      ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));

      contactList = (ArrayList) in.readObject();
      in.close();
    } catch (Exception e) {
      System.out.println("연락처 로딩 중 오류 발생");
    }
  }

  @Override
  protected void save() throws Exception {
    ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));

    out.writeObject(contactList);
    out.flush();
    out.close();
  }
}