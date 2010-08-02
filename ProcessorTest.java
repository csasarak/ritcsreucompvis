import java.io.File;
import java.io.IOException;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import javax.media.jai.JAI;

public class ProcessorTest{

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
        processor.add(new GrayConvertProcess());
        processor.add(new MonoscaleProcess());
        processor.add(new MorphologicalCloseProcess()); 
          
        for(int n = 0; n < args.length; n++){
            String inputFilename = args[n]; 
            String outputFilename = inputFilename.substring(0, inputFilename.lastIndexOf('.')) + "PR.jpg";
            RenderedImage inputImg = JAI.create("fileload", inputFilename);
            
            RenderedImage outputImg = processor.process(inputImg);

            try{
                ImageIO.write(outputImg, "jpg", new File(outputFilename));
            }catch(IOException e){
                System.out.println("Error writing " + outputFilename + ": " 
                                    + e.getMessage());
            }
        }
    }
}
