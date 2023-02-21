package com.bitcamp.mylist.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import com.bitcamp.mylist.dao.ContactDao;
import com.bitcamp.mylist.domain.Contact;
import com.bitcamp.mylist.domain.ContactTel;

//@Service
public class TransactionManagerContactService {

  @Autowired
  ContactDao contactDao;

  @Autowired
  PlatformTransactionManager transactionManager;

  public int add(Contact contact) {
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("t1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    TransactionStatus status = transactionManager.getTransaction(def);

    try {
      contactDao.insert(contact);
      for (ContactTel tel : contact.getTels()){
        tel.setContactNo(contact.getNo());
        contactDao.insertTel(tel);
      }
      transactionManager.commit(status);
      return 1;
    } catch (Exception e) {
      transactionManager.rollback(status);
      throw e;
    }
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
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("t1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    TransactionStatus status = transactionManager.getTransaction(def);

    try {
      int count = contactDao.update(contact);
      if (count > 0) {
        contactDao.deleteTelByContactNo(contact.getNo()); 
        for (ContactTel tel : contact.getTels()){
          contactDao.insertTel(tel);
        }
      }
      transactionManager.commit(status);
      return count;
    } catch (Exception e) {
      transactionManager.rollback(status);
      throw e;
    }
  }

  public int delete(int no) {
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("t1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    TransactionStatus status = transactionManager.getTransaction(def);

    try {
      contactDao.deleteTelByContactNo(no);
      int count = contactDao.delete(no);

      transactionManager.commit(status);
      return count;
    } catch (Exception e) {
      transactionManager.rollback(status);
      throw e;
    }
  }
}
