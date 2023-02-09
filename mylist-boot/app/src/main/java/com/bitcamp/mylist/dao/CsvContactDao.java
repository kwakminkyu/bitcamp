package com.bitcamp.mylist.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import com.bitcamp.mylist.domain.Contact;

//@Repository
public class CsvContactDao extends AbstractContactDao {

  String filname = "contacts.csv";

  public CsvContactDao() throws Exception {
    try {
      BufferedReader in = new BufferedReader(new FileReader(filname));

      String csvStr;
      while ((csvStr = in.readLine()) != null) {
        contactList.add(Contact.valueOf(csvStr)); 
      }
      in.close();
    } catch (Exception e) {
      System.out.println("연락처 로딩 중 오류 발생");
    }
  }

  @Override
  protected void save() throws Exception {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filname)));

    for (int i = 0; i < contactList.size(); i++) {
      Contact contact = (Contact) contactList.get(i);
      out.println(contact.toCsvString());
    }
    out.flush();
    out.close();
  }
}
