package com.bitcamp.mylist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

  ArrayList contacts = new ArrayList();

  @GetMapping("/contact/list")
  public Object list() {
    return ArrayList.toArray(contacts);
  }

  @GetMapping("/contact/add")
  public Object add(Contact contact) {
    ArrayList.add(contacts, contact);
    return contacts.size;
  }

  @GetMapping("/contact/get")
  public Object get(String email) {
    int index = indexOf(email);
    if(index == -1) {
      return "";
    }
    return contacts.list[index];
  }

  @GetMapping("/contact/update")
  public Object update(Contact contact) {
    int index = indexOf(contact.email);
    if(index == -1) {
      return 0;
    }
    return ArrayList.set(contacts, index, contact) == null ? 0 : 1;
  }

  @GetMapping("/contact/delete")
  public Object delet(String email) {
    int index = indexOf(email);
    if(index == -1) {
      return 0;
    }
    ArrayList.remove(contacts, index);
    return 1;
  }

  //이메일로 연락처 정보를 찾는다.
  int indexOf(String email) {
    for (int i = 0; i < contacts.size; i++) {
      if (((Contact) contacts.list[i]).email.equals(email)) {
        return i;
      }
    }
    return -1;
  }
}
