/**
 * @author Robin Röschke
 */

package task_6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * This class represents a file reader and a file writer.
 */
public class UTF8TextFileReaderAndWriter {
  /**
   * Encodes an integer to a character.
   * 
   * @param i
   * @return char
   */
  public static char encodeChar(int i) {
    return ((char) i);
  }

  /**
   * The main entry point for the application.
   * 
   * @param args
   */
  public static void main(String[] args) {
    // String fileName = "file-utf-8.txt";
    String fileName = "file-utf-16.txt";
    String text = "Die Welt kostet 17 €";

    try {
      FileOutputStream fileOutputStream = new FileOutputStream(fileName);

      // byte[] encodedText = text.getBytes(StandardCharsets.UTF_8);
      byte[] encodedText = text.getBytes(StandardCharsets.UTF_16BE);
      fileOutputStream.write(encodedText);
      fileOutputStream.close();

      FileInputStream in = new FileInputStream(fileName);
      int byteValue;
      while ((byteValue = in.read()) != -1) {
        System.out.print(byteValue + " ");
      }

      in.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
