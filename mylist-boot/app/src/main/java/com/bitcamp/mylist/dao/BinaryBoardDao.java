package com.bitcamp.mylist.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import com.bitcamp.mylist.domain.Board;

//@Repository
public class BinaryBoardDao extends AbstractBoardDao {

  String filename = "boards.bin";

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

  @Override
  protected void save() throws Exception {
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
}
