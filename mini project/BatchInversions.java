
/**
 * Write a description of Batchinversions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class BatchInversions {
    //I started with the image I wanted (inImage) 
    public ImageResource makeInversion(ImageResource inImage) {
        //I made a blank image of the same size (outImage) 
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //For each pixel in outImage 
        for (Pixel pixel : outImage.pixels()) {
            //Look at the corresponding pixel in inImage (Call it inPixel)
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            //Get inPixel’s red , inPixel’s green , inpixel’s blue
            //Convert to Inversion witch is 255-;
            int inversionRed = 255- inPixel.getRed();
            int inversionGreen = 255 -inPixel.getGreen(); 
            int inversionBlue = 255 - inPixel.getBlue();
            //Set pixel’s red to inversionRed
            pixel.setRed(inversionRed);
            //Set pixel’s green to inversionGreen
            pixel.setGreen(inversionGreen);
            //Set pixel’s blue to inversionBlue
            pixel.setBlue(inversionBlue);
        }
        return outImage;       
    }
    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){            
            ImageResource inImage = new ImageResource(f);
            String fname = inImage.getFileName();
            String newName = "inverted-" + fname;
            ImageResource invertedImage = makeInversion(inImage);
            invertedImage.setFileName(newName);
            // files saved in current folder, 
            //Need to figure out how to save to different location
            invertedImage.save();
            invertedImage.draw();
        }
    }    
}


