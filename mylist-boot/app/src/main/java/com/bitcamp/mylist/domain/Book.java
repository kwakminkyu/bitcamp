package com.bitcamp.mylist.domain;

import java.sql.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
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