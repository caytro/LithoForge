package io.github.caytro.lithoforge.geometry;

import java.util.List;

public final class Mesh {

    private final List<Vertex> vertices;
    private final List<Triangle> triangles;

    public Mesh(
            List<Vertex> vertices,
            List<Triangle> triangles
    ) {
        // validations
        validateTriangleIndices(vertices,triangles);
        this.vertices = List.copyOf(vertices);
        this.triangles = List.copyOf(triangles);

    }
    public Vertex vertex(int index) {
        if (index < 0 || index >= vertices.size()) {
            throw new IndexOutOfBoundsException("Vertex index value " + index + " invalid");
        }
        return this.vertices.get(index);
    }

    public Triangle triangle(int index) {
        if (index < 0 || index >= triangles.size()) {
            throw new IndexOutOfBoundsException("Triangle index value " + index + " invalid");
        }
        return this.triangles.get(index);
    }

    public List<Vertex> vertices() {
        return vertices;
    }

    public List<Triangle> triangles() {
        return triangles;
    }

    public int vertexCount() {
        return vertices.size();
    }

    public int triangleCount() {
        return triangles.size();
    }

    private static void validateTriangleIndices(
            List<Vertex> vertices,
            List<Triangle> triangles
    ) {
        int vertexCount = vertices.size();

        for (Triangle triangle : triangles) {
            if (triangle.first() >= vertexCount
                    || triangle.second() >= vertexCount
                    || triangle.third() >= vertexCount) {
                throw new IllegalArgumentException(
                        "Triangle references a missing vertex"
                );
            }
        }
    }
}