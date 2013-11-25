/**
 * Class: ColorAdapter
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 23.11.13
 * Time: 13:32
 * Mail: ysb.kanivtsi@gmail.com
 */

package org.geekhub.json.adapters;

import java.awt.Color;

/**
 * Converts object of type java.awt.Color to its String representation. i.e. Color.GRAY = "(128,128,128)"
 */
public class ColorAdapter implements JsonDataAdapter<Color> {
    @Override
    public Object toJson(Color o) {
        return "(" + o.getRed() + "," + o.getGreen() + "," + o.getBlue() + ")";
    }
}
