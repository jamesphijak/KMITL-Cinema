/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class File_TP {
 
 public static void main(String[] args) throws IOException {
  
  FileInputStream in = new FileInputStream("C:\\Users\\Phijak\\Pictures\\FullSizeRender.jpg");
 FileOutputStream ou = new FileOutputStream("C:\\Users\\Phijak\\Documents\\GitHub\\OOAD\\KMITL Cinema\\src\\cinema\\posterImage\\James.jpg");
  
  BufferedInputStream bin = new BufferedInputStream(in);
  BufferedOutputStream bou = new BufferedOutputStream(ou);
  int b=0;
  while(b!=-1){
   b=bin.read();
   bou.write(b);
  }
  bin.close();
  bou.close();
  
 }

}
