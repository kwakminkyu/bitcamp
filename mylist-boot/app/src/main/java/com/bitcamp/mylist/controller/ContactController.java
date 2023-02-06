package com.bitcamp.mylist.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Contact;
import com.bitcamp.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ContactController {

  ArrayList contactList;

  public ContactController() throws Exception {
    contactList = new ArrayList();
    System.out.println("ContactController() 호출됨!");

    try {
      BufferedReader in = (new BufferedReader(new FileReader("contacts.json")));

      ObjectMapper mapper =new ObjectMapper();
      String jsonStr = in.readLine();
      Contact[] contacts = mapper.readValue(jsonStr, Contact[].class);

      for (Contact contact : contacts) {
        contactList.add(contact);
      }

      System.out.println(jsonStr);

      in.close();

    } catch (Exception e) {
      System.out.println("연락처 로딩 중 오류 발생");
    }
  }

  @RequestMapping("/contact/list")
  public Object list() {
    return contactList.toArray();
  }

  @RequestMapping("/contact/add")
  public Object add(Contact contact) {
    contactList.add(contact);
    return contactList.size();
  }

  @RequestMapping("/contact/get")
  public Object get(String email) {
    int index = indexOf(email);
    if(index == -1) {
      return "";
    }
    return contactList.get(index);
  }

  @RequestMapping("/contact/update")
  public Object update(Contact contact) {
    int index = indexOf(contact.getEmail());
    if(index == -1) {
      return 0;
    }
    return contactList.set(index, contact) == null ? 0 : 1;
  }

  @RequestMapping("/contact/delete")
  public Object delet(String email) {
    int index = indexOf(email);
    if(index == -1) {
      return 0;
    }
    contactList.remove(index);
    return 1;
  }

  @RequestMapping("/contact/save")
  public Object save() throws Exception {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contacts.json")));

    ObjectMapper mapper = new ObjectMapper();
    String jsonStr = mapper.writeValueAsString(contactList.toArray());
    out.println(jsonStr);

    out.close();
    return contactList.size();
  }

  //이메일로 연락처 정보를 찾는다.
  int indexOf(String email) {
    for (int i = 0; i < contactList.size(); i++) {
      Contact contact = (Contact) contactList.get(i);
      if (contact.getEmail().equals(email)) {
        return i;
      }
    }
    return -1;
  }
}
