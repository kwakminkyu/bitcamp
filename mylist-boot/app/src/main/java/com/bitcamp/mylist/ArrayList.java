package com.bitcamp.mylist;

public class ArrayList {
  static Contact[] contacts = new Contact[5];
  static int size = 0;

  //이메일로 연락처 정보를 찾는다.
  static int indexOf(String email) {
    for (int i = 0; i < size; i++) {
      if (contacts[i].email.equals(email)) {
        return i;
      }
    }
    return -1;
  }

  // 기존 배열을 새 배열에 복사한다.
  static Contact[] grow() {
    Contact[] arr = new Contact[newLength()];
    copy(contacts, arr);
    return arr;
  }

  // 배열을 크기를 늘린다.
  static int newLength() {
    return contacts.length + (contacts.length >> 1);
  }

  // 배열을 복사한다.
  static void copy(Contact[] source, Contact[] target) {
    // 개발자가 잘못 사용할 것을 대비한 코드를 추가한다.
    int length = source.length;
    if (target.length < source.length) {
      length = target.length;
    }
    for (int i = 0; i < length; i++) {
      target[i] = source[i];
    }
  }

  //배열에서 지정한 항목을 삭제한다.
  static Contact remove(int index) {
    Contact old = contacts[index];
    for (int i = index + 1; i < size; i++) {
      contacts[i-1] = contacts[i];
    }
    size--;
    return old;
  }
}
