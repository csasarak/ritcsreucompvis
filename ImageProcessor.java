import java.io.File;
import java.io.IOException;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import javax.media.jai.JAI;

public class ImageProcessor{
    
    ProcessList process;

    /**
     *
     * @param args The images to operate on
     */
    public static void main(String args[]){
        if(args.length < 1){
            System.out.println("Need a file to convert to a binary image");
            System.exit(1);
        }
        
        ImageProcessor process = new ImageProcessor();  
         

        for(int n = 0; n < args.length; n++){
            try{
                process.categorizeImage(args[n]);
            }catch(IOException e){
                System.err.println("Error reading " + args[n] + ": " 
                                    + e.getMessage());
            }  
        }
    }
    
    public ImageProcessor(){
        //build up the image process
        process = new ProcessList();
        process.add(new MorphologicalCloseProcess()); 
        process.add(new GrayConvertProcess());
        process.add(new MonoscaleProcess());
        SobelDetectionProcess sobel = new SobelDetectionProcess();
        process.add(sobel);
    }

    public Category categorizeImage(String filename)
        throws IOException{

        RenderedImage inputImg = ImageIO.read(new File(filename));
        
        HoughTransformer trans = new HoughTransformer(250, .70, 100.0);
        RenderedImage outputImg = process.process(inputImg);
        Category cat = trans.process(outputImg);

        //if undefined, try processing again, without the Sobel operator
        if(cat == Category.UNDEFINED){
            ImageProcess sobel = process.get(process.size() - 1);
            process.remove(process.size() - 1);
            cat = trans.process(process.process(inputImg));
            process.add(sobel);
        }
        
        System.out.println("Input file " + filename + ": " + cat);
        return cat;
    }
}
