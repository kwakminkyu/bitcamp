package com.bitcamp.mylist.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Board;
import com.bitcamp.util.ArrayList;

@RestController
public class BoardController {

  ArrayList boardList;

  public BoardController() throws Exception {
    boardList = new ArrayList();
    System.out.println("BoardController() 호출됨!");

    FileInputStream in = new FileInputStream("boards.data");
    BufferedInputStream in1 = new BufferedInputStream(in);
    DataInputStream in2 = new DataInputStream(in1);

    String line;
    while (true) {
      try {
        Board board = new Board();
        board.setTitle(in2.readUTF());
        board.setContent(in2.readUTF());
        board.setViewCount(in2.readInt());
        board.setCreateDate(Date.valueOf(in2.readUTF()));

        boardList.add(board);
      } catch (Exception e) {
        break;
      }
      in2.close();
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
    FileOutputStream out = new FileOutputStream("boards.data");
    BufferedOutputStream out1 = new BufferedOutputStream(out);
    DataOutputStream out2 = new DataOutputStream(out1);

    Object[] arr = boardList.toArray();
    for (Object obj : arr) {
      Board board = (Board) obj;
      out2.writeUTF(board.getTitle());
      out2.writeUTF(board.getContent());
      out2.writeInt(board.getViewCount());
      out2.writeUTF(board.getCreateDate().toString());
    }
    out2.close();
    return arr.length;
  }
}
