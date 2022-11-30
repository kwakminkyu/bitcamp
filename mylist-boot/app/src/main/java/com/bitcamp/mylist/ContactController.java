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
    // 배열이 꽉 찼는지 검사.
    if (size == contacts.length) {
      // 꽉 찼을 경우 매서드 실행.
      contacts = grow();
    }
    contacts[size++] = createCSV(name, email, tel, company);
    return size;
  }

  @GetMapping("/contact/get")
  public Object get(String email) {
    int index = indexOf(email);
    if(index == -1) {
      return 0;
    }
    return contacts[index];
  }

  @GetMapping("/contact/update")
  public Object update(String name, String email, String tel, String company) {
    int index = indexOf(email);
    if(index == -1) {
      return 0;
    }
    contacts[index] = createCSV(name,email,tel,company);
    return 1;
  }

  @GetMapping("/contact/delet")
  public Object delet(String email) {
    int index = indexOf(email);
    if(index == -1) {
      return 0;
    }
    remove(index);
    return 1;
  }

  //입력 받은 파라미터를 CSV형식의 문자열로 만들어준다.
  String createCSV(String name, String email, String tel, String company) {
    return name + "," + email + "," + tel + "," + company;
  }

  //이메일로 연락처 정보를 찾는다.
  int indexOf(String email) {
    for (int i = 0; i < size; i++) {
      if (contacts[i].split(",")[1].equals(email)) {
        return i;
      }
    }
    return -1;
  }

  //배열에서 지정한 항목을 삭제한다.
  String remove(int index) {
    String old = contacts[index];
    for (int i = index + 1; i < size; i++) {
      contacts[i-1] = contacts[i];
    }
    size--;
    return old;
  }

  // 기존 배열을 새 배열에 복사한다.
  String[] grow() {
    String[] arr = new String[newLength()];
    copy(contacts, arr);
    return arr;
  }
  // 배열을 크기를 늘린다.
  int newLength() {
    return contacts.length + (contacts.length >> 1);
  }
  // 배열을 복사한다.
  void copy(String[] source, String[] target) {
    // 개발자가 잘못 사용할 것을 대비한 코드를 추가한다.
    int length = source.length;
    if (target.length < source.length) {
      length = target.length;
    }
    for (int i = 0; i < length; i++) {
      target[i] = source[i];
    }
  }
}
