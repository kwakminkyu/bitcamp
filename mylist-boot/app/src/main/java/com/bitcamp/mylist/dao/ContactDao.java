package com.bitcamp.mylist.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.mylist.domain.Contact;
import com.bitcamp.mylist.domain.ContactTel;

@Mapper
public interface ContactDao {

  int countAll();

  List<Contact> findAll();

  void insert(Contact contact);

  Contact findByNo(int no);

  Contact findByEmail(String email);

  List<Contact> findByName(String name);

  int update(Contact contact);

  int delete(int no);

  List<ContactTel> findTelByContactNo(int contactNo);

  int insertTel(ContactTel tel);

  int updateTel(ContactTel tel);

  int deleteTel(int telNo);

  int deleteTelByContactNo(int contactNo);
}