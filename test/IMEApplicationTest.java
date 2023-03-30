import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.NoSuchElementException;

import controller.CommandController;
import controller.ImageController;
import controller.ImageControllerImp;
import model.RGBImage;
import model.Image;
import view.ImageView;
import view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test the application as a whole.
 */
public class IMEApplicationTest {

  @Test
  public void testLoadScript() {

    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("run res/scripts/testScript2.txt\nq");

    Image model = new RGBImage(0, 0, 0);
    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, iv);
    assertEquals("loaded fractal successfully\napplication ended\n",
            iv.outputString().toString());

  }

  @Test
  public void testLoadScriptWrongFile() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("run res/scripts/flower.ppm\nrun images/flower.ppm\nq");

    Image model = new RGBImage(0, 0, 0);
    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, iv);
    assertEquals("invalid script being used. "
            + "only txt files are allowed. Try again\n"
            + "\n"
            + "invalid script being used. only txt files are allowed. Try again\n"
            + "\napplication ended\n", iv.outputString().toString());
  }


  @Test
  public void testLoadTerminal() {

    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load res/images/flower.ppm flower \nq");
    Image model = new RGBImage(0, 0, 0);

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, iv);

    assertEquals("loaded flower successfully\napplication ended\n",
            iv.outputString().toString());

  }

  @Test(expected = NoSuchElementException.class)
  public void testLoadTerminalWrongFile() {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("load res/images/test-image.ppm koala");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, iv);

  }

  @Test
  public void testSaveBeforeLoad() {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("save res/images/flower.ppm fractal"
            + "\nload res/images/flower.ppm fractal\nq\n");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, iv);
    assertEquals("image not found\n"
            + "loaded fractal successfully\n", iv.outputString().toString());
  }

  @Test
  public void testSaveTerminal() {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("load res/images/flower.ppm fractal"
            + "\nsave res/images/flower-save.ppm fractal\nq\n");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, iv);

    assertEquals("loaded fractal successfully" + "\n"
            + "saved fractal successfully\napplication ended\n", iv.outputString().toString());
  }

  @Test
  public void testSaveScript() {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("run res/scripts/testScript1.txt\n");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, iv);

    assertEquals("loaded fractal successfully" + "\n"
                    + "increased the brightness of fractal by 10 to fractal-brighter successfully"
                    + "\n"
                    + "flipped fractal-brighter to fractal-brighter-vertical vertically "
                    + "successfully"
                    + "\nsaved fractal-brighter-vertical successfully\napplication ended\n",
            iv.outputString().toString());
  }

  @Test
  public void testDoubleLoad() {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("load res/images/flower.ppm fractal"
            + "\n load res/images/flower-brightened.ppm flower-brightened \nq");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, iv);

    assertEquals("loaded fractal successfully" + "\n"
                    + "loaded flower-brightened successfully" + "\napplication ended\n",
            iv.outputString().toString());
  }

  @Test
  public void testCombineBySepiaPPM() {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "sepia-tone fractal fractal-sepia\n"
            + "save res/images/fractal-sepia.ppm fractal-sepia\n"
            + "q\n");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, iv);

    assertEquals("loaded fractal successfully" + "\n"
            + "converting fractal to a sepia-toned Image - fractal-sepia is successful\n"
            + "saved fractal-sepia successfully"
            + "\napplication ended\n", iv.outputString().toString());
  }

  @Test
  public void testCombineBySepiaPNG() {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "sepia-tone fractal fractal-sepia\n"
            + "save res/images/fractal-sepia.ppm fractal-sepia\n"
            + "q\n");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, iv);

    assertEquals("loaded fractal successfully" + "\n"
            + "converting fractal to a sepia-toned Image - fractal-sepia is successful\n"
            + "saved fractal-sepia successfully"
            + "\napplication ended\n", iv.outputString().toString());
  }

  @Test
  public void testDither() {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "dither fractal fractal-dithered\n"
            + "save res/images/fractal-dithered.ppm fractal-dithered\n"
            + "q\n");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, iv);

    assertEquals("loaded fractal successfully" + "\n"
            + "dither conversion of fractal to fractal-dithered is successful\n"
            + "saved fractal-dithered successfully"
            + "\napplication ended\n", iv.outputString().toString());
  }


  //  @Test
  //  public void testCommandLineArgument() {
  //
  //    Image model = new RGBImage(0, 0, 0);
  //
  //    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
  //            + "dither fractal fractal-dithered\n"
  //            + "save res/images/fractal-dithered.ppm fractal-dithered\n"
  //            + "q");
  //
  //    String[] arguments = new String[1];
  //    arguments[0] = "res/scripts/testScript1.txt";
  //    // IMEApplication temp = new IMEApplication(arguments);
  //
  //    StringBuffer out = new StringBuffer();
  //
  //    CommandController controller = new CommandController(in, out);
  //    ImageController ic = new ImageControllerImp(model);
  //    ImageView iv = new TextView(System.out);
  //    controller.startProgram(ic, iv);
  //
  //  }

}