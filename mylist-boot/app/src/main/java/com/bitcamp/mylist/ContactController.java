package com.bitcamp.mylist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

  @GetMapping("/contact/list")
  public Object list() {
    Contact[] list = new Contact[ArrayList.size];
    for(int i = 0; i < ArrayList.size; i++) {
      list[i] = ArrayList.contacts[i];
    }
    return list;
  }

  @GetMapping("/contact/add")
  public Object add(Contact contact) {
    // 배열이 꽉 찼는지 검사.
    if (ArrayList.size == ArrayList.contacts.length) {
      // 꽉 찼을 경우 매서드 실행.
      ArrayList.contacts = ArrayList.grow();
    }
    ArrayList.contacts[ArrayList.size++] = contact;
    return ArrayList.size;
  }

  @GetMapping("/contact/get")
  public Object get(String email) {
    int index = ArrayList.indexOf(email);
    if(index == -1) {
      return "";
    }
    return ArrayList.contacts[index];
  }

  @GetMapping("/contact/update")
  public Object update(Contact contact) {
    int index = ArrayList.indexOf(contact.email);
    if(index == -1) {
      return 0;
    }
    ArrayList.contacts[index] = contact;
    return 1;
  }

  @GetMapping("/contact/delet")
  public Object delet(String email) {
    int index = ArrayList.indexOf(email);
    if(index == -1) {
      return 0;
    }
    ArrayList.remove(index);
    return 1;
  }
}
