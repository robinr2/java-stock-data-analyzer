/**
 * @author Robin Röschke
 */

package task_4;

import java.io.*;

/**
 * This class represents a file reader that reads the file content byte by byte.
 */
public class ByteByByteFileReader {
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
    try {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

      System.out.print("Enter the filename: ");
      String fileName = bufferedReader.readLine();

      File file = new File(fileName);

      FileInputStream fileInputStream = new FileInputStream(file);
      int byteRead;

      while ((byteRead = fileInputStream.read()) != -1) {
        char character = encodeChar(byteRead);
        System.out.print(character);
      }

      fileInputStream.close();
    } catch (FileNotFoundException e) {
      System.err.println("Error: " + e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
