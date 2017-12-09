/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
//import org.opencv.highgui.VideoCapture;

/**
 *
 * @author Usuario
 */
public class VideoAnalyzer {

    public void loadVideo() {
        JFileChooser jf = new JFileChooser();
        //jf.showOpenDialog(null);
        
        VideoCapture video = new VideoCapture("green_ball.mp4");
        Mat frame = new Mat();
        JFrame jframe = new JFrame("MyTitle");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel vidpanel = new JLabel();
        jframe.setContentPane(vidpanel);
        jframe.setSize(900, 900);
        jframe.setVisible(true);

        if(!video.isOpened()){
            System.out.println("Camera fechada");
        }
        
        int i = 1;
        while(video.read(frame)){
            
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(VideoAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
            }
            vidpanel.setIcon(new ImageIcon(matToBufferedImage(frame)));
            vidpanel.repaint();
            i++;
        }
        System.out.println("Frames: " + i);
        
        
    }
    
    
    /**
     * Converts/writes a Mat into a BufferedImage.
     *
     * @param matrix Mat of type CV_8UC3 or CV_8UC1
     * @return BufferedImage of type TYPE_3BYTE_BGR or TYPE_BYTE_GRAY
     */
    public static BufferedImage matToBufferedImage(Mat matrix) {
        int cols = matrix.cols();
        int rows = matrix.rows();
        int elemSize = (int) matrix.elemSize();
        byte[] data = new byte[cols * rows * elemSize];
        int type;
        matrix.get(0, 0, data);
        switch (matrix.channels()) {
            case 1:
                type = BufferedImage.TYPE_BYTE_GRAY;
                break;
            case 3:
                type = BufferedImage.TYPE_3BYTE_BGR;
                // bgr to rgb  
                byte b;
                for (int i = 0; i < data.length; i = i + 3) {
                    b = data[i];
                    data[i] = data[i + 2];
                    data[i + 2] = b;
                }
                break;
            default:
                return null;
        }
        BufferedImage image = new BufferedImage(cols, rows, type);
        image.getRaster().setDataElements(0, 0, cols, rows, data);
        return image;
    }

}
