
/**
 * Write a description of GrayScaleConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    //I started with the image I wanted (inImage) 
    public ImageResource makeGray(ImageResource inImage) {
        //I made a blank image of the same size (outImage) 
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //For each pixel in outImage 
        for (Pixel pixel : outImage.pixels()) {
            //Look at the corresponding pixel in inImage (Call it inPixel)
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            //Computer inPixel’s red + inPixel’s green + inpixel’s blue
            //Divide that sum by 3 (call it average)
            int average = ((inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) )/3;
            //Set pixel’s red to average
            pixel.setRed(average);
            //Set pixel’s green to average
            pixel.setGreen(average);
            //Set pixel’s blue to average
            pixel.setBlue(average);
        }
        return outImage;       
    }
    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            gray.draw();
        }
    }
    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }

}
	