package com.bitcamp.mylist.service;

import java.util.List;
import com.bitcamp.mylist.domain.Board;

public interface BoardService {

  int add(Board board);

  List<Board> list();

  Board get(int no);

  int update(Board board);

  int delete(int no);
}
