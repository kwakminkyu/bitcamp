package com.bitcamp.mylist.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.mylist.domain.Contact;
import com.bitcamp.mylist.domain.ContactTel;
import com.bitcamp.mylist.service.ContactService;

@RestController
public class ContactController {

  @Autowired
  ContactService contactService;

  @Autowired
  TransactionTemplate transactionTelmplate;

  public ContactController() {
    System.out.println("ContactController() 호출됨!");
  }

  @RequestMapping("/contact/list")
  public Object list() {
    return contactService.list();
  }

  @RequestMapping("/contact/add")
  public Object add(Contact contact, String[] tel) {

    ArrayList<ContactTel> telList = new ArrayList<>();
    for (int i = 0; i < tel.length; i++) {
      String[] value = tel[i].split("_");
      if (value[1].length() == 0) {
        continue;
      }
      ContactTel contactTel = new ContactTel(Integer.parseInt(value[0]), value[1]);
      telList.add(contactTel);
    }
    contact.setTels(telList);

    return contactService.add(contact);

    //    class ContactAddTransaction implements TransactionCallback {
    //      @Override
    //      public Object doInTransaction(TransactionStatus status) {
    //        contactDao.insert(contact);
    //        for (int i = 0; i < tel.length; i++) {
    //          String[] value = tel[i].split("_");
    //          if (value[1].length() == 0) {
    //            continue;
    //          }
    //          contactDao.insertTel(new ContactTel(contact.getNo(), Integer.parseInt(value[0]), value[1]));
    //        }
    //        return 1;
    //      }
    //    }
    //    return transactionTelmplate.execute(new ContactAddTransaction());
  }

  @RequestMapping("/contact/get")
  public Object get(int no) {
    Contact contact = contactService.get(no);
    if (contact == null) {
      return "";
    }
    return contact;
  }

  @RequestMapping("/contact/update")
  public Object update(Contact contact, String[] tel) {
    ArrayList<ContactTel> telList = new ArrayList<>();
    for (int i = 0; i < tel.length; i++) {
      String[] value = tel[i].split("_");
      if (value[1].length() == 0) {
        continue;
      }
      ContactTel contactTel = new ContactTel(contact.getNo(), Integer.parseInt(value[0]), value[1]);
      telList.add(contactTel);
    }
    contact.setTels(telList);

    return contactService.update(contact);
  }

  @RequestMapping("/contact/delete")
  public Object delete(int no) {
    return contactService.delete(no);
  }
}
