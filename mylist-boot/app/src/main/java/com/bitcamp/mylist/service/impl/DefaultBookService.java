package com.bitcamp.mylist.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.mylist.dao.BookDao;
import com.bitcamp.mylist.domain.Book;
import com.bitcamp.mylist.service.BookService;

@Service
public class DefaultBookService implements BookService {

  @Autowired
  BookDao bookDao;

  @Override
  @Transactional
  public int add(Book book) {
    bookDao.insert(book);
    return bookDao.countAll();
  }

  @Override
  public List<Book> list() {
    return bookDao.findAll();
  }

  @Override
  public Book get(int no) {
    return bookDao.findByNo(no);
  }

  @Override
  @Transactional
  public int update(Book book) {
    return bookDao.update(book);
  }

  @Override
  @Transactional
  public int delete(int no) {
    return bookDao.delete(no);
  }
}
