package com.bitcamp.mylist.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.mylist.domain.Board;

@Mapper
public interface BoardDao {

  int countAll();

  List<Board> findAll();

  int insert(Board board);

  Board findByNo(int no);

  int update(Board board);

  int delete(Board board); 

  int increaseViewCount(int no);
}
