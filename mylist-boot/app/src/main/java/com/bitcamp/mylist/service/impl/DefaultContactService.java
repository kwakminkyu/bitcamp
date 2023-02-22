package com.bitcamp.mylist.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.mylist.dao.ContactDao;
import com.bitcamp.mylist.domain.Contact;
import com.bitcamp.mylist.service.ContactService;

@Service
public class DefaultContactService implements ContactService {

  @Autowired
  ContactDao contactDao;

  @Override
  @Transactional
  public int add(Contact contact) {
    contactDao.insert(contact);
    contactDao.insertTels(contact.getNo(), contact.getTels());
    return 1;
  }

  @Override
  public List<Contact> list() {
    return contactDao.findAll();
  }

  @Override
  public Contact get(int no) {
    return contactDao.findByNo(no);
  }

  @Override
  @Transactional
  public int update(Contact contact) {
    int count = contactDao.update(contact);
    if (count > 0) {
      contactDao.deleteTelByContactNo(contact.getNo()); 
      contactDao.insertTels(contact.getNo(), contact.getTels());
    }
    return count;
  }

  @Override
  @Transactional
  public int delete(int no) {
    contactDao.deleteTelByContactNo(no);
    return contactDao.delete(no);
  }
}
