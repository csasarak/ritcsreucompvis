/**
 * This program will perform a morphological open operation using JAI
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

/**
 * @class Performs a morphological open operation; this is an erode followed by a dilation.
 */
public class MorphologicalClose{
    /**
     *
     * @param args Command line arguments
     */
    public static void main(String args[]){
        if(args.length < 1){
            System.out.println("Need a file to convert to a binary image");
            System.exit(1);
        }
        String inputFilename = args[0]; 
        String outputFilename = inputFilename.substring(0, inputFilename.lastIndexOf('.')) + "OP.jpg";
        PlanarImage inputImg = JAI.create("fileload", inputFilename);
        
        PlanarImage outputImg = morphClose(inputImg);

        try{
            ImageIO.write(outputImg, "jpg", new File(outputFilename));
        }catch(IOException e){
            System.out.println("Error writing " + outputFilename + ": " 
                                + e.getMessage());
        }
    }

    /**
     * This method takes an image and then returns it after performing a 
     * morphological close operation, which basically means
     * erode the image, then dilate it using the same structuring element.
     *
     * @param img The image to convert perform a morphological open operation on 
     * @return PlanarImage The image after
     */
     public static PlanarImage morphClose(PlanarImage img){
         float disk[] ={0,     0,    1,    1,    1,    0,    0,
                       0,    1,    1,    1,    1,    1,    0,
                       1,    1,    1,    1,    1,    1,    1,
                       1,    1,    1,    1,    1,    1,    1,
                       1,    1,    1,    1,    1,    1,    1,
                       0,    1,    1,    1,    1,    1,    0,
                       0,    0,   1,    1,    1,    0,    0};

        KernelJAI kern = new KernelJAI(7,7,disk); 
        PlanarImage erode = JAI.create("dilate", img, kern, null);
        return JAI.create("erode",img, kern, null);
     }
}
