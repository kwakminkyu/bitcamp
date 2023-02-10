package com.bitcamp.mylist.dao;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.bitcamp.mylist.domain.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class JsonBookDao extends AbstractBookDao {

  String filename = "books.json";

  public JsonBookDao() throws Exception {
    try {
      ObjectMapper mapper = new ObjectMapper();
      bookList.addAll(mapper.readValue(new File(filename),
          mapper.getTypeFactory().constructCollectionType(List.class, Book.class)));
    } catch (Exception e) {
      System.out.println("게시판 로딩 중 오류 발생");
    }
  }

  @Override
  protected void save() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(new File(filename), bookList.toArray());
  }
}
