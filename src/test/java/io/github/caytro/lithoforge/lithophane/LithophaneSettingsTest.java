package io.github.caytro.lithoforge.lithophane;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LithophaneSettingsTest {

    @Test
    void shouldRejectZeroWidth() {
        assertThrows(
                IllegalArgumentException.class,
                ()-> new LithophaneSettings(0, 100.0, 0.4, 4.0)
        );
    }
    @Test
    void shouldRejectNegativeHeight() {
        assertThrows(
                IllegalArgumentException.class,
                ()-> new LithophaneSettings(150.5, -100.0, 0.4, 4.0)
        );
    }

    @Test
    void shouldRejectZeroHeight() {
        assertThrows(
                IllegalArgumentException.class,
                ()-> new LithophaneSettings(150.5, 0.0, 0.4, 4.0)
        );
    }

    @Test
    void shouldRejectNegativeMinThickness() {
        assertThrows(
                IllegalArgumentException.class,
                ()-> new LithophaneSettings(150.5, 100.0, -0.4, 4.0)
        );
    }

    @Test
    void shouldRejectZeroMinThickness() {
        assertThrows(
                IllegalArgumentException.class,
                ()-> new LithophaneSettings(150.5, 100.0, 0.0, 4.0)
        );
    }

    @Test
    void shouldRejectMaxThicknessLowerThanMinThickness() {
        assertThrows(
                IllegalArgumentException.class,
                ()-> new LithophaneSettings(150.5, 100.0, 4.0, 2.2)
        );
    }

    @Test
    void shouldExposeValues() {
        LithophaneSettings lithophaneSettings = new LithophaneSettings(150.5, 87.9, 0.4, 4.0);
        assertEquals( 150.5, lithophaneSettings.width());
        assertEquals(87.9, lithophaneSettings.height());
        assertEquals(0.4, lithophaneSettings.minThickness());
        assertEquals(4.0, lithophaneSettings.maxThickness());


    }



}
