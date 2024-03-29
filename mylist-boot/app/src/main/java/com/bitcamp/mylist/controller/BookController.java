package com.bitcamp.mylist.controller;

import static com.bitcamp.mylist.controller.ResultMap.FAIL;
import static com.bitcamp.mylist.controller.ResultMap.SUCCESS;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.bitcamp.mylist.domain.Book;
import com.bitcamp.mylist.service.BookService;
import net.coobird.thumbnailator.Thumbnails;

@RestController
public class BookController {

  private static final Logger log = LogManager.getLogger(BookController.class);

  @Autowired
  BookService bookService;

  @RequestMapping("/book/list")
  public Object list() {
    return new ResultMap().setStatus(SUCCESS).setData(bookService.list());
  }

  @RequestMapping("/book/add")
  public Object add(Book book, MultipartFile file) {
    try {
      book.setPhoto(saveFile(file));
      bookService.add(book);
      return new ResultMap().setStatus(SUCCESS);

    } catch (Exception e) {
      StringWriter out = new StringWriter();
      e.printStackTrace(new PrintWriter(out));
      log.error(out.toString());

      return new ResultMap().setStatus(FAIL);
    }
  }

  @RequestMapping("/book/get")
  public Object get(int no) {
    Book book = bookService.get(no);
    if (book == null) {
      return new ResultMap().setStatus(FAIL).setData("해당 번호의 독서록이 없습니다.");
    }
    return new ResultMap().setStatus(SUCCESS).setData(book);
  }

  @RequestMapping("/book/update")
  public Object update(Book book, MultipartFile file) {
    try {
      book.setPhoto(saveFile(file));
      int count = bookService.update(book);
      if (count == 1) {
        return new ResultMap().setStatus(SUCCESS);
      } else {
        return new ResultMap().setStatus(FAIL).setData("독서록 번호가 유효하지 않거나 작성자가 아닙니다.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new ResultMap().setStatus(FAIL).setData(e.getMessage());
    }
  }

  @RequestMapping("/book/delete")
  public Object delete(int no) {
    int count = bookService.delete(no);
    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("독서록 번호가 유효하지 않거나 작성자가 아닙니다.");
    }
  }

  @RequestMapping("/book/photo")
  public ResponseEntity<Resource> photo(String filename) {
    try {
      File downloadFile = new File("./upload/book/" + filename); // 다운로드 상대 경로 준비
      FileInputStream fileIn = new FileInputStream(downloadFile.getCanonicalPath()); // 다운로드 파일의 실제 경로를 지정하여 입력 스트림 준비
      InputStreamResource resource = new InputStreamResource(fileIn); // 입력 스트림을 입력 자원으로 포장

      HttpHeaders header = new HttpHeaders();
      header.add("Cache-Control", "no-cache, no-store, must-revalidate");
      header.add("Pragma", "no-cache");
      header.add("Expires", "0");
      header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

      return ResponseEntity.ok()
          .headers(header)
          .contentLength(downloadFile.length())
          .contentType(MediaType.APPLICATION_OCTET_STREAM)
          .body(resource);

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private String saveFile(MultipartFile file) throws Exception {
    if (file != null && file.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      int dotIndex = file.getOriginalFilename().lastIndexOf(".");
      if (dotIndex != -1) {
        filename += file.getOriginalFilename().substring(dotIndex);
      }
      File photoFile = new File("./upload/book/" + filename);
      file.transferTo(photoFile.getCanonicalFile());

      Thumbnails.of(photoFile)
      .size(50, 50)
      .outputFormat("jpg")
      .toFile(new File("./upload/book/" + "50x50_" + filename));

      return filename;
    } else {
      return null;
    } 
  }
}