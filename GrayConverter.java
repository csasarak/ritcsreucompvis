import javax.media.jai.*;
import java.awt.color.ColorSpace;
import java.awt.image.RenderedImage;

/**
 * This class can convert a colored image to grayscale. 
 *
 * @author Christopher Sasarak
 */
public class GrayConverter implements ImageProcess{
    /**
     * This method will make an image GrayScale.
     *
     * @param img A RenderedImage to operate on.
     * @return RenderedImage The new grayscale image.
     */
    public RenderedImage process(RenderedImage img){
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
