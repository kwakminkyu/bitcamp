package com.bitcamp.mylist.controller;

import java.sql.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Board;
import com.bitcamp.util.ArrayList;

@RestController
public class BoardController {

  ArrayList boardList = new ArrayList();

  public BoardController() {
    System.out.println("BoardController() 호출됨!");
  }

  @GetMapping("/board/list")
  public Object list() {
    return boardList.toArray();
  }

  @GetMapping("/board/add")
  public Object add(Board board) {
    board.setCreateDate(new Date(System.currentTimeMillis()));
    boardList.add(board);
    return boardList.size();
  }

  @GetMapping("/board/get")
  public Object get(int index) {
    if(index < 0 || index >= boardList.size()) {
      return 0;
    }
    Board board = (Board) boardList.get(index);
    board.setViewCount(board.getViewCount() + 1);
    return board;
  }

  @GetMapping("/board/update")
  public Object update(int index, Board board) {
    if(index < 0 || index >= boardList.size()) {
      return 0;
    }
    Board old = (Board) boardList.get(index);
    board.setViewCount(old.getViewCount());
    board.setCreateDate(old.getCreateDate());
    return boardList.set(index, board) == null ? 0 : 1;
  }

  @GetMapping("/board/delete")
  public Object delet(int index) {
    //    if(index < 0 || index >= ArrayList.size) {
    //      return 0;
    //    }
    return boardList.remove(index) == null ? 0 : 1;
  }
}
