package com.bitcamp.mylist.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.mylist.dao.ContactDao;
import com.bitcamp.mylist.domain.Contact;
import com.bitcamp.mylist.domain.ContactTel;

@Service
public class DefaultContactService {

  @Autowired
  ContactDao contactDao;

  @Transactional
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

  @Transactional
  public int update(Contact contact) {
    int count = contactDao.update(contact);
    if (count > 0) {
      contactDao.deleteTelByContactNo(contact.getNo()); 
      for (ContactTel tel : contact.getTels()){
        contactDao.insertTel(tel);
      }
    }
    return count;
  }

  @Transactional
  public int delete(int no) {
    contactDao.deleteTelByContactNo(no);
    return contactDao.delete(no);
  }
}
