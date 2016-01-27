/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opencvhello;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

/**
 *
 * @author Alex
 */
public class CPanel extends JPanel {
    
    
    MatOfByte mem  = new MatOfByte();
    
    public void setMem(Mat m) {
        Highgui.imencode(".bmp", m, mem);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(mem != null && !mem.empty()) {
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
    
//    Mat frames;
//    MatOfByte mem;
//    int minh = 0, mins = 0, minv = 0, maxh = 255, maxs = 255, maxv = 50, mode = 1, x = 0, y = 0;
//
//    double distance = 0, heading = 0;
//    Mat rawimg;
//    RRCPClient client;
//    VideoCapture vc = null;
//
//    private void makeBinaryFromColorRange(int minH, int minS, int minV, int maxH, int maxS, int maxV, Mat input, Mat output) {
//        Imgproc.cvtColor(input, input, Imgproc.COLOR_BGR2HSV);
//        Core.inRange(input, new Scalar(minH, minS, minV), new Scalar(maxH, maxS, maxV), output);
//    }
//
//    private void findContours(Mat input, ArrayList<MatOfPoint> outputlist) {
//        Imgproc.findContours(input, outputlist, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_TC89_KCOS);
//    }
//
//    private void getTargets(ArrayList<MatOfPoint> input, ArrayList<MatOfPoint> positive, ArrayList<MatOfPoint> negative) {
//        for (MatOfPoint l : input) {
//            double area = Imgproc.moments(l, true).get_m00();
//            if (area > 100) {
//                Rect r = Imgproc.boundingRect(l);
//                //% area .32857
//                //width/hight 20/14 = 1.4285
//                if ((((double)area / (r.width * r.height)) < .5 && ((double)area / (r.width * r.height)) > .15) && (((double) r.width / r.height) < 1.7 && ((double) r.width / r.height) > 1.2) && r.width > r.height) {
//                    positive.add(l);
//                } else {
//                    negative.add(l);
//                    System.out.println("%A: "+ ((double)area / (r.width * r.height)));
//                    System.out.println("AR: "+ ((double) r.width / r.height));
//                }
//            }
//        }
//    }
//
//    private void proccesseImages(Mat inputMat, ArrayList<MatOfPoint> targetList) {
//        if (targetList.size() == 1) {
//            Rect goal;
//            goal = Imgproc.boundingRect(targetList.get(0));
//            double targetWidthInches = 20;
//
//            //Distance
//            double fieldwidthInches = ((double)inputMat.width() / (double)goal.width) * targetWidthInches;//
//            distance = ((double)fieldwidthInches / 2) / Math.tan(Math.toRadians((double)28.7+this.cameraAngle)); //24.5. 30.7
//            //^ Distance in "
//            //Heading
//            double o = (targetWidthInches / goal.width) * (((double) inputMat.width() / 2) - (goal.x + (goal.width / 2)));//In per pixel * pixels
//            heading = Math.toDegrees(Math.atan(o / distance));
//        } else if(targetList.isEmpty()) {
//            heading = 0;
//            distance = 0;
//        }
//    }
//
//    private void paintGUI(ArrayList<MatOfPoint> positive, ArrayList<MatOfPoint> negative, Mat or, Mat bi) {
//        if (mode == 0) {
//            Highgui.imencode(".bmp", frames, mem);
//        }
//
//        //if (mode == 1) {
//            if (!negative.isEmpty() || !positive.isEmpty()) {
//                Core.circle(or, new Point(x, y), 2, new Scalar(255, 0, 0));
//
//                for (MatOfPoint l : negative) {
//                    Rect r = Imgproc.boundingRect(l);
//                    Core.rectangle(or, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), new Scalar(255, 0, 0), 1);
//                }
//                for (MatOfPoint l : positive) {
//                    Rect r = Imgproc.boundingRect(l);
//                    Core.rectangle(or, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), new Scalar(0, 0, 255), 1);
//                }
//            } else {
//                
//            }
//            Highgui.imencode(".bmp", or, mem);
//        //}
//        //if (mode == 2) {
//            Imgproc.cvtColor(bi, bi, Imgproc.COLOR_GRAY2RGB);
//            if (!negative.isEmpty() || !positive.isEmpty()) {
//                for (MatOfPoint l : negative) {
//                    Rect r = Imgproc.boundingRect(l);
//                    Core.rectangle(bi, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), new Scalar(255, 0, 0), 2);
//                    Core.circle(bi, new Point(r.x+(r.width/2), r.y+(r.height/2)), 3, new Scalar(255, 0, 0));
//
//                }
//
//                for (MatOfPoint l : positive) {
//                    Rect r = Imgproc.boundingRect(l);
//                    Core.rectangle(bi, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), new Scalar(0, 0, 255), 2);
//                }         
//            } else {
//                
//            }
//            Highgui.imencode(".bmp", bi, mem);
//       // }
//    }
//    
//    private void drawImageOnPanel(Graphics  g) throws IOException {
//        Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
//        BufferedImage buff = (BufferedImage) im;
//        g.drawImage(buff, 0, 0, null);
//    }
//    long start = 0;
//    @Override
//    public void paintComponent(Graphics g) {
//
//        
//        if (rawimg != null && !rawimg.empty() && rawimg.channels() == 3) {
//            
//            frames = rawimg.clone();
//            try {
//                        start = 0;
//        
//        start = System.currentTimeMillis();
//                Mat original = frames.clone();
//                makeBinaryFromColorRange(minh, mins, minv, maxh, maxs, maxv, frames, frames);
//                Mat binaryImage = frames.clone();
//                ArrayList<MatOfPoint> list = new ArrayList<MatOfPoint>();
//                findContours(frames, list);
//                ArrayList<MatOfPoint> badTargets = new ArrayList<MatOfPoint>();
//                ArrayList<MatOfPoint> goodTargets = new ArrayList<MatOfPoint>();
//                getTargets(list, goodTargets, badTargets);
//                proccesseImages(frames, goodTargets);
//                System.out.println((System.currentTimeMillis() - start));
//                System.out.println("D: " + distance);
//                System.out.println("H: " + heading);
//                paintGUI(goodTargets, badTargets, original, binaryImage);
//                drawImageOnPanel(g);
//                //Mat kerner = Mat.ones(4, 4, Imgproc.MORPH_RECT);
//                //webSource.retrieve(frames);
////                Imgproc.cvtColor(frames, frames, Imgproc.COLOR_BGR2HSV);
////                Core.inRange(frames, new Scalar(minh, mins, minv), new Scalar(maxh, maxs, maxv), frames);
////                 Imgproc.findContours(frames, list, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_TC89_KCOS);
//                //if (mode != 0) {
//                //Imgproc.morphologyEx(frames, frames, Imgproc.MORPH_ERODE, kerner);
//                //Imgproc.morphologyEx(frames, frames, Imgproc.MORPH_DILATE, kerner2);
//                //} else {
//                //Imgproc.morphologyEx(frames, frames, Imgproc.MORPH_CLOSE, kerner);
//                //}
//                //MatOfPoint s = new MatOfPoint();
//                //% area .32857
//                //width/hight 20/14 = 1.4285
////                for (MatOfPoint l : list) {
////                    double area = Imgproc.moments(l, true).get_m00();
////                    if(area > 20) {
////                        Rect r = Imgproc.boundingRect(l);
////                        if ((area / (r.width * r.height)) < .5) {
////                            if(((double)r.width/r.height) < 1.6 && ((double)r.width/r.height) > 1.2) {
////                                htragets.add(l);
////                            }
////                        }
////                    }
////                }
////                for (MatOfPoint l : list) {
////                    double area = Imgproc.moments(l, true).get_m00();
////
////                    if (area > 25) {
////                        Rect r = Imgproc.boundingRect(l);
////                        //System.out.println(i + " Area %: " + (area / (r.width * r.height)) + "%");
////                        //System.out.println(i + " AR: " + ((double) r.width / r.height) * 100);
////                        if ((((double) r.width / r.height) * 100) > 90 && (((double) r.width / r.height) * 100) < 110 && (area / (r.width * r.height)) > .9) { //Percent area of box
////
////                            htragets.add(l);
////                        } else {
////                            vtragets.add(l);
////                        }
////                        
////                    }
////                    
////                }
////                double targetWidth = 0, rx1 = 0, ry1 = 0, rx2= 0, ry2 = 0;
////                if(htragets.size() == 1) {
////                    Rect goal;
////                    goal = Imgproc.boundingRect(htragets.get(0));
////                    double targetWidthInches = 16.75;
////                    
////                    //Distance
////                    double fieldwidth = (frames.width()/goal.width)*targetWidthInches;
////                    power = (fieldwidth/2)/Math.tan(Math.toRadians(30.7+this.cameraAngle)); //24.5
////                    //^ Distance in "
////                    //Heading
////                    double o = (targetWidthInches/targetWidth)*(((double)frames.width()/2)-x);
////                    wheel = Math.toDegrees(Math.atan(o/power));
////                    
////                }
////                RotatedRect box1 ,box2;
////                if (htragets.size() == 2) {
////                    Rect leftBox;
////                    Rect rightBox;
////                    
////                    box1 = Imgproc.minAreaRect(new MatOfPoint2f(htragets.get(0).toArray()));
////                    box2 = Imgproc.minAreaRect(new MatOfPoint2f(htragets.get(1).toArray()));
////                    System.out.println(box1.angle);
////                    System.out.println(box2.angle);
////                    if(Imgproc.boundingRect(htragets.get(0)).x > Imgproc.boundingRect(htragets.get(1)).x) {
////                         leftBox = Imgproc.boundingRect(htragets.get(1));
////                        rightBox = Imgproc.boundingRect(htragets.get(0));
////                    } else {
////                        leftBox = Imgproc.boundingRect(htragets.get(0));
////                        rightBox = Imgproc.boundingRect(htragets.get(1));
////                    }
////                    rx1 = leftBox.x;
////                    ry1 = leftBox.y;
////                    rx2 = rightBox.width+rightBox.x;
////                    ry2 = rightBox.height+rightBox.y;
////                    x = (int) ((leftBox.x+(rightBox.x+rightBox.width))/2);
////                    y = (int) ((leftBox.y+(rightBox.y+rightBox.height))/2);
////                    targetWidth = rx2-rx1;
////                    double targetWidthInches = 16.75;
////                    //Distance
////                    double fieldwidth = (frames.width()/targetWidth)*targetWidthInches; 
////                    power = (fieldwidth/2)/Math.tan(Math.toRadians(30.7+this.cameraAngle)); //24.5
////                    //^ Distance in "
////                    //Heading
////                    double o = (targetWidthInches/targetWidth)*(((double)frames.width()/2)-x);
////                    wheel = Math.toDegrees(Math.atan(o/power));
////                } else {
////                    wheel = 0; 
////                    power = -1;
////                }
////                if (mode == 0) {
////
////                    Highgui.imencode(".bmp", frames, mem);
//////                    if (client.isConnected()) {
//////                            client.sendCommandWithNumber("power", power);
//////                            client.sendCommandWithNumber("wheel", wheel);
//////                        }
////                }
////
////                if (mode == 1) {
////                    if (!vtragets.isEmpty() || !htragets.isEmpty()) {
////                        Core.circle(original, new Point(x, y), 2, new Scalar(255, 0, 0));
////
////                        System.out.println(wheel);
////                        for (MatOfPoint l : vtragets) {
////                            Rect r = Imgproc.boundingRect(l);
////                            Core.rectangle(original, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), new Scalar(255, 0, 0), 1);
////                        }
////                        if (client.isConnected()) {
////                            client.sendCommandWithNumber("power", power);
////                            client.sendCommandWithNumber("wheel", wheel);
////                        }
////                        for (MatOfPoint l : htragets) {
////                            Rect r = Imgproc.boundingRect(l);
////                            Core.rectangle(original, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), new Scalar(0, 0, 255), 1);
////                        }
////                    } else {
////                        wheel = 0;
////                    }
////                    Highgui.imencode(".bmp", original, mem);
////                }
////                if (mode == 2) {
////                    Imgproc.cvtColor(original2, original2, Imgproc.COLOR_GRAY2RGB);
////                    if (!vtragets.isEmpty() || !htragets.isEmpty()) {
////                        Core.circle(original2, new Point(x, y), 3, new Scalar(255, 0, 0));
////                        //Core.line(original2, new Point(rx1, y), new Point(rx2, y), new Scalar(0, 0, 255));
////
////                        Core.rectangle(original2, new Point(rx1, ry1), new Point(rx2, ry2), new Scalar(255, 0, 0), 4);
////                        //Mat newimg = new Mat(original, new Rect(new Point(rx1, ry1), new Point(rx2, ry2)));
////
////                        for (MatOfPoint l : vtragets) {
////                            Rect r = Imgproc.boundingRect(l);
////                            Core.rectangle(original2, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), new Scalar(255, 0, 0), 2);
////
////                        }
////
////                        for (MatOfPoint l : htragets) {
////                            Rect r = Imgproc.boundingRect(l);
////                            Core.rectangle(original2, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), new Scalar(0, 0, 255), 2);
////                        }
////                        //Imgproc.resize(original2, original2, new Size((double)original2.width()*.4, (double)original2.height()*.4));
////
////                    } else {
////                        wheel = -1000;
////                    }
////                    if (client.isConnected()) {
////                        client.sendCommandWithNumber("power", power);
////                        client.sendCommandWithNumber("wheel", wheel);
////                    }
////                    Highgui.imencode(".bmp", original2, mem);
////
////                }
////                Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
////                BufferedImage buff = (BufferedImage) im;
////                g.drawImage(buff, 0, 0, null);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//
//    }
//
//    public void setNumbers(int minh, int mins, int minv, int maxh, int maxs, int maxv) {
//        this.minh = minh;
//        this.mins = mins;
//        this.minv = minv;
//        this.maxh = maxh;
//        this.maxs = maxs;
//        this.maxv = maxv;
//    }
//
//    public void setMode(int mode) {
//        this.mode = mode;
//    }
//    double cameraAngle = 0;
//
//    public void setAngle(int n) {
//        this.cameraAngle = n * .1;
//    }
//
//    @Override
//    public void run() {
//        //vc = new VideoCapture(1);
//        //http://10.5.48.11/mjpg/video.mjpg
//        //vc.open("http://axis-camera.local/mjpg/video.mjpg");
//
//        frames = new Mat();
//        rawimg = new Mat();
//        mem = new MatOfByte();
//        //client = new RRCPClient("roboRIO-548.local", 2000, 5801);
//        //client.connect();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                getNetImage();
//            }
//        }).start();
//        while (true) {
//            this.repaint();
//
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(CPanel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//    }
//
//    public Mat getImage() {
//        try {
//            Mat mat;
//            BufferedImage img = ImageIO.read(new URL("http://axis-camera.local/jpg/image.jpg"));
//            byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
//            mat = new Mat(img.getHeight(), img.getWidth(), CvType.CV_8UC3);
//            mat.put(0, 0, data);
//            return mat;
//        } catch (IOException ex) {
//            Logger.getLogger(CPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    //UM....
//    private static final int[] START_BYTES = { 255, 216 };
//    private static final int[] END_BYTES = { 255, 217 };
//    long lastRepaint = 0L;
//    private long lastFPSCheck = 0L;
//    private int lastFPS = 0;
//    private int fpsCounter = 0;
//    private BufferedImage imageToDraw;
//
//    public void getNetImage() {
//       URLConnection connection = null;
//        InputStream stream = null;
//        ByteArrayOutputStream imageBuffer = new ByteArrayOutputStream();
//        while (true) {
//            try {
//                Mat mat;
//                URL url = new URL("http://axis-camera.local/mjpg/video.mjpg");
//                connection = url.openConnection();
//                connection.setReadTimeout(1000);
//                stream = connection.getInputStream();
//                while ((true)) {
//                    while (System.currentTimeMillis() - this.lastRepaint < 10L) {
//                        stream.skip(stream.available());
//                        Thread.sleep(1L);
//                    }
//                    stream.skip(stream.available());
//
//                    imageBuffer.reset();
//                    for (int i = 0; i < this.START_BYTES.length;) {
//                        int b = stream.read();
//                        if (b == this.START_BYTES[i]) {
//                            i++;
//                        } else {
//                            i = 0;
//                        }
//                    }
//                    for (int i = 0; i < START_BYTES.length; i++) {
//                        imageBuffer.write(START_BYTES[i]);
//                    }
//                    for (int i = 0; i < END_BYTES.length;) {
//                        int b = stream.read();
//                        imageBuffer.write(b);
//                        if (b == END_BYTES[i]) {
//                            i++;
//                        } else {
//                            i = 0;
//                        }
//                    }
//                    if (System.currentTimeMillis() - this.lastFPSCheck > 500L) {
//                        this.lastFPSCheck = System.currentTimeMillis();
//                        this.lastFPS = (this.fpsCounter * 2);
//                        this.fpsCounter = 0;
//                    }
//                    this.lastRepaint = System.currentTimeMillis();
//                    ByteArrayInputStream tmpStream = new ByteArrayInputStream(imageBuffer.toByteArray());
//                    this.imageToDraw = ImageIO.read(tmpStream);
//                    
//                    byte[] data = ((DataBufferByte) imageToDraw.getRaster().getDataBuffer()).getData();
//                    mat = new Mat(imageToDraw.getHeight(), imageToDraw.getWidth(), CvType.CV_8UC3);
//                    mat.put(0, 0, data);
//                    rawimg = mat;
//                }
//            } catch (Exception e) {
//                this.imageToDraw = null;
//                e.printStackTrace();
//            }
//            try {
//                Thread.sleep(500L);
//            } catch (InterruptedException ex) {
//            }
//        }
//       // while (true) {
//
//            //if(vc.grab()) {
//              //  vc.retrieve(rawimg);
//                
//            //}
//
////        rawimg = getImage();
////            this.repaint();
//            
////            try {
////                Thread.sleep(10L);
////            } catch (InterruptedException ex) {
////            }
//        //}
//    }
}
