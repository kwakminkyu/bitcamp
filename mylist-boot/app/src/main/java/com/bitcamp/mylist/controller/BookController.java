package com.bitcamp.mylist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Book;
import com.bitcamp.mylist.service.BookService;

@RestController
public class BookController {

  @Autowired
  BookService bookService;

  public BookController() throws Exception {
    System.out.println("BookController() 호출됨!");
  }

  @RequestMapping("/book/list")
  public Object list() {
    return bookService.list();
  }

  @RequestMapping("/book/add")
  public Object add(Book book) {
    return bookService.add(book);
  }

  @RequestMapping("/book/get")
  public Object get(int no) {
    Book book = bookService.get(no);
    return book != null ? book : "";
  }

  @RequestMapping("/book/update")
  public Object update(Book book) {
    return bookService.update(book);
  }

  @RequestMapping("/book/delete")
  public Object delete(int no) {
    return bookService.delete(no);
  }
}