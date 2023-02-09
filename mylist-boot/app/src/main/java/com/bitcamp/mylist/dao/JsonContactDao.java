package com.bitcamp.mylist.dao;

import java.io.File;
import org.springframework.stereotype.Repository;
import com.bitcamp.mylist.domain.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class JsonContactDao extends AbstractContactDao {

  String filename = "contacts.json";

  public JsonContactDao() throws Exception {
    try {
      ObjectMapper mapper = new ObjectMapper();
      contactList.addAll(mapper.readValue(new File(filename), Contact[].class));
    } catch (Exception e) {
      System.out.println("연락처 로딩 중 오류 발생");
    }
  }

  @Override
  protected void save() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(new File(filename), contactList.toArray());
  }
}
