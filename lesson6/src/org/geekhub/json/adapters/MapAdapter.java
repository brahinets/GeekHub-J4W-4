/**
 * Class: MapAdapter
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 23.11.13
 * Time: 13:37
 * Mail: ysb.kanivtsi@gmail.com
 */



package org.geekhub.json.adapters;

import org.geekhub.json.JsonSerializer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Converts all objects that extends java.util.Map to JSONObject.
 */

public class MapAdapter implements JsonDataAdapter<Map> {
    @Override
    public Object toJson(Map map) throws JSONException {
        JSONObject m = new JSONObject();
        for (Object key : map.keySet()) {
            m.put(key.toString(), JsonSerializer.serialize(map.get(key)));
        }
        /* why we can't 'return new JSONObject(map);'  ???, result will be same */
        return m;
    }
}
