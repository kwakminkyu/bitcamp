package com.bitcamp.util;

public class ArrayList {

  Object[] list = new Object[5];
  int size = 0;

  // 기존 배열의 목록을 새 배열에 담는다.
  public Object[] toArray() {
    Object[] arr = new Object[this.size];
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.list[i];
    }
    return arr;
  }

  public void add(Object obj) {
    // 배열이 꽉 찼는지 검사.
    if (this.size == this.list.length) {
      // 꽉 찼을 경우 매서드 실행.
      this.list = this.grow();
    }
    this.list[this.size++] = obj;
  }

  // 기존 배열을 새 배열에 복사한다.
  Object[] grow() {
    Object[] arr = new Object[this.newLength()];
    this.copy(arr);
    return arr;
  }

  // 배열을 크기를 늘린다.
  int newLength() {
    return this.list.length + (this.list.length >> 1);
  }

  // 배열을 복사한다.
  void copy(Object[] target) {
    // 개발자가 잘못 사용할 것을 대비한 코드를 추가한다.
    int length = this.list.length;
    if (target.length < this.list.length) {
      length = target.length;
    }
    for (int i = 0; i < length; i++) {
      target[i] = this.list[i];
    }
  }

  //배열에서 지정한 항목을 삭제한다.
  public Object remove(int index) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    Object old = this.list[index];
    for (int i = index + 1; i < this.size; i++) {
      this.list[i-1] = this.list[i];
    }
    this.size--;
    return old;
  }

  // 특정 위치에 배열의 값을 변경한다.
  // 변경전 값을 리턴
  public Object set(int index, Object obj) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    Object old = this.list[index];
    this.list[index] = obj;
    return old;
  }

  public int size() {
    return this.size;
  }

  public Object get(int index) {
    return this.list[index];
  }
}
