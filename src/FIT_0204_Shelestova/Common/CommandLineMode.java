package FIT_0204_Shelestova.Common;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

public class CommandLineMode {
    private Controller controller;
    public CommandLineMode(String propertyFileName, Controller controller){
        this.controller = this.controller;

        try {
            controller.loadPropertiesFromFile(new File(propertyFileName));
            int width = controller.getPanelWidth();
            int height = controller.getPanelHeight();

            FileOutputStream outputBin = new FileOutputStream(new File("output.bin"));

            BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            int[] bits = bimage.getRGB(
                    0,
                    0,
                    width,
                    height,
                    null,
                    0,
                    width);
            controller.fillMemory(bits);
            bimage.setRGB(
                    0,
                    0,
                    width,
                    height,
                    bits,
                    0,
                    width);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bimage, "png", baos);
            outputBin.write(baos.toByteArray());


            outputBin.close();


        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


