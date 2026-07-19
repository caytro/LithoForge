package io.github.caytro.lithoforge.geometry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TriangleTest {

    @Test
    @DisplayName("Store Vertex indices")
    void shouldStoreVertexIndices() {
        Triangle triangle = new Triangle(2, 4, 7);

        assertEquals(2, triangle.first());
        assertEquals(4, triangle.second());
        assertEquals(7, triangle.third());
    }

    @Test
    void shouldRejectNegativeIndex() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Triangle(0, -1, 2)
        );
    }

    @Test
    void shouldRejectDuplicateIndices() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Triangle(0, 1, 1)
        );
    }
}
