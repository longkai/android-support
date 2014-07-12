/*
 * The MIT License (MIT)
 * Copyright (c) 2014 longkai
 * The software shall be used for good, not evil.
 */

package android.support.volley;

import android.content.Context;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Request for a json result from server, subclass may need to further process the json,
 * the procedure occur in the worker thread.
 * If you need update ui after processing, please pass not null {@link com.android.volley.Response.Listener}, or null
 *
 * @author longkai
 */
public class VolleyRequest extends Request<JSONObject> {
  public static final String TAG = VolleyRequest.class.getSimpleName();

  protected Context mContext;
  protected VolleyAPI mApi;
  protected Response.Listener<JSONObject> mListener;
  protected VolleyProcessor<JSONObject> mProcessor;

  public VolleyRequest(Context context, VolleyAPI api, VolleyProcessor<JSONObject> processor, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
    super(api.method, api.uri, errorListener);
    mContext = context;
    mApi = api;
    mProcessor = processor;
    mListener = listener;
  }

  @Override protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
    try {
      String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
      Response<JSONObject> success = Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
      if (mProcessor != null) {
        // do in background...
        mProcessor.asyncProcess(mContext, success.result);
      }
      return success;
    } catch (UnsupportedEncodingException e) {
      return Response.error(new ParseError(e));
    } catch (JSONException je) {
      return Response.error(new ParseError(je));
    } catch (Exception ex) {
      return Response.error(new VolleyError(ex));
    }
  }

  @Override protected void deliverResponse(JSONObject response) {
    if (mListener == null) {
      Log.d(TAG, "finish http request without response on ui-thread!");
    } else {
      mListener.onResponse(response);
    }
  }

  @Override public Map<String, String> getHeaders() throws AuthFailureError {
    return mApi.authRequired ? VolleyAuth.getAuthHeaders() : super.getHeaders();
  }

  @Override protected Map<String, String> getParams() throws AuthFailureError {
    return mApi.params == null ? super.getParams() : mApi.params;
  }
}