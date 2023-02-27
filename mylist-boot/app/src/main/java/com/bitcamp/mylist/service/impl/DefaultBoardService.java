package com.bitcamp.mylist.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.mylist.dao.BoardDao;
import com.bitcamp.mylist.domain.Board;
import com.bitcamp.mylist.service.BoardService;

@Service
public class DefaultBoardService implements BoardService {

  @Autowired
  BoardDao boardDao;

  @Override
  @Transactional
  public int add(Board board) {
    return boardDao.insert(board);
  }

  @Override
  public List<Board> list() {
    return boardDao.findAll();
  }

  @Override
  public Board get(int no) {
    boardDao.increaseViewCount(no);
    return boardDao.findByNo(no);
  }

  @Override
  @Transactional
  public int update(Board Board) {
    return boardDao.update(Board);
  }

  @Override
  @Transactional
  public int delete(Board board) {
    return boardDao.delete(board);
  }
}
