/**
 * filename: ImageProcess.java
 *
 * @author Christopher Sasarak
 */

import java.awt.image.RenderedImage;

/**
 * @class ImageProcess An interface defining a process method for subclasses to
 * use on an image. This is part of a composite pattern which will allow the user
 * to add different processes in sequence to a list, and then execute them one-by-one,
 * or use the classes for a one-off operation.

 *
 */
public interface ImageProcess{

    /**
     * This method will run an arbitrary process on a RenderedImage and then
     * return the result.
     *
     * @param img The RenderedImage to do some operation on
     * @return RenderedImage The processed img parameter
     */
    public RenderedImage process(RenderedImage img);
}
