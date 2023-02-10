package com.bitcamp.mylist.dao;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.bitcamp.mylist.domain.Board;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class JsonBoardDao extends AbstractBoardDao {

  String filename = "boards.json";

  public JsonBoardDao() throws Exception {
    try {
      ObjectMapper mapper = new ObjectMapper();
      boardList.addAll(mapper.readValue(new File(filename),
          mapper.getTypeFactory().constructCollectionType(List.class, Board.class)));
    } catch (Exception e) {
      System.out.println("게시판 로딩 중 오류 발생");
    }
  }

  @Override
  protected void save() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(new File(filename), boardList.toArray());
  }
}
