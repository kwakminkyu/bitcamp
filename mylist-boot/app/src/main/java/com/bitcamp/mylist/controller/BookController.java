package com.bitcamp.mylist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.dao.BookDao;
import com.bitcamp.mylist.domain.Book;

@RestController
public class BookController {

  @Autowired
  BookDao bookDao;

  public BookController() throws Exception {
    System.out.println("BookController() 호출됨!");
  }


  @RequestMapping("/book/list")
  public Object list() {
    return bookDao.findAll();
  }

  @RequestMapping("/book/add")
  public Object add(Book book) throws Exception {
    bookDao.insert(book);
    return bookDao.countAll();
  }

  @RequestMapping("/book/get")
  public Object get(int index) {
    return bookDao.findByNo(index);
  }

  @RequestMapping("/book/update")
  public Object update(int index, Book book) throws Exception {
    return bookDao.update(index, book);
  }

  @RequestMapping("/book/delete")
  public Object delete(int index) throws Exception{
    return bookDao.delete(index);
  }
}