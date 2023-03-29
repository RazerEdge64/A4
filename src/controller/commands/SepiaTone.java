package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.Command;
import controller.ImageController;
import view.ImageView;

public class SepiaTone implements Command {
  private final Scanner scan;
  private final ImageController imageControllerImp;
  private final ImageView view;

  public SepiaTone(Scanner scan, ImageController imageControllerImp, ImageView view) {
    this.scan = scan;
    this.imageControllerImp = imageControllerImp;
    this.view = view;
  }

  @Override
  public void execute() throws IOException {
    String imageName = scan.next();
    String updatedImageName = scan.next();
    imageControllerImp.combineBySepia(imageName, updatedImageName);
    view.display(String.format("converting %s to a sepia-toned "
                    + "Image - %s is successful\n",
            imageName, updatedImageName));
  }
}