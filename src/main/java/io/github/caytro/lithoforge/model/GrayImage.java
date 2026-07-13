package io.github.caytro.lithoforge.model;

/**
 * Représente une image rectangulaire en niveaux de gris.
 *
 * Chaque pixel possède une valeur comprise entre 0 et 255.
 */
public class GrayImage {

    private final int width;
    private final int height;
    private final int[][] pixels;

    public GrayImage(int[][] pixels) {
        validate(pixels);
        this.height = pixels.length;
        this.width = pixels[0].length;
        this.pixels = defensiveCopy(pixels);
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public int grayAt(int row, int column) {
        return pixels[row][column];
    }

    private static void validate(int[][] pixels) {

        // pixels != null
        if (pixels == null) {
            throw new IllegalArgumentException("pixels must not be null");
        }

        // pixels.length > 0
        if (pixels.length <= 0) {
            throw new IllegalArgumentException("pixels.length must be strictly positive");
        }
        int h = pixels.length;

        // pixels[0] != null
        if (pixels[0] == null) {
            throw new IllegalArgumentException("pixels[0] must not be null");
        }

        // pixels[0].length > 0
        if (pixels[0].length == 0) {
            throw new IllegalArgumentException("pixels[0].length must not be equal to 0");
        }

        // toutes les lignes non nulles
        for (int row = 1; row < h; row++) {
            if (pixels[row] == null) {
                throw new IllegalArgumentException("pixels[" + row + "] must not be null");
            }
        }
        int w = pixels[0].length;

        // toutes les lignes même longueur
        for (int row = 1; row < h; row++) {
            if (pixels[row].length != w) {
                throw new IllegalArgumentException("pixels[" + row + "].length must be equal to pixels[0].length (" + pixels[0].length +")");
            }
        }


        // toutes les valeurs entre 0 et 255
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if ((pixels[row][col] > 255) || (pixels[row][col] < 0)) {
                    throw new IllegalArgumentException("pixels[" + row + "][" + col + "] must be in interval [0 255]");
                }
            }
        }
    }
    private static int[][] defensiveCopy(int[][] pixels) {
        int[][] copy = new int[pixels.length][];

        for (int row = 0; row < pixels.length; row++) {
            copy[row] = pixels[row].clone();
        }

        return copy;
    }
}


