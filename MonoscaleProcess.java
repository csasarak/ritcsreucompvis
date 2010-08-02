/**
 * Convert a grayscale image to a binary image.
 *
 * @author Christopher Sasarak
 */
import javax.media.jai.*;
import java.awt.image.RenderedImage;

public class MonoscaleProcess implements ImageProcess {
    /**
     * This method accepts a RenderedImage and converts it to 
     * binary.
     *
     * @param img The source image.
     * @return RenderedImage The image after being processed by the method.
     */
    public RenderedImage process(RenderedImage img){
        Histogram hist = (Histogram)JAI.create("histogram", img).getProperty("histogram");
        double[] threshold = hist.getPTileThreshold(0.5);

        return JAI.create("binarize", img, new Double(threshold[0]));
    }
}
