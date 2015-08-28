package com.himanshuvirmani.androidreactivearch.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by himanshu.virmani on 13/03/15.
 */

public class FileLog {
  private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
  private static final String MSG_FORMAT = "%s: %s - %s";  // timestamp, tag, message

  private static String sLogFilePath;
  private static File sTheLogFile;
  private static BufferedWriter sBufferedWriter;
  private static int sCurrentPriority;
  private static int sFileSizeLimit;                  // bytes

  public static void open(String logFilePath, int currentPriority, int fileSizeLimit) {
    sLogFilePath = logFilePath;
    sCurrentPriority = currentPriority;
    sFileSizeLimit = fileSizeLimit;

    sTheLogFile = new File(sLogFilePath);
    if (!sTheLogFile.exists()) {
      try {
        sTheLogFile.createNewFile();
      } catch (IOException e) {
        Log.e(android.util.Log.getStackTraceString(e));
      }
    }

    checkFileSize();

    try {
      sBufferedWriter = new BufferedWriter(new FileWriter(sTheLogFile, true));
    } catch (IOException e) {
      Log.e(android.util.Log.getStackTraceString(e));
    }
  }

  public static void setCurrentPriority(int currentPriority) {
    sCurrentPriority = currentPriority;
  }

  public static void close() {
    try {
      if (sBufferedWriter != null) {
        sBufferedWriter.newLine();
        sBufferedWriter.flush();
        sBufferedWriter.close();
      }
    } catch (IOException e) {
      Log.e(android.util.Log.getStackTraceString(e));
    }
  }

  public static void delete() {
    close();

    if (sTheLogFile != null) {
      sTheLogFile.delete();
    }
  }

  public static void v(String tag, String msg) {
    writeToFile(android.util.Log.VERBOSE, tag, msg);

    Log.v(msg);
  }

  public static void v(String tag, String msg, Throwable tr) {
    writeToFile(android.util.Log.VERBOSE, tag, msg, tr);

    Log.v(msg, tr);
  }

  public static void d(String tag, String msg) {
    writeToFile(android.util.Log.DEBUG, tag, msg);

    Log.d(msg);
  }

  public static void d(String tag, String msg, Throwable tr) {
    writeToFile(android.util.Log.DEBUG, tag, msg, tr);

    Log.d(msg, tr);
  }

  public static void i(String tag, String msg) {
    writeToFile(android.util.Log.INFO, tag, msg);

    Log.i(msg);
  }

  public static void i(String tag, String msg, Throwable tr) {
    writeToFile(android.util.Log.INFO, tag, msg, tr);

    Log.i(msg, tr);
  }

  public static void w(String tag, String msg) {
    writeToFile(android.util.Log.WARN, tag, msg);

    Log.w(msg);
  }

  public static void w(String tag, String msg, Throwable tr) {
    writeToFile(android.util.Log.WARN, tag, msg, tr);

    Log.w(msg, tr);
  }

  public static void w(String tag, Throwable tr) {
    writeToFile(android.util.Log.WARN, tag, "", tr);

    Log.w(tag, tr);
  }

  public static void e(String tag, String msg) {
    writeToFile(android.util.Log.ERROR, tag, msg);

    Log.e(msg);
  }

  public static void e(String tag, String msg, Throwable tr) {
    writeToFile(android.util.Log.ERROR, tag, msg, tr);

    Log.e(msg, tr);
  }

  public static String getStackTraceString(Throwable tr) {
    return android.util.Log.getStackTraceString(tr);
  }

  private static void writeToFile(int priority, String tag, String msg) {
    writeToFile(priority, tag, msg, null);
  }

  private static void writeToFile(int priority, String tag, String msg, Throwable tr) {
    if ((priority >= sCurrentPriority) && (sBufferedWriter != null)) {
      try {
        if (checkFileSize()) {
          sBufferedWriter = new BufferedWriter(new FileWriter(sTheLogFile, true));
        }

        sBufferedWriter.write(formatMsg(tag, msg));
        sBufferedWriter.newLine();

        if (tr != null) {
          sBufferedWriter.write(android.util.Log.getStackTraceString(tr));
          sBufferedWriter.newLine();
        }

        sBufferedWriter.flush();
      } catch (IOException e) {
        Log.e(android.util.Log.getStackTraceString(e));
      }
    }

    if (sBufferedWriter == null) {
      Log.e("You have to call FileLog.open(...) before starting to log");
    }
  }

  private static String formatMsg(String tag, String msg) {
    return String.format(MSG_FORMAT, getCurrentTimeStamp(), tag, msg);
  }

  private static String getCurrentTimeStamp() {
    String currentTimeStamp = null;

    try {
      SimpleDateFormat dateFormat =
          new SimpleDateFormat(TIMESTAMP_FORMAT, java.util.Locale.getDefault());
      currentTimeStamp = dateFormat.format(new Date());
    } catch (Exception e) {
      Log.e(android.util.Log.getStackTraceString(e));
    }

    return currentTimeStamp;
  }

  private static boolean checkFileSize() {
    boolean createdNewLogFile = false;
    try {
      if (sTheLogFile.length() > sFileSizeLimit) {
        File to = new File(sLogFilePath + ".old");
        if (to.exists()) {
          to.delete();
        }

        sTheLogFile.renameTo(to);

        sTheLogFile = new File(sLogFilePath);
        sTheLogFile.createNewFile();

        createdNewLogFile = true;
      }
    } catch (IOException e) {
      Log.e(android.util.Log.getStackTraceString(e));
    }

    return createdNewLogFile;
  }
}
