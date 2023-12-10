package edu.project4.models;

import java.util.Arrays;

public record FractalImage(Pixel[][] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        Pixel[][] pixels = new Pixel[height][width];
        for (Pixel[] pixel : pixels) {
            Arrays.fill(pixel, new Pixel(0, 0, 0, 0, 0));
        }
        return new FractalImage(pixels, width, height);
    }

    public boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Pixel getPixel(int x, int y) {
        return contains(x, y) ? data[y][x] : null;
    }

    public void setPixel(Pixel newPixel, int x, int y) {
        data[y][x] = newPixel;
    }

}
