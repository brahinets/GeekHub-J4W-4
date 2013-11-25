/**
 * Interface : JsonDataAdapter
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 23.11.13
 * Time: 13:14
 * Mail: ysb.kanivtsi@gmail.com
 */


package org.geekhub.json.adapters;

import org.json.JSONException;

/**
 * JsonDataAdapter contains instructions how to serialize Java object to Json representation.
 * @param <T> determines type adapter works with.
 */
public interface JsonDataAdapter<T> {
    Object toJson(T o) throws JSONException;
}