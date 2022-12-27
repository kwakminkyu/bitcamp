package com.bitcamp.mylist;

public class ArrayList {

  Object[] list = new Object[5];
  int size = 0;

  // 기존 배열의 목록을 새 배열에 담는다.
  static Object[] toArray(ArrayList that) {
    Object[] arr = new Object[that.size];
    for (int i = 0; i < that.size; i++) {
      arr[i] = that.list[i];
    }
    return arr;
  }

  static void add(ArrayList that, Object obj) {
    // 배열이 꽉 찼는지 검사.
    if (that.size == that.list.length) {
      // 꽉 찼을 경우 매서드 실행.
      that.list = grow(that);
    }
    that.list[that.size++] = obj;
  }

  // 기존 배열을 새 배열에 복사한다.
  static Object[] grow(ArrayList that) {
    Object[] arr = new Object[newLength(that)];
    copy(that.list, arr);
    return arr;
  }

  // 배열을 크기를 늘린다.
  static int newLength(ArrayList that) {
    return that.list.length + (that.list.length >> 1);
  }

  // 배열을 복사한다.
  static void copy(Object [] source, Object[] target) {
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
  static Object remove(ArrayList that, int index) {
    if (index < 0 || index >= that.size) {
      return null;
    }
    Object old = that.list[index];
    for (int i = index + 1; i < that.size; i++) {
      that.list[i-1] = that.list[i];
    }
    that.size--;
    return old;
  }

  // 특정 위치에 배열의 값을 변경한다.
  // 변경전 값을 리턴
  static Object set(ArrayList that, int index, Object obj) {
    if (index < 0 || index >= that.size) {
      return null;
    }
    Object old = that.list[index];
    that.list[index] = obj;
    return old;
  }
}
