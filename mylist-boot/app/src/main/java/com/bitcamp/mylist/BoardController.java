package com.bitcamp.mylist;

import java.sql.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

  ArrayList boardList = new ArrayList();

  @GetMapping("/board/list")
  public Object list() {
    return ArrayList.toArray(boardList);
  }

  @GetMapping("/board/add")
  public Object add(Board board) {
    board.setCreateDate(new Date(System.currentTimeMillis()));
    ArrayList.add(boardList, board);
    return boardList.size;
  }

  @GetMapping("/board/get")
  public Object get(int index) {
    if(index < 0 || index >= boardList.size) {
      return 0;
    }
    Board board = (Board) boardList.list[index];
    board.viewCount++;
    return board;
  }

  @GetMapping("/board/update")
  public Object update(int index, Board board) {
    if(index < 0 || index >= boardList.size) {
      return 0;
    }
    Board old = (Board) boardList.list[index];
    board.viewCount = old.viewCount;
    board.createDate = old.createDate;
    return ArrayList.set(boardList, index, board) == null ? 0 : 1;
  }

  @GetMapping("/board/delete")
  public Object delet(int index) {
    //    if(index < 0 || index >= ArrayList.size) {
    //      return 0;
    //    }
    return ArrayList.remove(boardList, index) == null ? 0 : 1;
  }
}
