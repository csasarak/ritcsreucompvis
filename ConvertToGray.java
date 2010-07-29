import javax.media.jai.*;
import javax.imageio.ImageIO;
import java.awt.image.renderable.ParameterBlock;
import java.awt.color.ColorSpace;
import java.lang.String;
import java.io.File;
import java.io.IOException;

/**
 * This class can convert a colored image to grayscale. 
 *
 * @author Christopher Sasarak
 */
public class ConvertToMono{
    
    /**
     * Main method will read in a filename from its arguments and then
     * convert that file to a monoscale image. I'm doing this to learn more about
     * JAI
     */
    public static void main(String args[]){
        if(args.length < 1){
            System.out.println("Need a file to convert to grayscale");
            System.exit(1);
        }
        String inputFilename = args[0]; 
        String outputFilename = inputFilename.substring(0, inputFilename.lastIndexOf('.')) + "GR.jpg";
        PlanarImage inputImg = JAI.create("fileload", inputFilename);

        /**
         * For writing you cannot use JAI with the openJDK for reasons shown on
         * this web page: http://codingexplorer.wordpress.com/2009/07/12/interface-was-expected-for-jpegimageencoder/
         * So use ImageIO instead.
         */
        PlanarImage outputImg = makeGrayScale(inputImg);
        try{
            ImageIO.write(outputImg, "jpg", new File(outputFilename));
        }catch(IOException e){
            System.out.println("Error writing " + outputFilename + ": " 
                                + e.getMessage());
        }
    }

    /**
     * This method will make an image GrayScale.
     *
     * @param img A PlanerImage to operate on.
     * @return PlanarImage The new grayscale image.
     */
    public static PlanarImage makeGrayScale(PlanarImage img){
        /**
         * This matrix is for converting the image to grayscale.
         * There is one destination band and n source bands ( [0][1..n]  ). 
         * The double values are what we multiply the source pixels bands by 
         * to get their ultimate value, which are then combined into the single band 
         * of the output. The last item in the second part of the array is a constant
         * value that will be added to the destination band. I have set it to 0 so 
         * it has no effect. 
         * The values I used are from this website: http://www.johndcook.com/blog/2009/08/24/algorithms-convert-color-grayscale/
         */

        double[][] matrix = {{.21, .71, 0.07, 0.0}};
        return JAI.create("bandcombine", img, matrix, null);
    }
}
