package com.bitcamp.mylist.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import com.bitcamp.mylist.domain.Board;
import com.bitcamp.util.ArrayList;

public class BinaryBoardDao {

  String filename = "boards.bin";
  ArrayList boardList = new ArrayList();

  public BinaryBoardDao() throws Exception {
    try {
      DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));

      int len = in.readInt();

      for (int i = 0; i < len; i++) {
        Board board = new Board();
        board.setTitle(in.readUTF());
        board.setContent(in.readUTF());
        board.setViewCount(in.readInt());
        board.setCreateDate(Date.valueOf(in.readUTF()));

        boardList.add(board);
      }
      in.close();
    } catch (Exception e) {
      System.out.println("게시판 로딩 중 오류 발생");
    }
  }

  public void save() throws Exception {
    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));

    out.writeInt(boardList.size()); 
    for (int i = 0; i < boardList.size(); i++) {
      Board board = (Board) boardList.get(i);
      out.writeUTF(board.getTitle());
      out.writeUTF(board.getContent());
      out.writeInt(board.getViewCount());
      out.writeUTF(board.getCreateDate().toString());
    }
    out.flush();
    out.close();
  }

  public int countAll() {
    return boardList.size();
  }

  public Object[] findAll() {
    return boardList.toArray();
  }

  public void insert(Board board) throws Exception {
    boardList.add(board);
    save();
  }

  public Board findByNo(int no) {
    if(no < 0 || no >= boardList.size()) {
      return null;
    }
    return (Board) boardList.get(no);
  }

  public int update(int no, Board board) throws Exception {
    if(no < 0 || no >= boardList.size()) {
      return 0;
    }
    boardList.set(no, board);
    save();
    return 1;
  }

  public int delete(int no) throws Exception {
    if(no < 0 || no >= boardList.size()) {
      return 0;
    }
    boardList.remove(no);
    save();
    return 1;
  }

  public void increaseViewCount(int no) throws Exception {
    Board board = findByNo(no);
    board.setViewCount(board.getViewCount() + 1);
    save();
  }
}
