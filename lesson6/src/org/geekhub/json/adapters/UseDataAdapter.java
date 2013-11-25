/**
 * Annotation : UseDataAdapter
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 23.11.13
 * Time: 13:18
 * Mail: ysb.kanivtsi@gmail.com
 */



package org.geekhub.json.adapters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Determines class of JsonDataAdapter that should be used to serialize annotated field value.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseDataAdapter {
    Class<? extends JsonDataAdapter> value();
}
