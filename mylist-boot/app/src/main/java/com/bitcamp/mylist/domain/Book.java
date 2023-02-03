package com.bitcamp.mylist.domain;

import java.sql.Date;

public class Book implements java.io.Serializable {
  String title;
  String author;
  String press;
  int page;
  int price;
  Date readDate;
  String feed;

  public Book() {
    System.out.println("Book() 호출됨");
  }

  public Book(String csvStr) {
    String[] values = csvStr.split(",");
    this.setTitle(values[0]); // 배열에 들어 있는 각 항목을 객체의 필드에 저장한다.
    this.setAuthor(values[1]);
    this.setPress(values[2]);
    this.setPage(Integer.valueOf(values[3]));
    this.setPrice(Integer.valueOf(values[4]));
    this.setReadDate(Date.valueOf(values[5]));
    this.setFeed((values[6]));
  }

  public static Book valueOf(String csvStr) {
    String[] values = csvStr.split(",");

    Book book = new Book();
    book.setTitle(values[0]);
    book.setAuthor(values[1]);
    book.setPress(values[2]);
    book.setPage(Integer.valueOf(values[3]));
    book.setPrice(Integer.valueOf(values[4]));
    book.setReadDate(Date.valueOf(values[5]));
    book.setFeed(values[6]);

    return book;
  }

  public String toCsvString() {
    return String.format("%s,%s,%s,%s,%s,%s,%s",
        this.getTitle(),
        this.getAuthor(),
        this.getPress(),
        this.getPage(),
        this.getPrice(),
        this.getReadDate(),
        this.getFeed());
  }

  @Override
  public String toString() {
    return "Book [title=" + title + ", author=" + author + ", press=" + press + ", page=" + page
        + ", price=" + price + ", readDate=" + readDate + ", feed=" + feed + "]";
  }

  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getAuthor() {
    return author;
  }
  public void setAuthor(String author) {
    this.author = author;
  }
  public String getPress() {
    return press;
  }
  public void setPress(String press) {
    this.press = press;
  }
  public int getPage() {
    return page;
  }
  public void setPage(int page) {
    this.page = page;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public Date getReadDate() {
    return readDate;
  }
  public void setReadDate(Date readDate) {
    this.readDate = readDate;
  }
  public String getFeed() {
    return feed;
  }
  public void setFeed(String feed) {
    this.feed = feed;
  }
}