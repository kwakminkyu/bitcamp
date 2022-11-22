package com.bitcamp.mylist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

  @GetMapping("/contact/list")
  public Object list() {
    String[] contacts = {
        "홍길동3,hong@test.com,010-0000-1111,비트캠프",
        "홍길동4,hong@test.com,010-0000-1111,비트캠프",
        "홍길동5,hong@test.com,010-0000-1111,비트캠프"
    };
    return contacts;
  }
}
