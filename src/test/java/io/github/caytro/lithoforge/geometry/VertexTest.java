package io.github.caytro.lithoforge.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VertexTest {

    @Test
    void shouldStoreCoordinates() {
        Vertex vertex = new Vertex(1.5, 2.0, -3.5);

        assertEquals(1.5, vertex.x());
        assertEquals(2.0, vertex.y());
        assertEquals(-3.5, vertex.z());
    }

    @Test
    void shouldRejectNaNCoordinate() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Vertex(Double.NaN, 0.0, 0.0)
        );
    }

    @Test
    void shouldRejectInfiniteCoordinate() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Vertex(
                        0.0,
                        Double.POSITIVE_INFINITY,
                        0.0
                )
        );
    }
}