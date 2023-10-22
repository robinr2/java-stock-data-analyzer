/**
 * @author Robin Röschke
 */

package task_9;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is responsible for analyzing a file containing stock fund data.
 * It aims to extract the initial price (first day's price) and the year-end
 * price (last day's price) for the year 2018.
 * Subsequently, it compares these two prices and generates an output of "bull"
 * if the price has increased, or "bear" if the price has decreased.
 */
public class StockDataAnalyzer {
  static final String FILE_NAME = "DWS-TOP-DIVIDENDE.csv";
  static final String DATE_PATTERN = "dd.MM.yyyy";
  static final String SEPERATOR = ";";
  static final int YEAR = 2018;
  static final int LINES_TO_SKIP = 6;
  static final int NUMBER_OF_COLUMNS = 6;
  static final int OPENING_PRICE_COLUMN_INDEX = 1;
  static final int CLOSING_PRICE_COLUMN_INDEX = 4;

  /**
   * The main entry point for the application.
   * 
   * @param args
   */
  public static void main(String[] args) {
    try {
      SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);
      Date startDate = null;
      Date endDate = null;
      String[] startLineArray = null;
      String[] endLineArray = null;
      String currentLine = null;
      int lineCount = 0;

      BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
      while ((currentLine = reader.readLine()) != null) {
        // Skip lines with irrelevant information.
        if (lineCount++ <= LINES_TO_SKIP) {
          continue;
        }

        String[] currentLineArray = currentLine.split(SEPERATOR);

        boolean hasBlankColumn = false;
        for (String column : currentLineArray) {
          if (column.isBlank()) {
            hasBlankColumn = true;
            break;
          }
        }

        // Skip invalid lines.
        if (currentLineArray.length < NUMBER_OF_COLUMNS || hasBlankColumn) {
          System.err.printf("Überspringe Zeile %d: Ungültiges Format.\n", lineCount, currentLineArray.length,
              NUMBER_OF_COLUMNS);
          continue;
        }

        String currentDateString = currentLineArray[0];
        Date currentDate = dateFormatter.parse(currentDateString);

        // Future improvement: getYear() can be replaced by Calendar.
        int currentYear = currentDate.getYear() + 1900;
        if (currentYear != YEAR) {
          continue;
        }

        if (startDate == null || currentDate.before(startDate)) {
          startDate = currentDate;
          startLineArray = currentLineArray;
        }

        if (endDate == null || currentDate.after(endDate)) {
          endDate = currentDate;
          endLineArray = currentLineArray;
        }
      }

      if (startLineArray == null || endLineArray == null) {
        System.err.println("Es konnten keine Datensätze für das angegebene Jahr gefunden werden.");
        reader.close();
        return;
      }

      String startPriceString = startLineArray[OPENING_PRICE_COLUMN_INDEX].replace(",", ".");
      double startPrice = Double.parseDouble(startPriceString);

      String endPriceString = endLineArray[CLOSING_PRICE_COLUMN_INDEX].replace(",", ".");
      double endPrice = Double.parseDouble(endPriceString);

      String startDateString = startLineArray[0];
      String endDateString = endLineArray[0];
      String result = endPrice > startPrice ? "Bull" : "Bear";
      System.out.printf("%s (%.2f) - %s (%.2f) -> %s\n", startDateString, startPrice, endDateString, endPrice, result);

      reader.close();
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
    } catch (IOException e) {
      System.err.println(e.getMessage());
    } catch (ParseException e) {
      System.err.println(e.getMessage());
    } catch (NumberFormatException e) {
      System.err.println(e.getMessage());
    }
  }
}
