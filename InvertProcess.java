/**
 * Invert the pixels in a binary image.
 *
 * @author Christopher Sasarak
 */
import java.awt.image.RenderedImage;
import javax.media.jai.JAI;
public class InvertProcess implements ImageProcess{

    /**
     * This method takes a RenderedImage and then inverts it.
     * It could be useful for image recognition to have the background
     * white and the foreground black.
     *
     * @param img The image to invert
     * @return RenderedImage The img parameter with its bits inverted.
     */
    public RenderedImage process(RenderedImage img){
        return JAI.create("invert", img);
    }

}
