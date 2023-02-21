package com.bitcamp.mylist.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitcamp.mylist.dao.ContactDao;
import com.bitcamp.mylist.domain.Contact;
import com.bitcamp.mylist.domain.ContactTel;

//@Service
public class NonTransactionContactService {

  @Autowired
  ContactDao contactDao;

  public int add(Contact contact) {
    contactDao.insert(contact);
    for (ContactTel tel : contact.getTels()){
      tel.setContactNo(contact.getNo());
      contactDao.insertTel(tel);
    }
    return 1;
  }

  public List<Contact> list() {
    List<Contact> contacts = contactDao.findAll();
    for (Contact contact : contacts) {
      contact.setTels(contactDao.findTelByContactNo(contact.getNo()));
    }
    return contacts;
  }

  public Contact get(int no) {
    Contact contact = contactDao.findByNo(no);
    if (contact != null) {
      contact.setTels(contactDao.findTelByContactNo(no));
    }
    return contact;
  }

  public int update(Contact contact) {
    int count = contactDao.update(contact);
    if (count > 0) {
      contactDao.deleteTelByContactNo(contact.getNo());
      for (ContactTel tel : contact.getTels()) {
        contactDao.insertTel(tel);
      }
    }
    return count;
  }

  public int delete(int no) {
    contactDao.deleteTelByContactNo(no);
    return contactDao.delete(no);
  }
}
