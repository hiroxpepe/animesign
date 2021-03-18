
package com.studio.meowtoon.animesign.service;

import lombok.val;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author h.adachi
 */
public class PointTest {
    @Test
    public void testGetX() {
        val _target = new Point(120.0f, 120.0f);
        float _result = _target.getX();
        assertEquals(120.0f, _result, 0.0);
    }

    @Test
    public void testGetY() {
        val _target = new Point(120.0f, 120.0f);
        float _result = _target.getY();
        assertEquals(120.0f, _result, 0.0);
    }

    @Test
    public void testGetGridX() {
        val _target = new Point(120.0f, 120.0f);
        float _result = _target.getGridX();
        assertEquals(1.0f, _result, 0.0);
    }

    @Test
    public void testGetGridY() {
        val _target = new Point(120.0f, 120.0f);
        float _result = _target.getGridY();
        assertEquals(1.0f, _result, 0.0);
    }
}
