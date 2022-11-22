package com.bitcamp.mylist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

  String[] contacts = new String[5];
  int size = 0;

  @GetMapping("/contact/list")
  public Object list() {
    String[] list = new String[size];
    for(int i = 0; i < size; i++) {
      list[i] = contacts[i];
    }
    return list;
  }

  @GetMapping("/contact/add")
  public Object add(String name, String email, String tel, String company) {
    contacts[size++] = name + "," + email + "," + tel + "," + company;
    return size;
  }

  @GetMapping("/contact/get")
  public Object get(String email) {
    for (int i = 0; i < size; i++) {
      if (email.equals(contacts[i].split(",")[1])) {
        return contacts[i];
      }
    }
    return 1;
  }

  @GetMapping("/contact/update")
  public Object update(String name, String email, String tel, String company) {
    String contact = name + "," + email + "," + tel + "," + company;
    for (int i = 0; i < size; i++) {
      if (email.equals(contacts[i].split(",")[1])) {
        contacts[i] = contact;
      }
    }
    return 1;
  }

  @GetMapping("/contact/delet")
  public Object delet(String email) {
    for (int i = 0; i < size; i++) {
      if (email.equals(contacts[i].split(",")[1])) {
        for (int j = i + 1; j < size; j++) {
          contacts[j - 1] = contacts[j];
        }
        size--;
      }
    }
    return 1;
  }
}
