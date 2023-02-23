package com.bitcamp.mylist.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;
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

  @Autowired
  BookService bookService;

  public BookController() throws Exception {
    System.out.println("BookController() 호출됨!");
  }

  @RequestMapping("/book/list")
  public Object list() {
    return bookService.list();
  }

  @RequestMapping("/book/add")
  public Object add(Book book, MultipartFile file) {
    try {
      book.setPhoto(saveFile(file));
      return bookService.add(book);
    } catch (Exception e) {
      e.printStackTrace();
      return "error";
    }
  }

  @RequestMapping("/book/get")
  public Object get(int no) {
    Book book = bookService.get(no);
    return book != null ? book : "";
  }

  @RequestMapping("/book/update")
  public Object update(Book book, MultipartFile file) {
    try {
      book.setPhoto(saveFile(file));
      return bookService.update(book);
    } catch (Exception e) {
      e.printStackTrace();
      return "error";
    }
  }

  @RequestMapping("/book/delete")
  public Object delete(int no) {
    return bookService.delete(no);
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