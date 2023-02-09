package com.bitcamp.mylist.dao;

import com.bitcamp.mylist.domain.Contact;

public interface ContactDao {

  int countAll();

  Object[] findAll();

  void insert(Contact contact) throws Exception;

  Contact findByEmail(String email);

  int update(Contact contact) throws Exception;

  int delete(String email) throws Exception; 
}
