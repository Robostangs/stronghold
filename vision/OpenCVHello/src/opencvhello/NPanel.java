/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencvhello;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;

/**
 *
 * @author Alex
 */
public class NPanel extends JPanel {
    MatOfByte mem = new MatOfByte();
    
    public void setMem(Mat m) {
        Highgui.imencode(".bmp", m, mem);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(mem!=null && !mem.empty()) {
        try {
             //To change body of generated methods, choose Tools | Templates.
            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
            BufferedImage buff = (BufferedImage) im;
            g.drawImage(buff, 0, 0, null);
        } catch (IOException ex) {
            Logger.getLogger(NPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    
}
