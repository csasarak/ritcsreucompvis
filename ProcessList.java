/**
 * filename: ProcessList.java
 *
 * @author Christopher Sasarak
 */

import java.util.ArrayList;
import java.awt.image.RenderedImage;
import java.util.Iterator;

/**
 * @class This class is an ArrayList of ImageProcesses. Processes can be added
 * and removed from the list and when this class's process method is called it
 * will run all of the Process methods on the objects it contains.
 */
public class ProcessList extends ArrayList<ImageProcess> implements ImageProcess {
     
    /**
     * This is the process method defined by ImageProcess. It will
     * go through all ImageProcess objects in the list and then run
     * their process methods in turn.
     */
    public RenderedImage process(RenderedImage img){
        Iterator iter = this.iterator();
        
        while(iter.hasNext()){
            ImageProcess next = (ImageProcess)iter.next();
            img = next.process(img);
        }
        
        return img;
    }
}
