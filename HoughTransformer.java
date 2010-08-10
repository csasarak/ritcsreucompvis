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
    
    int centerTolerance, houghTolerance;
    
    /**
     * The first constructor for HoughTransformer which
     * will set the tolerance for the center of the circle.
     *
     * @param centerTolerance The tolerance in pixels for center of the circle.
     * @param houghTolerance Tolerance for the hough transform (0.0 - 1.0)
     */
    public HoughTransformer(int centerTolerance, double houghTolerance){
        this.centerTolerance = centerTolerance;
        this.houghTolerance = houghTolerance;
    }

    /**
     * This method will try to find a circle of a 
     * certain size, and then return true or 
     * false if it does.
     *
     * @param img The image to do the processing on.
     * @return boolean Whether or not a circle was found.
     */
    public void process(RenderedImage img){
        int width = img.getWidth(), height = img.getHeight();
        float[] imgData = img.getData().getPixels(0,0, width, 
            height, (float[])null);

        /*Find out more about what threshold does
        I'm referring to this site: http://homepages.inf.ed.ac.uk/rbf/HIPR2/hough.htm
        for this. */
        HoughTransform hough = new HoughTransform(img.getWidth(), 
            img.getHeight(), .70);

        LocatedCircle circle = hough.getLargestCircle(imgData, 
            img.getWidth(), img.getHeight());
        
        /**
         * Check that the circle we're looking for is near the center.
         * If it is not, then the hough transform probably found
         * a larger object than the one in question and failed.
         */
        int circX = circle.getX(), circY = circle.getY();
        if((circX < width-centerTolerance || circX > width-centerTolerance)
            || (circY < height-centerTolerance || circY>height+centerTolerance))
            return Category.UNDEFINED;

        System.out.println(circle.getRadius());
        System.out.println("Location: ("+circle.getX() +"," +circle.getY());
        System.out.println("Confidence: " + circle.getConfidence());
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
