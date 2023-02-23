package com.bitcamp.mylist.domain;

import java.sql.Date;
import lombok.Data;

@Data
public class Book {
  int no;
  String title;
  String author;
  String press;
  int page;
  int price;
  Date readDate;
  String feed;
  String photo;
}