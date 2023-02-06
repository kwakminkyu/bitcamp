package com.bitcamp.mylist.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Board;
import com.bitcamp.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class BoardController {

  ArrayList boardList;

  public BoardController() throws Exception {
    boardList = new ArrayList();
    System.out.println("BoardController() 호출됨!");

    try {
      BufferedReader in = (new BufferedReader(new FileReader("boards.json")));

      ObjectMapper mapper =new ObjectMapper();

      String jsonStr = in.readLine();

      Board[] boards = mapper.readValue(jsonStr, Board[].class);

      for (Board board : boards) {
        boardList.add(board);
      }

      System.out.println(jsonStr);

      in.close();

    } catch (Exception e) {
      System.out.println("게시판 로딩 중 오류 발생");
    }
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
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("boards.json")));

    ObjectMapper mapper = new ObjectMapper();

    String jsonStr = mapper.writeValueAsString(boardList.toArray());

    out.println(jsonStr);

    out.close();
    return boardList.size();
  }
}
