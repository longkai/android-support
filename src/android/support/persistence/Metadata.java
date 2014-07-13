/*
 * The MIT License (MIT)
 * Copyright (c) 2014 longkai
 * The software shall be used for good, not evil.
 */

package android.support.persistence;

/**
 * the metadata interface which need persist into the local database.
 *
 * @author longkai
 */
public interface Metadata<From, To> {
  /**
   * generate the sql schema of the metadata
   *
   * @return sql schema
   */
  String ddl();

  /**
   * convert the data(such as {@link org.json.JSONObject})
   * from one type to another(such as {@link android.content.ContentValues}
   *
   * @param data original data
   * @return new data
   */
  To convert(From data);
}
