package io.github.caytro.lithoforge.geometry;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MeshTest {

    @Test
    void shouldExposeVerticesAndTriangles() {
        List<Vertex> vertices = List.of(
                new Vertex(0, 0, 0),
                new Vertex(1, 0, 0),
                new Vertex(0, 1, 0)
        );

        List<Triangle> triangles = List.of(
                new Triangle(0, 1, 2)
        );

        Mesh mesh = new Mesh(vertices, triangles);

        assertEquals(3, mesh.vertexCount());
        assertEquals(1, mesh.triangleCount());
    }

    @Test
    void shouldRejectTriangleReferencingMissingVertex() {
        List<Vertex> vertices = List.of(
                new Vertex(0, 0, 0),
                new Vertex(1, 0, 0),
                new Vertex(0, 1, 0)
        );

        List<Triangle> triangles = List.of(
                new Triangle(0, 1, 3)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Mesh(vertices, triangles)
        );
    }

    @Test
    void shouldDefensivelyCopyCollections() {
        List<Vertex> vertices = new ArrayList<>(List.of(
                new Vertex(0, 0, 0),
                new Vertex(1, 0, 0),
                new Vertex(0, 1, 0)
        ));

        List<Triangle> triangles = new ArrayList<>(List.of(
                new Triangle(0, 1, 2)
        ));

        Mesh mesh = new Mesh(vertices, triangles);

        vertices.clear();
        triangles.clear();

        assertEquals(3, mesh.vertexCount());
        assertEquals(1, mesh.triangleCount());
    }

    @Test
    void shouldExposeNthVertex() {
        List<Vertex> vertices = List.of(
                new Vertex(1, 2, 3),
                new Vertex(4, 5, 6),
                new Vertex(7, 8, 9),
                new Vertex(10, 20, 30),
                new Vertex(40, 50, 60),
                new Vertex(70, 80, 90)
        );

        List<Triangle> triangles = List.of(
                new Triangle(0, 1, 2),
                new Triangle(3, 4, 5)
        );
        Mesh mesh = new Mesh(vertices, triangles);

        assertEquals(new Vertex(40.0,50.0,60.0), mesh.vertex(4));
    }

    @Test
    void shouldExposeNthTriangle() {
        List<Vertex> vertices = List.of(
                new Vertex(1, 2, 3),
                new Vertex(4, 5, 6),
                new Vertex(7, 8, 9),
                new Vertex(10, 20, 30),
                new Vertex(40, 50, 60),
                new Vertex(70, 80, 90)
        );

        List<Triangle> triangles = List.of(
                new Triangle(0, 1, 2),
                new Triangle(3, 4, 5)
        );
        Mesh mesh = new Mesh(vertices, triangles);

        assertEquals(new Triangle(3,4,5), mesh.triangle(1));
    }

    @Test
    void shouldRejectInvalidVertexIndex() {

        List<Vertex> vertices = List.of(
                new Vertex(1, 2, 3),
                new Vertex(4, 5, 6),
                new Vertex(7, 8, 9),
                new Vertex(10, 20, 30),
                new Vertex(40, 50, 60),
                new Vertex(70, 80, 90)
        );

        List<Triangle> triangles = List.of(
                new Triangle(0, 1, 2),
                new Triangle(3, 4, 5)
        );
        Mesh mesh = new Mesh(vertices, triangles);

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> mesh.vertex(8)
        );
    }

    @Test
    void shouldRejectInvalidTriangleIndex() {

        List<Vertex> vertices = List.of(
                new Vertex(1, 2, 3),
                new Vertex(4, 5, 6),
                new Vertex(7, 8, 9),
                new Vertex(10, 20, 30),
                new Vertex(40, 50, 60),
                new Vertex(70, 80, 90)
        );

        List<Triangle> triangles = List.of(
                new Triangle(0, 1, 2),
                new Triangle(3, 4, 5),
                new Triangle(0, 2, 3)
        );
        Mesh mesh = new Mesh(vertices, triangles);

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> mesh.triangle(8)
        );
    }

}