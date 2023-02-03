package com.bitcamp.mylist.controller;

import java.sql.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Board;
import com.bitcamp.mylist.io.FileWriter2;
import com.bitcamp.util.ArrayList;

@RestController
public class BoardController {

  ArrayList boardList;

  public BoardController() throws Exception {
    boardList = new ArrayList();
    System.out.println("BoardController() 호출됨!");

    com.bitcamp.mylist.io.FileReader2 in = new com.bitcamp.mylist.io.FileReader2("boards.csv");

    String line;
    while ((line = in.readLine()).length() != 0) {
      boardList.add(Board.valueOf(line));
    }
    in.close();
  }

  @RequestMapping("/board/list")
  public Object list() {
    return boardList.toArray();
  }

  @RequestMapping("/board/add")
  public Object add(Board board) {
    board.setCreateDate(new Date(System.currentTimeMillis()));
    boardList.add(board);
    return boardList.size();
  }

  @RequestMapping("/board/get")
  public Object get(int index) {
    if(index < 0 || index >= boardList.size()) {
      return 0;
    }
    Board board = (Board) boardList.get(index);
    board.setViewCount(board.getViewCount() + 1);
    return board;
  }

  @RequestMapping("/board/update")
  public Object update(int index, Board board) {
    if(index < 0 || index >= boardList.size()) {
      return 0;
    }
    Board old = (Board) boardList.get(index);
    board.setViewCount(old.getViewCount());
    board.setCreateDate(old.getCreateDate());
    return boardList.set(index, board) == null ? 0 : 1;
  }

  @RequestMapping("/board/delete")
  public Object delet(int index) {
    //    if(index < 0 || index >= ArrayList.size) {
    //      return 0;
    //    }
    return boardList.remove(index) == null ? 0 : 1;
  }

  @RequestMapping("/board/save")
  public Object save() throws Exception {
    FileWriter2 out = new FileWriter2("boards.csv");

    Object[] arr = boardList.toArray();
    for (Object obj : arr) {
      Board board = (Board) obj;
      out.println(board.toCsvString());
    }
    out.close();
    return 0;
  }
}
