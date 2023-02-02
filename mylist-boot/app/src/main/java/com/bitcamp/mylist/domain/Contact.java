package com.bitcamp.mylist.domain;

public class Contact {
  String name;
  String email;
  String tel;
  String company;

  public Contact() {
    System.out.println("Contact() 호출됨");
  }

  public Contact(String csvStr) {
    String[] values = csvStr.split(",");
    this.setName(values[0]); // 배열에 들어 있는 각 항목을 객체의 필드에 저장한다.
    this.setEmail(values[1]);
    this.setTel(values[2]);
    this.setCompany(values[3]);
  }

  public static Contact valueOf(String csvStr) {
    String[] values = csvStr.split(",");

    Contact contact = new Contact();
    contact.setName(values[0]);
    contact.setEmail(values[1]);
    contact.setTel(values[2]);
    contact.setCompany(values[3]);

    return contact;
  }

  public String toCsvString() {
    return String.format("%s,%s,%s,%s",
        this.getName(),
        this.getEmail(),
        this.getTel(),
        this.getCompany());
  }

  @Override
  public String toString() {
    return "Contact [name=" + name + ", email=" + email + ", tel=" + tel + ", company=" + company
        + "]";
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getCompany() {
    return company;
  }
  public void setCompany(String company) {
    this.company = company;
  }
}
