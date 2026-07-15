package io.github.caytro.lithoforge.io;

import java.nio.file.Path;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import io.github.caytro.lithoforge.model.GrayImage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PngImageLoaderTest
{
    @Test
    void shouldLoadTinyImage() throws IOException {

        Path path =
                Path.of("src/test/resources/tiny.png");

        GrayImage image =
                PngImageLoader.load(path);

        assertEquals(2, image.width());
        assertEquals(2, image.height());

        assertEquals(0, image.grayAt(0,0));
        assertEquals(85, image.grayAt(0,1));
        assertEquals(170, image.grayAt(1,0));
        assertEquals(255, image.grayAt(1,1));
    }

}