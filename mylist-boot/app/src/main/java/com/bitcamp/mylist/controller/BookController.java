package com.bitcamp.mylist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Book;
import com.bitcamp.util.ArrayList;

@RestController
public class BookController {

  ArrayList bookList = new ArrayList();

  public BookController() {
    System.out.println("BookController() 호출됨!");
  }

  @GetMapping("/book/list")
  public Object list() {
    return bookList.toArray();
  }

  @GetMapping("/book/add")
  public Object add(Book book) {
    bookList.add(book);
    return bookList.size();
  }

  @GetMapping("/book/get")
  public Object get(int index) {
    if(index < 0 || index >= bookList.size()) {
      return 0;
    }
    return bookList.get(index);
  }

  @GetMapping("/book/update")
  public Object update(int index, Book book) {
    if(index < 0 || index >= bookList.size()) {
      return 0;
    }
    return bookList.set(index, book) == null ? 0 : 1;
  }

  @GetMapping("/book/delete")
  public Object delet(int index) {
    if(index < 0 || index >= bookList.size()) {
      return 0;
    }
    return bookList.remove(index) == null ? 0 : 1;
  }
}
