package com.bitcamp.mylist.domain;

import java.sql.Date;

public class Board implements java.io.Serializable {
  String title;
  String content;
  int viewCount;
  java.sql.Date createDate;

  public Board() {
    System.out.println("Board() 호출됨");
  }

  public Board(String csvStr) {
    String[] values = csvStr.split(",");
    this.setTitle(values[0]); // 배열에 들어 있는 각 항목을 객체의 필드에 저장한다.
    this.setContent(values[1]);
    this.setViewCount(Integer.valueOf(values[2]));
    this.setCreateDate(Date.valueOf(values[3]));
  }

  public static Board valueOf(String csvStr) {
    String[] values = csvStr.split(",");

    Board board = new Board();
    board.setTitle(values[0]);
    board.setContent(values[1]);
    board.setViewCount(Integer.valueOf(values[2]));
    board.setCreateDate(Date.valueOf(values[3]));

    return board;
  }

  public String toCsvString() {
    return String.format("%s,%s,%s,%s",
        this.getTitle(),
        this.getContent(),
        this.getViewCount(),
        this.getCreateDate());
  }

  @Override
  public String toString() {
    return "Board [title=" + title + ", content=" + content + ", viewCount=" + viewCount
        + ", createDate=" + createDate + "]";
  }

  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
  public java.sql.Date getCreateDate() {
    return createDate;
  }
  public void setCreateDate(java.sql.Date createDate) {
    this.createDate = createDate;
  }
}
