/*
 * The MIT License (MIT)
 * Copyright (c) 2014 longkai
 * The software shall be used for good, not evil.
 */

package android.support.volley;

import android.content.Context;

/**
 * Process the data from the http response. For example, persist it into local database.
 * This method shall be called in ui-thread!
 *
 * @author longkai
 */
public interface VolleyProcessor<X> {
  /**
   * Process the data from the http response. For example, persist it into local database.
   *
   * @param context {@link android.content.Context}
   * @param data    the data from the http response
   * @throws java.lang.Exception if the processor cannot process or any exception thrown.
   */
  void asyncProcess(Context context, X data) throws Exception;
}

