package com.siziksu.tic_tac_toe.commons;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Strings {

  public static final String UTF_8 = "utf-8";
  public static final String ISO_8859_1 = "iso-8859-1";

  public static String getStringFromFile(Context context, String file, String charset) throws Exception {
    AssetManager manager = context.getAssets();
    InputStream stream = manager.open(file);
    String string = convertStreamToString(stream, charset);
    stream.close();
    return string;
  }

  public static String convertStreamToString(InputStream stream, String charset) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
    StringBuilder builder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      builder.append(line).append("\n");
    }
    builder.delete(builder.length() - 1, builder.length());
    return builder.toString();
  }
}
