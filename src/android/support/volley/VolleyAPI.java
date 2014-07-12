/*
 * The MIT License (MIT)
 * Copyright (c) 2014 longkai
 * The software shall be used for good, not evil.
 */

package android.support.volley;

import java.util.Map;

/**
 * The api abstraction.
 *
 * @author longkai
 */
public class VolleyAPI {
  /** {@link com.android.volley.Request.Method} */
  public final int method;

  /** HTTP URI */
  public final String uri;

  /** required access token? */
  public final boolean authRequired;

  /** used with post and put in the request body */
  public final Map<String, String> params;

  public VolleyAPI(int method, CharSequence uri, boolean authRequired, Map<String, String> params) {
    this.method = method;
    this.uri = uri.toString();
    this.authRequired = authRequired;
    this.params = params;
  }
}
