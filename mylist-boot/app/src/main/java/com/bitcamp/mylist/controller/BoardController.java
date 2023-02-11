package com.bitcamp.mylist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.dao.BoardDao;
import com.bitcamp.mylist.domain.Board;

@RestController
public class BoardController {

  @Autowired
  BoardDao boardDao;

  public BoardController() throws Exception {
    System.out.println("BoardController() 호출됨!");
  }

  @RequestMapping("/board/list")
  public Object list() throws Exception {
    return boardDao.findAll();
  }

  @RequestMapping("/board/add")
  public Object add(Board board) throws Exception {
    return boardDao.insert(board);
  }

  @RequestMapping("/board/get")
  public Object get(int no) throws Exception {
    Board board = boardDao.findByNo(no);
    if(board == null) {
      return "";
    }
    boardDao.increaseViewCount(no);
    return board;
  }

  @RequestMapping("/board/update")
  public Object update(Board board) throws Exception {
    return boardDao.update(board);
  }

  @RequestMapping("/board/delete")
  public Object delete(int index) throws Exception {
    return boardDao.delete(index);
  }
}
