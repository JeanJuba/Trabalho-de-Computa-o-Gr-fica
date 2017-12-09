/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv;

import org.opencv.core.Core;
import util.VideoAnalyzer;

/**
 *
 * @author Usuario
 */
public class MainClass {

    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    // colocar o endereço completo do dll do opencv_java330
    //System.load("C:\\Users\\Alcsaw\\Documents\\OpenCV\\opencv\\build\\java\\x64\\opencv_java330.dll");        
    // TODO code application logic here
        VideoAnalyzer videoAnalyzer = new VideoAnalyzer();
        videoAnalyzer.loadVideo();
    }
    
}
