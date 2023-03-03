package com.bitcamp.mylist.controller;

import static com.bitcamp.mylist.controller.ResultMap.FAIL;
import static com.bitcamp.mylist.controller.ResultMap.SUCCESS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Todo;
import com.bitcamp.mylist.service.TodoService;

@RestController
public class TodoController {

  @Autowired
  TodoService todoService;

  public TodoController() throws Exception {
    System.out.println("TodoController() 호출됨!");
  }

  @RequestMapping("/todo/list")
  public Object list() {
    todoService.list();
    return new ResultMap().setStatus(SUCCESS).setData(todoService.list());
  }

  @RequestMapping("/todo/add")
  public Object add(Todo todo) {
    todoService.add(todo);
    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/todo/update")
  public Object update(Todo todo) {
    int count = todoService.update(todo);
    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("할 일 번호가 유효하지 않거나 할 일 등록자가 아닙니다.");
    }
  }

  @RequestMapping("/todo/check")
  public Object check(int no, boolean done) {
    int count = todoService.updateStatus(new Todo(no, done));
    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("할 일 번호가 유효하지 않거나 할 일 등록자가 아닙니다.");
    }
  }

  @RequestMapping("/todo/delete")
  public Object delete(int no) {
    int count = todoService.delete(no);
    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("할 일 번호가 유효하지 않거나 할 일 등록자가 아닙니다.");
    }
  }
}
