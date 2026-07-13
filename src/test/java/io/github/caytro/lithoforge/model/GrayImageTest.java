package io.github.caytro.lithoforge.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GrayImageTest {

    @Test
    void shouldReturnImageDimensions() {
        int[][] pixels = {
                {0, 1},
                {2, 3}
        };

        GrayImage image = new GrayImage(pixels);

        assertEquals(2, image.width());
        assertEquals(2, image.height());
    }

    @Test
    void shouldReturnGrayValue() {
        int[][] pixels = {
                {0, 1, 2},
                {3, 4, 5}
        };

        GrayImage image = new GrayImage(pixels);

        assertEquals(4, image.grayAt(1, 1));
    }

    @Test
    void shouldDefensivelyCopyPixels() {

        int[][] pixels = {
                {0, 1},
                {2, 3}
        };

        GrayImage image = new GrayImage(pixels);
        pixels[0][0] = 255;
        assertEquals(0, image.grayAt(0, 0));
    }

    @Test
    void shouldRejectNullPixels() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new GrayImage(null)
        );
    }

    @Test
    void shouldRejectGrayValueAbove255() {
        int[][] pixels = {
                {0, 256}
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> new GrayImage(pixels)
        );
    }
    @Test
    void shouldRejectEmptyPixels() {
        int[][] pixels = {};

        assertThrows(
                IllegalArgumentException.class,
                () -> new GrayImage(pixels)
        );
    }

    @Test
    void shouldRejectNullRow() {
        int[][] pixels = {
                {0, 1},
                null
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> new GrayImage(pixels)
        );
    }

    @Test
    void shouldRejectEmptyRow() {
        int[][] pixels = {
                {}
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> new GrayImage(pixels)
        );
    }

    @Test
    void shouldRejectRowsWithDifferentWidths() {
        int[][] pixels = {
                {0, 1},
                {2}
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> new GrayImage(pixels)
        );
    }

    @Test
    void shouldRejectGrayValueBelowZero() {
        int[][] pixels = {
                {-1, 0}
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> new GrayImage(pixels)
        );
    }
}