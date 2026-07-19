package io.github.caytro.lithoforge.geometry;

public record Triangle(
        int first,
        int second,
        int third
) {
    public Triangle {
        if (first < 0 || second < 0 || third < 0) {
            throw new IllegalArgumentException(
                    "Vertex indices must be non-negative"
            );
        }

        if (first == second
                || first == third
                || second == third) {
            throw new IllegalArgumentException(
                    "A triangle must reference three distinct vertices"
            );
        }
    }
}
