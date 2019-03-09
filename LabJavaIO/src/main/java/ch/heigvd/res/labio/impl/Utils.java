package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());
  private static final String WINDOWS_SEPARATOR = "\r\n";
  private static final String UNIX_SEPARATOR = "\n";
  private static final String MAC_SEPARATOR = "\r";
  private static final int NB_ELEMENT = 2;
  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
    String[] result = new String[NB_ELEMENT];

    if (lines.contains(WINDOWS_SEPARATOR)){
      result = lines.split(WINDOWS_SEPARATOR, NB_ELEMENT);
      result[0] += WINDOWS_SEPARATOR;
    } else if (lines.contains(MAC_SEPARATOR)){
      result = lines.split(MAC_SEPARATOR, NB_ELEMENT);
      result[0] += MAC_SEPARATOR;
    } else if (lines.contains(UNIX_SEPARATOR)){
      result = lines.split(UNIX_SEPARATOR, NB_ELEMENT);
      result[0] += UNIX_SEPARATOR;
    } else {
      result[0] = "";
      result[1] = lines;
    }
    return result;
  }

}
