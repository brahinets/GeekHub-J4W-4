/**
 * Class: CollectionAdapter
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 23.11.13
 * Time: 13:52
 * Mail: ysb.kanivtsi@gmail.com
 */

package org.geekhub.json.adapters;

import org.geekhub.json.JsonSerializer;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Collection;

/**
 * Converts all objects that extends java.util.Collections to JSONArray.
 */
public class CollectionAdapter implements JsonDataAdapter<Collection> {
    @Override
    public Object toJson(Collection c) throws JSONException{
        JSONArray ja = new JSONArray();
        for (Object key : c) {
            ja.put(JsonSerializer.serialize(key));
        }
        // why we can't 'return new JSONArray(c);' ???, result will be same
        return ja;
    }
}
