/**
 * Class: JsonSerializer
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 23.11.13
 * Time: 13:10
 * Mail: ysb.kanivtsi@gmail.com
 */

package org.geekhub.json;

import org.geekhub.json.adapters.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.*;


/**
 * JsonSerializer converts Java objects to JSON representation.
 *
 */
public class JsonSerializer {

    /**
     * simpleTypes contains java classes for which we should not make any deeper serialization and we should return object as is
     * and use toString() method to get it serialized representation
     */
    private static Set<Class> simpleTypes = new HashSet<Class>(Arrays.asList(
            JSONObject.class,
            JSONArray.class,
            String.class,
            Integer.class,
            Short.class,
            Long.class,
            Byte.class,
            Double.class,
            Float.class,
            Character.class,
            Boolean.class,
            int.class,
            short.class,
            long.class,
            byte.class,
            double.class,
            float.class,
            char.class,
            boolean.class
    ));

    /**
     * Main method to convert Java object to JSON. If type of the object is part of the simpleTypes object itself will be returned.
     * If object is null String value "null" will be returned.
     * @param o object to serialize.
     * @return JSON representation of the object.
     */
    public static Object serialize(Object o) {
        if (o == null) {
            return "null";
        }
        if (simpleTypes.contains(o.getClass())) {
            return o;
        } else {
            try {
                return toJsonObject(o);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * Converts Java object to JSON. Uses reflection to access object fields.
     * Uses JsonDataAdapter to serialize complex values. Ignores @Ignore annotated fields.
     * @param o object to serialize to JSON
     * @return JSON object.
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static JSONObject toJsonObject(Object o) throws Exception {
        JSONObject jo = new JSONObject();
        Field[] fields = o.getClass().getDeclaredFields();
        AccessibleObject.setAccessible(fields,true);

        /* go through all fields */
        for (Field f: fields){
            if(f.isAnnotationPresent(Ignore.class)){ /* if field of the object has ignore annotation -> then skip this field */
               continue;
            } else {
                if(f.isAnnotationPresent(UseDataAdapter.class)){
                    jo.put(f.getName(), f.getAnnotation(UseDataAdapter.class).value().newInstance().toJson(f.get(o))); /* if field of the object has dataAdapter annotation */
                } else {
                    if(simpleTypes.contains(f.get(o).getClass())){ /* if field of the object is 'Simple' type (Simple types described earlier) */
                        jo.put(f.getName(),f.get(o));
                    } else {
                        jo.put(f.getName(), serialize(f.get(o))); /* if field of the object is object again */
                    }
                }
            }
        }
        AccessibleObject.setAccessible(fields,false);

        return jo;
    }
}
