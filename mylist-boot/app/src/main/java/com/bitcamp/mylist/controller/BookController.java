package com.bitcamp.mylist.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Book;
import com.bitcamp.util.ArrayList;

@RestController
public class BookController {

  ArrayList bookList;

  public BookController() throws Exception {
    bookList = new ArrayList();
    System.out.println("BookController() 호출됨!");

    ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("books.ser")));

    bookList = (ArrayList) in.readObject();
    in.close();
  }


  @RequestMapping("/book/list")
  public Object list() {
    return bookList.toArray();
  }

  @RequestMapping("/book/add")
  public Object add(Book book) {
    bookList.add(book);
    return bookList.size();
  }

  @RequestMapping("/book/get")
  public Object get(int index) {
    if(index < 0 || index >= bookList.size()) {
      return 0;
    }
    return bookList.get(index);
  }

  @RequestMapping("/book/update")
  public Object update(int index, Book book) {
    if(index < 0 || index >= bookList.size()) {
      return 0;
    }
    return bookList.set(index, book) == null ? 0 : 1;
  }

  @RequestMapping("/book/delete")
  public Object delet(int index) {
    if(index < 0 || index >= bookList.size()) {
      return 0;
    }
    return bookList.remove(index) == null ? 0 : 1;
  }

  @RequestMapping("/book/save")
  public Object save() throws Exception {
    ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("books.ser")));

    out.writeObject(bookList);
    out.close();
    return bookList.size();
  }
}