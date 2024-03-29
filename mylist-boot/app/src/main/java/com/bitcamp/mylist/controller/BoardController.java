package com.bitcamp.mylist.controller;

import static com.bitcamp.mylist.controller.ResultMap.FAIL;
import static com.bitcamp.mylist.controller.ResultMap.SUCCESS;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Board;
import com.bitcamp.mylist.domain.Member;
import com.bitcamp.mylist.service.BoardService;

@RestController
public class BoardController {

  private static final Logger log = LogManager.getLogger(BoardController.class);

  @Autowired
  BoardService boardService;

  @RequestMapping("/board/list")
  public Object list() {
    log.info("게시물 목록 조회");
    return new ResultMap().setStatus(SUCCESS).setData(boardService.list());
  }

  @RequestMapping("/board/add")
  public Object add(Board board, HttpSession session) {
    log.info("게시글 등록");
    log.debug(board);

    Member member = (Member) session.getAttribute("loginUser");
    board.setWriter(member);
    boardService.add(board);
    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/board/get")
  public Object get(int no) {
    Board board = boardService.get(no);
    if (board == null) {
      return new ResultMap().setStatus(FAIL).setData("해당 번호의 게시글이 없습니다.");
    }
    return new ResultMap().setStatus(SUCCESS).setData(board);
  }

  @RequestMapping("/board/update")
  public Object update(Board board, HttpSession session) {
    Member member = (Member) session.getAttribute("loginUser");
    board.setWriter(member);
    int count = boardService.update(board);

    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("게시글 번호가 유효하지 않거나 작성자가 아닙니다.");
    }
  }

  @RequestMapping("/board/delete")
  public Object delete(int no, HttpSession session) {
    Member member = (Member) session.getAttribute("loginUser");
    Board board = new Board();
    board.setNo(no);
    board.setWriter(member);

    int count = boardService.delete(board);

    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("게시글 번호가 유효하지 않거나 작성자가 아닙니다.");
    }
  }
}
