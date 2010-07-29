/**
 * Convert a grayscale image to a binary image.
 *
 * @author Christopher Sasarak
 */
import javax.media.jai.*;
import javax.imageio.ImageIO;
import java.awt.image.renderable.ParameterBlock;
import java.awt.color.ColorSpace;
import java.lang.String;
import java.io.File;
import java.io.IOException;
public class Binarize {

    /**
     * This is the main method that will convert an image to a black and
     * white binary image.
     *
     * @param args The command line arguments
     */
    public static void main(String args[]){
        if(args.length < 1){
            System.out.println("Need a file to convert to grayscale");
            System.exit(1);
        }
        String inputFilename = args[0]; 
        String outputFilename = inputFilename.substring(0, inputFilename.lastIndexOf('.')) + "BW.jpg";
        PlanarImage inputImg = JAI.create("fileload", inputFilename);

        PlanarImage outputImg = makeBinary(inputImg);
        try{
            ImageIO.write(outputImg, "jpg", new File(outputFilename));
        }catch(IOException e){
            System.out.println("Error writing " + outputFilename + ": " 
                                + e.getMessage());
        }
    }


    /**
     * This method accepts a PlanarImage and converts it to 
     * binary.
     *
     * @param img The source image.
     * @return PlanarImage The image after being processed by the method.
     */
    public static PlanarImage makeBinary(PlanarImage img){
        Histogram hist = (Histogram)JAI.create("histogram", img).getProperty("histogram");
        double[] threshold = hist.getPTileThreshold(0.5);

        return JAI.create("binarize", img, new Double(threshold[0]));
    }
}
