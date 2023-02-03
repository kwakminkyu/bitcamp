package com.bitcamp.mylist.io;

public class FileReader2 extends java.io.FileReader {

  public FileReader2(String filename) throws Exception {
    super(filename);
  }

  public String readLine() throws Exception {
    StringBuilder buf = new StringBuilder();
    int c;

    while ((c = this.read()) != -1) {
      if (c == '\n') {
        return buf.toString();
      } else if (c == '\r') {
      } else {
        buf.append((char) c);
      }
    }
    return buf.toString();
  }
}
