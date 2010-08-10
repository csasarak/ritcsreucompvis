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
    
    int centerTolerance; 
    double houghTolerance;
    double houghConfidence;    

    /**
     * The first constructor for HoughTransformer which
     * will set the tolerance for the center of the circle.
     *
     * @param centerTolerance The tolerance in pixels for center of the circle.
     * @param houghTolerance Tolerance for the hough transform (0.0 - 1.0)
     * @param houghConfidence The threshold 
     *        confidence for determining the type of galaxy.
     */
    public HoughTransformer(int centerTolerance, double houghTolerance, 
        double houghConfidence){
        this.centerTolerance = centerTolerance;
        this.houghTolerance = houghTolerance;
        this.houghConfidence = houghConfidence;
    }

    /**
     * This method will try to find a circle of a 
     * certain size, and then return true or 
     * false if it does.
     *
     * @param img The image to do the processing on.
     * @return boolean Whether or not a circle was found.
     */
    public Category process(RenderedImage img){
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
        
        System.out.println(circle.getRadius());
        System.out.println("Location: ("+circle.getX() +"," +circle.getY()+")");
        System.out.println("Confidence: " + circle.getConfidence());
        /**
         * Check that the circle we're looking for is near the center.
         * If it is not, then the hough transform probably found
         * a larger object than the one in question and failed.
         */
        double circX = circle.getX(), circY = circle.getY();
        double confidence = circle.getConfidence();
        if((circX < width-centerTolerance || circX > width+centerTolerance)
            || (circY < height-centerTolerance || circY>height+centerTolerance))
            return Category.UNDEFINED;
       
        else if(confidence == 0)
            return Category.UNDEFINED; 

        //Now based on the confidence, place the circle in a category.
        //If the confidence is higher, then the galaxy was more circular. 
        if(confidence < houghConfidence)
           return Category.SPIRAL;
        else
            return Category.ELLIPTICAL;
    }
    

    public static void main(String args[]){
        if(args.length < 1)
            System.exit(1);

        //The last parameter is a placeholder for now
        HoughTransformer trans = new HoughTransformer(100, .70, 100.0);

        try{
            trans.process(ImageIO.read(new File(args[0])));
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
