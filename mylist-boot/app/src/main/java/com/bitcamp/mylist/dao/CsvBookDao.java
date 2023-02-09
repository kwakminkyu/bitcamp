package com.bitcamp.mylist.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import com.bitcamp.mylist.domain.Book;

//@Repository
public class CsvBookDao extends AbstractBookDao {

  String filname = "books.csv";

  public CsvBookDao() throws Exception {
    try {
      BufferedReader in = new BufferedReader(new FileReader(filname));

      String csvStr;
      while ((csvStr = in.readLine()) != null) {
        bookList.add(Book.valueOf(csvStr)); 
      }
      in.close();
    } catch (Exception e) {
      System.out.println("독서록 로딩 중 오류 발생");
    }
  }

  @Override
  protected void save() throws Exception {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filname)));

    for (int i = 0; i < bookList.size(); i++) {
      Book book = (Book) bookList.get(i);
      out.println(book.toCsvString());
    }
    out.flush();
    out.close();
  }
}
