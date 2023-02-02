package com.bitcamp.mylist.controller;

import java.io.FileWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Contact;
import com.bitcamp.util.ArrayList;

@RestController
public class ContactController {

  ArrayList contactList = new ArrayList();

  public ContactController() {
    System.out.println("ContactController() 호출됨!");
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
    FileWriter out = new FileWriter("contacts.csv");
    Object[] arr = contactList.toArray();
    for (Object obj : arr) {
      Contact contact = (Contact) obj;
      out.write(contact.getName() + "," +
          contact.getEmail() + "," +
          contact.getTel() + "," +
          contact.getCompany() +
          "\n");
    }
    out.close();
    return 0;
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
