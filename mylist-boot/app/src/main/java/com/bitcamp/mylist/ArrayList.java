package com.bitcamp.mylist;

public class ArrayList {

  static Object[] list = new Object[5];
  static int size = 0;

  // 기존 배열의 목록을 새 배열에 담는다.
  static Object[] toArray() {
    Object[] arr = new Object[size];
    for (int i = 0; i < size; i++) {
      arr[i] = list[i];
    }
    return arr;
  }

  static void add(Object obj) {
    // 배열이 꽉 찼는지 검사.
    if (size == list.length) {
      // 꽉 찼을 경우 매서드 실행.
      list = grow();
    }
    list[size++] = obj;
  }

  // 기존 배열을 새 배열에 복사한다.
  static Object[] grow() {
    Object[] arr = new Object[newLength()];
    copy(list, arr);
    return arr;
  }

  // 배열을 크기를 늘린다.
  static int newLength() {
    return list.length + (list.length >> 1);
  }

  // 배열을 복사한다.
  static void copy(Object[] source, Object[] target) {
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
  static Object remove(int index) {
    Object old = list[index];
    for (int i = index + 1; i < size; i++) {
      list[i-1] = list[i];
    }
    size--;
    return old;
  }

  // 특정 위치에 배열의 값을 변경한다.
  // 변경전 값을 리턴
  static Object set(int index, Object obj) {
    if (index < 0 || index >= size) {
      return null;
    }
    Object old = list[index];
    list[index] = obj;
    return old;
  }
}
