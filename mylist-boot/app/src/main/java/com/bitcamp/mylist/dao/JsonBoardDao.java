package com.bitcamp.mylist.dao;

import java.io.File;
import org.springframework.stereotype.Repository;
import com.bitcamp.mylist.domain.Board;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class JsonBoardDao extends AbstractBoardDao {

  String filename = "boards.json";

  public JsonBoardDao() throws Exception {
    try {
      ObjectMapper mapper = new ObjectMapper();
      boardList.addAll(mapper.readValue(new File(filename), Board[].class));
    } catch (Exception e) {
      System.out.println("게시판 로딩 중 오류 발생");
    }
  }

  @Override
  protected void save() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(new File(filename), boardList.toArray());
  }

  @Override
  public int countAll() {
    return boardList.size();
  }

  @Override
  public Object[] findAll() {
    return boardList.toArray();
  }

  @Override
  public void insert(Board board) throws Exception {
    boardList.add(board);
    save();
  }

  @Override
  public Board findByNo(int no) {
    if(no < 0 || no >= boardList.size()) {
      return null;
    }
    return (Board) boardList.get(no);
  }

  @Override
  public int update(int no, Board board) throws Exception {
    if(no < 0 || no >= boardList.size()) {
      return 0;
    }
    boardList.set(no, board);
    save();
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    if(no < 0 || no >= boardList.size()) {
      return 0;
    }
    boardList.remove(no);
    save();
    return 1;
  }

  @Override
  public void increaseViewCount(int no) throws Exception {
    Board board = findByNo(no);
    board.setViewCount(board.getViewCount() + 1);
    save();
  }
}
