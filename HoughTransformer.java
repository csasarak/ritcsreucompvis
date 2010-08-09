/**
 * filename: HoughTransformer.java
 *
 * @class HoughTransformer This class will use the BIJ library to find
 * a circle in the given image and then do its best
 * to tell if there is an elliptical galaxy inside it.
 *
 * @author Christopher Sasarak
 */
import java.awt.image.RenderedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

import registration.HoughTransform;
import registration.LocatedCircle;

public class HoughTransformer{
    
    /**
     * This method will try to find a circle of a 
     * certain size, and then return true or 
     * false if it does.
     *
     * @param img The image to do the processing on.
     * @return boolean Whether or not a circle was found.
     */
    public void process(RenderedImage img){
        float[] imgData = new float[img.getWidth() * img.getHeight()];
        img.getData().getPixels(0,0, img.getWidth(), 
            img.getHeight(), imgData);

        /*Find out more about what threshold does
        I'm referring to this site: http://homepages.inf.ed.ac.uk/rbf/HIPR2/hough.htm
        for this. */
        HoughTransform hough = new HoughTransform(img.getWidth(), 
            img.getHeight(), .70);

        LocatedCircle circle = hough.getLargestCircle(imgData, 
            img.getWidth(), img.getHeight());

        System.out.println(circle.getRadius());
    }

    public static void main(String args[]){
        if(args.length < 1)
            System.exit(1);

        HoughTransformer trans = new HoughTransformer();

        try{
            trans.process(ImageIO.read(new File(args[0])));
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
