/**
 * This program will perform a morphological open operation using JAI
 *
 * @author Christopher Sasarak
 */

/**
 * @class Performs a morphological open operation; this is an erode followed by a dilation.
 */
public class MorphologicalOpen{
    /**
     *
     * @param args Command line arguments
     */
    public static void main(String args[]){
        if(args.length < 1){
            System.out.println("Need a file to convert to a binary image");
            System.exit(1);
        }
        String inputFilename = args[0]; 
        String outputFilename = inputFilename.substring(0, inputFilename.lastIndexOf('.')) + "BW.jpg";
        PlanarImage inputImg = JAI.create("fileload", inputFilename);

        PlanarImage outputImg = //To do 

        try{
            ImageIO.write(outputImg, "jpg", new File(outputFilename));
        }catch(IOException e){
            System.out.println("Error writing " + outputFilename + ": " 
                                + e.getMessage());
        }
    }

    /**
     * This method takes an image and then returns it after performing a 
     * morphological open operation, which basically means
     * erode the image, then dilate it using the same structuring element.
     *
     * @param img The image to convert perform a morphological open operation on 
     * @return PlanarImage The image after
     */
}
