
/**
 * filename: SobelDetectionProcess.java
 *
 * @class This class will perform a Sobel edge detection operation
 * on an image and then return it after doing so.
 *
 * @author Christopher Sasarak
 */
import javax.media.jai.KernelJAI;
import javax.media.jai.JAI;
import java.awt.image.RenderedImage;
public class SobelDetectionProcess implements ImageProcess{
   
   /**
    * Return the parameter image after performing
    * sobel edge detection.
    *
    * @param img The image to perform detection on.
    * @return RenderedImage The image with detection performed.
    */
   public RenderedImage process(RenderedImage img){
        float[] hx = {-1,0,1,-2,0,2,-1,0,1};
        float[] hy = {-1,-2,-1,0,0,0,1,2,1}; 
        KernelJAI kernX = new KernelJAI(3,3, hx);
        KernelJAI kernY = new KernelJAI(3,3, hy); 

        img = JAI.create("convolve", img, kernY);
        return JAI.create("convolve", img, kernX);
   }
}
