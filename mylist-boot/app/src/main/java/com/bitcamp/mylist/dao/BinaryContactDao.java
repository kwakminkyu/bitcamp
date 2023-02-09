package com.bitcamp.mylist.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import com.bitcamp.mylist.domain.Contact;

//@Repository
public class BinaryContactDao extends AbstractContactDao {

  String filename = "contacts.bin";

  public BinaryContactDao() throws Exception {
    try {
      DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));

      int len = in.readInt();

      for (int i = 0; i < len; i++) {
        Contact contact = new Contact();
        contact.setName(in.readUTF());
        contact.setEmail(in.readUTF());
        contact.setTel(in.readUTF());
        contact.setCompany(in.readUTF());

        contactList.add(contact);
      }
      in.close();
    } catch (Exception e) {
      System.out.println("연락처 로딩 중 오류 발생");
    }
  }

  @Override
  protected void save() throws Exception {
    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));

    out.writeInt(contactList.size()); 
    for (int i = 0; i < contactList.size(); i++) {
      Contact contact = (Contact) contactList.get(i);
      out.writeUTF(contact.getName());
      out.writeUTF(contact.getEmail());
      out.writeUTF(contact.getTel());
      out.writeUTF(contact.getCompany());
    }
    out.flush();
    out.close();
  }
}
