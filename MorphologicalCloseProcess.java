/**
 * This program will perform a morphological open operation using JAI
 *
 * @author Christopher Sasarak
 */
import javax.media.jai.*;
import java.awt.image.RenderedImage;

/**
 * @class Performs a morphological open operation; this is an erode followed by a dilation.
 */
public class MorphologicalCloseProcess implements ImageProcess{
    /**
     * This method takes an image and then returns it after performing a 
     * morphological close operation, which basically means
     * erode the image, then dilate it using the same structuring element.
     *
     * @param img The image to convert perform a morphological open operation on 
     * @return RenderedImage The image after
     */
     public RenderedImage process(RenderedImage img){
         float disk[] ={0,     0,    1,    1,    1,    0,    0,
                       0,    1,    1,    1,    1,    1,    0,
                       1,    1,    1,    1,    1,    1,    1,
                       1,    1,    1,    1,    1,    1,    1,
                       1,    1,    1,    1,    1,    1,    1,
                       0,    1,    1,    1,    1,    1,    0,
                       0,    0,   1,    1,    1,    0,    0};

        KernelJAI kern = new KernelJAI(7,7,disk); 
        RenderedImage erode = JAI.create("dilate", img, kern, null);
        return JAI.create("erode",img, kern, null);
     }
}
