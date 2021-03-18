
package com.studio.meowtoon.animesign.service;

import lombok.val;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author h.adachi
 */
public class RectTest {
    @Test
    public void testGetWidth() {
        val _target = new Rect(120.0f, 120.0f);
        float _result = _target.getWidth();
        assertEquals(120.0f, _result, 0.0);
    }

    @Test
    public void testGetHeight() {
        val _target = new Rect(120.0f, 120.0f);
        float _result = _target.getHeight();
        assertEquals(120.0f, _result, 0.0);
    }

    @Test
    public void testGetGridWidth() {
        val _target = new Rect(120.0f, 120.0f);
        float _result = _target.getGridWidth();
        assertEquals(1.0f, _result, 0.0);
    }

    @Test
    public void testGetGridHeight() {
        val _target = new Rect(120.0f, 120.0f);
        float _result = _target.getGridHeight();
        assertEquals(1.0f, _result, 0.0);
    }
}
