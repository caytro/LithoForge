package io.github.caytro.lithoforge.geometry;

public record Vertex(
        double x,
        double y,
        double z
)
{
    public Vertex {
        if (!Double.isFinite(x)
                || !Double.isFinite(y)
                || !Double.isFinite(z)) {
            throw new IllegalArgumentException(
                    "Vertex coordinates must be finite"
            );
        }
    }
}
