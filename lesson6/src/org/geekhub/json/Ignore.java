/**
 * Annotation : Ignore
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 23.11.13
 * Time: 13:43
 * Mail: ysb.kanivtsi@gmail.com
 */



package org.geekhub.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark its annotated field to be ignored during serialization or other operation that needs access to the field.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Ignore {
}
