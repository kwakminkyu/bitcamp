package com.bitcamp.mylist.domain;

public class Board {
  int no;
  String title;
  String content;
  int viewCount;
  java.sql.Date createdDate;

  public Board() {
    System.out.println("Board() 호출됨");
  }
}
