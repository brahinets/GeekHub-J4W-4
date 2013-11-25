/**
 * Class: Paw
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 23.11.13
 * Time: 12:53
 * Mail: ysb.kanivtsi@gmail.com
 */

package org.geekhub.test;

import org.geekhub.json.adapters.ColorAdapter;
import org.geekhub.json.adapters.UseDataAdapter;

import java.awt.*;

public class Paw {

    private Integer length;
    @UseDataAdapter(ColorAdapter.class)
    private Color color;

    public Paw(Integer length, Color color) {
        this.length = length;
        this.color = color;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}