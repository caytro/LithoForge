package io.github.caytro.lithoforge.io;
import java.nio.file.Path;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import io.github.caytro.lithoforge.model.GrayImage;

public final class PngImageLoader {

    private PngImageLoader() {
    }

    private static int grayLevel(int rgb) {
        int red =  ( rgb >>> 16 ) & 0xFF ;
        int green = ( rgb >>> 8 ) & 0xFF ;
        int blue = rgb & 0xFF ;
        int grayValue = ( red + green + blue ) / 3;
        // Evolution : pondération 0.299 R + 0.587 G + 0.114 B
        return grayValue;
    }

    public static GrayImage load(Path path)
            throws IOException {

        BufferedImage image =
                ImageIO.read(path.toFile());

        int width = image.getWidth();
        int height = image.getHeight();
        int [][] pixels = new int[height][width];
        for (int row = 0 ; row < height ; row ++ ) {
            for ( int column = 0 ; column < width ; column ++ ) {
                pixels[row][column] = grayLevel( image.getRGB( column, row ));
            }
        }

        return new GrayImage(pixels);
    }
}