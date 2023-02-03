package com.bitcamp.mylist.io;

import java.io.FileWriter;

public class FileWriter2 extends FileWriter {

  public FileWriter2(String filename) throws Exception {
    super(filename);
  }

  public void println(String str) throws Exception {
    this.write(str + '\n');
  }
}
