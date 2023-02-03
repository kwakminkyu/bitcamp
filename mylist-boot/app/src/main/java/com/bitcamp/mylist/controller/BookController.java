package com.bitcamp.mylist.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Book;
import com.bitcamp.mylist.io.FileWriter2;
import com.bitcamp.util.ArrayList;

@RestController
public class BookController {

  ArrayList bookList;

  public BookController() throws Exception {
    bookList = new ArrayList();
    System.out.println("BookController() 호출됨!");

    com.bitcamp.mylist.io.FileReader2 in = new com.bitcamp.mylist.io.FileReader2("books.csv");

    String line;
    while ((line = in.readLine()).length() != 0) {
      bookList.add(Book.valueOf(line));
    }
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
    FileWriter2 out = new FileWriter2("books.csv");

    Object[] arr = bookList.toArray();
    for (Object obj : arr) {
      Book book = (Book) obj;
      out.println(book.toCsvString());
    }
    out.close();
    return 0;
  }
}
