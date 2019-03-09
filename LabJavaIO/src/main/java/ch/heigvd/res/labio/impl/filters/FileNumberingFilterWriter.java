package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int line = 1;
  private int previous = 0;
  private static final char TABULATION = '\t';
  private static final char LINEFEED = '\n';
  private static final char CARRIAGERETURN = '\r';

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(),off,len);  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for (int j = 0; j < len; j++){
      write(cbuf[off + j]);
    }
  }

  @Override
  public void write(int c) throws IOException {

    String strToWrite = String.valueOf(line);

    if(line == 1){
      super.write(strToWrite,0, strToWrite.length());
      super.write(TABULATION);
      ++line;
      super.write(c);
    } else {
      //si c'est un mac (qui utilise le \r)
      if (previous == CARRIAGERETURN && c != LINEFEED) {
        super.write(strToWrite, 0, strToWrite.length());
        super.write(TABULATION);
        ++line;
      }

      super.write(c);

      //si c'est un windows ou unix (qui utilise le \n)
      if (c == LINEFEED) {
        super.write(strToWrite, 0, strToWrite.length());
        super.write(TABULATION);
        ++line;
      }
    }
    previous = c;
  }

}
