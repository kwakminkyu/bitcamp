package com.bitcamp.mylist.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import com.bitcamp.mylist.domain.Book;

//@Repository
public class BinaryBookDao extends AbstractBookDao {

  String filename = "books.bin";

  public BinaryBookDao() throws Exception {
    try {
      DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));

      int len = in.readInt();

      for (int i = 0; i < len; i++) {
        Book book = new Book();
        book.setTitle(in.readUTF());
        book.setAuthor(in.readUTF());
        book.setPress(in.readUTF());
        book.setPage(in.readInt());
        book.setPrice(in.readInt());
        book.setReadDate(Date.valueOf(in.readUTF()));
        book.setFeed(in.readUTF());

        bookList.add(book);
      }
      in.close();
    } catch (Exception e) {
      System.out.println("독서록 로딩 중 오류 발생");
    }
  }

  @Override
  protected void save() throws Exception {
    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));

    out.writeInt(bookList.size()); 
    for (int i = 0; i < bookList.size(); i++) {
      Book book = (Book) bookList.get(i);
      out.writeUTF(book.getTitle());
      out.writeUTF(book.getAuthor());
      out.writeUTF(book.getPress());
      out.writeInt(book.getPage());
      out.writeInt(book.getPrice());
      out.writeUTF(book.getReadDate().toString());
      out.writeUTF(book.getFeed());
    }
    out.flush();
    out.close();
  }
}
