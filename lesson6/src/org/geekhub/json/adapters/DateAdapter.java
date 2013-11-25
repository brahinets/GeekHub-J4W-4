/**
 * Class: DateAdapter
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 23.11.13
 * Time: 13:18
 * Mail: ysb.kanivtsi@gmail.com
 */

package org.geekhub.json.adapters;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converts object of type java.util.Date to String by using dd/MM/yyyy format
 */
public class DateAdapter implements JsonDataAdapter<Date> {
    @Override
    public Object toJson(Date date) {
        return "(" + new SimpleDateFormat("dd/MM/yyyy").format(date) + ")";
    }
}