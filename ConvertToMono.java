import javax.media.jai.*;
//import com.sun.media.jai.*;
import java.awt.image.renderable.ParameterBlock;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.lang.String;

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
        String inputFile = args[0]; 
        String outputFile = inputFile.substring(0, inputFile.lastIndexOf('.')) + "GR.tif";
        PlanarImage inputImg = JAI.create("fileload", inputFile);
        PlanarImage outputImg = makeGrayScale(inputImg);
        JAI.create("filestore", outputImg, outputFile, "TIFF");
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
