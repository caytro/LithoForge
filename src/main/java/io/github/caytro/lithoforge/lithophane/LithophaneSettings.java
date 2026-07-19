package io.github.caytro.lithoforge.lithophane;

public record LithophaneSettings(
        double width,
        double height,
        double minThickness,
        double maxThickness
) {

    public LithophaneSettings {
        requireStrictlyPositive("width", width);
        requireStrictlyPositive("height", height);
        requireStrictlyPositive("minThickness", minThickness);
        requireFinite("maxThickness", maxThickness);

        if (maxThickness < minThickness) {
            throw new IllegalArgumentException(
                    "maxThickness must be greater than or equal to "
                            + "minThickness: (minThickness, maxThickness) = ("
                            + minThickness + ", " + maxThickness + ")"
            );
        }
    }

    private static void requireStrictlyPositive(
            String name,
            double value
    ) {
        requireFinite(name, value);

        if (value <= 0) {
            throw new IllegalArgumentException(
                    name + " must be strictly positive: " + value
            );
        }
    }

    private static void requireFinite(
            String name,
            double value
    ) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    name + " must be finite: " + value
            );
        }
    }
}