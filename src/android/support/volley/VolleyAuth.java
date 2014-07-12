/*
 * The MIT License (MIT)
 * Copyright (c) 2014 longkai
 * The software shall be used for good, not evil.
 */

package android.support.volley;

import java.util.Map;

/**
 * Simple Auth headers for the volley request auth.
 *
 * @author longkai
 */
public class VolleyAuth {
  Map<String, String> headers;

  private static VolleyAuth instance;

  private VolleyAuth() {}

  public static Map<String, String> getAuthHeaders() {
    if (instance == null || instance.headers == null) {
      throw new RuntimeException("You must provider your auth header before using!");
    }
    return instance.headers;
  }

  public static void setup(Map<String, String> headers) {
    if (instance != null) {
      throw new RuntimeException("You have been set up already!");
    }
    instance = new VolleyAuth();
    instance.headers = headers;
  }

  public static void reset(Map<String, String> headers) {
    if (instance == null) {
      throw new RuntimeException("You must provider your auth header before using!");
    }
    synchronized (instance) {
      instance.headers = headers;
    }
  }

  public static void destroy() {
    if (instance != null) {
      instance.headers = null;
      instance = null;
    }
  }
}
