import java.io.File;
import java.io.IOException;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import javax.media.jai.JAI;

public class ImageProcessor{

    /**
     *
     * @param args The images to operate on
     */
    public static void main(String args[]){
        if(args.length < 1){
            System.out.println("Need a file to convert to a binary image");
            System.exit(1);
        }
        
        //Build up the processor
        ProcessList processor = new ProcessList();
        processor.add(new MorphologicalCloseProcess()); 
        processor.add(new GrayConvertProcess());
        processor.add(new MonoscaleProcess());
        SobelDetectionProcess sobel = new SobelDetectionProcess();
        processor.add(sobel);

        //the third parameter is a placeholder until I can try something
        //different
        HoughTransformer trans = new HoughTransformer(100, .70, 100.0);

        for(int n = 0; n < args.length; n++){
            String inputFilename = args[n]; 
            String outputFilename = inputFilename.substring(0, inputFilename.lastIndexOf('.')) + "PR.jpg";
            RenderedImage inputImg = null;

            try{
                inputImg = ImageIO.read(new File(inputFilename));
            }catch(IOException e){
                System.out.println("Error writing " + outputFilename + ": " 
                                    + e.getMessage());
            }  
            
            if(inputImg == null){
                System.out.println("Could not read: " + inputFilename);
                System.exit(1);
            }
            
            RenderedImage outputImg = processor.process(inputImg);
            Category cat = trans.process(outputImg);

            //if undefined, try processing again, without the Sobel operator
            if(cat == Category.UNDEFINED){
                processor.remove(sobel);
                outputImg = processor.process(inputImg);
                cat = trans.process(outputImg);
                processor.add(sobel);
            }
            
            System.out.println("Input file " + inputFilename + ": " + cat);
        }
    }
}
