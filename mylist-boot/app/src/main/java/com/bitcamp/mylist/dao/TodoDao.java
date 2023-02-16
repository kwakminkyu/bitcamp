package com.bitcamp.mylist.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bitcamp.mylist.domain.Todo;

@Mapper
public interface TodoDao {

  int countAll();

  List<Todo> findAll();

  void insert(Todo todo);

  int update(Todo todo);

  int updateDone(@Param("no") int no, @Param("done") boolean done);

  int delete(int no);
}
