package com.bitcamp.mylist.domain;

import java.util.List;
import lombok.Data;

@Data
public class Contact {
  int no;
  String name;
  String email;
  String company;
  List<ContactTel> tels;
}
