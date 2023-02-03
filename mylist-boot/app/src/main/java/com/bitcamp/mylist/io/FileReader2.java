package com.bitcamp.mylist.io;

import java.io.FileReader;

public class FileReader2 {

  FileReader in;

  public FileReader2(String filename) throws Exception {
    in = new FileReader(filename);
  }

  public String readLine() throws Exception {
    StringBuilder buf = new StringBuilder();
    int c;

    while ((c = in.read()) != -1) {
      if (c == '\n') {
        return buf.toString();
      } else if (c == '\r') {
      } else {
        buf.append((char) c);
      }
    }
    return buf.toString();
  }

  public void close() throws Exception {
    in.close();
  }
}
