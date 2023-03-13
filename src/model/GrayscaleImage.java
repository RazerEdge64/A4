package model;

import java.util.Scanner;

/**
 * For a greyscale image there is only one value per pixel. On a scale of 0-1, 0 indicates black
 * and 1 indicates white. Values in between indicate shades of grey. This value is traditionally
 * represented using an integer. The number of bits used to store this value dictates how many
 * "levels of grey" are supported. For example, an 8-bit representation creates 256 distinct levels
 * (including black and white).
 */
public class GrayscaleImage extends AbstractImage {

  //TODO we can build a builder class within
  public GrayscaleImage(int width, int height, int maxValue) {
    super(width, height, maxValue, "P2");
  }

  public GrayscaleImage(int width, int height, int maxValue, Pixel[][] pixels) {
    super(width, height, maxValue, "P2");
    this.pixels = pixels;
  }

  public void save(String filePath) {
    // Save grayscale image to file
  }

  public void load(String content) {
    Scanner sc = new Scanner(content);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int intensity = sc.nextInt();
        setPixel(i, j, new Pixel(intensity, intensity, intensity));
      }
    }
  }

  public void brighten(int increment) {
    // Brighten grayscale image by given increment
    int maxValue = maxColorValue;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel pixel = getPixel(i, j);
        // In Greyscale, all channels have equal values.
        int intensity = pixel.getRed() + increment;
        if (intensity > maxValue) {
          intensity = maxValue;
        }
        setPixel(i, j, new Pixel(intensity, intensity, intensity));
      }
    }

  }

  public Image[] splitChannels() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Splitting of greyscale images are not allowed");
  }

  public void combineChannels(Image[] channels) {
  }
}

