package com.bitcamp.mylist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.dao.ContactDao;
import com.bitcamp.mylist.domain.Contact;

@RestController
public class ContactController {

  @Autowired
  ContactDao contactDao;

  public ContactController() {
    System.out.println("ContactController() 호출됨!");
  }

  @RequestMapping("/contact/list")
  public Object list() {
    return contactDao.findAll();
  }

  @RequestMapping("/contact/add")
  public Object add(Contact contact) {
    contactDao.insert(contact);
    return contactDao.countAll();
  }

  @RequestMapping("/contact/get")
  public Object get(int no) {
    Contact contact = contactDao.findByNo(no);
    return contact != null ? contact : "";
  }

  @RequestMapping("/contact/update")
  public Object update(Contact contact) {
    return contactDao.update(contact);
  }

  @RequestMapping("/contact/delete")
  public Object delete(int no) {
    return contactDao.delete(no);
  }
}
