import javax.media.jai.*;
//import com.sun.media.jai.*;
import java.awt.image.renderable.ParameterBlock;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.lang.String;

/**
 * This class can convert a colored image to grayscale. 
 *
 * @author Christopher Sasarak
 */
public class ConvertToMono{
    
    /**
     * Main method will read in a filename from its arguments and then
     * convert that file to a monoscale image. I'm doing this to learn more about
     * JAI
     */
    public static void main(String args[]){
        if(args.length < 1){
            System.out.println("Need a file to convert to grayscale");
            System.exit(1);
        }
        String inputFile = args[0]; 
        String outputFile = inputFile.substring(0, inputFile.lastIndexOf('.')) + "GR.jpg";
        PlanarImage inputImg = JAI.create("fileload", inputFile);
        PlanarImage outputImg = makeGrayScale(inputImg);
        JAI.create("filestore", outputImg, outputFile, "JPEG", null);
    }

    /**
     * This method will make an image GrayScale.
     *
     * @param img A PlanerImage to operate on.
     * @return PlanarImage The new grayscale image.
     */
    public static PlanarImage makeGrayScale(PlanarImage img){
        ParameterBlock pb = new ParameterBlock();
        pb.addSource(img);        
        ColorModel cm = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_GRAY), 
                                                new int[] {8},
                                                false,
                                                false,
                                                Transparency.OPAQUE,
                                                DataBuffer.TYPE_BYTE);
        pb.add(cm);
        return JAI.create("ColorConvert", pb);
    }
}
