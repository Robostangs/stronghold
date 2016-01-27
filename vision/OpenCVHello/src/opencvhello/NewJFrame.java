/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opencvhello;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Alex
 */
public class NewJFrame extends javax.swing.JFrame {

    class CameraStream implements Runnable {

        private final int[] START_BYTES = {255, 216};
        private final int[] END_BYTES = {255, 217};
        long lastRepaint = 0L;
        private long lastFPSCheck = 0L;
        private int lastFPS = 0;
        private int fpsCounter = 0;
        private BufferedImage imageToDraw;

        @Override
        public void run() {
            URLConnection connection = null;
            InputStream stream = null;
            ByteArrayOutputStream imageBuffer = new ByteArrayOutputStream();

            while (true) {
                try {
                    Mat mat;
                    URL url = new URL("http://axis-camera.local/mjpg/video.mjpg");
                    connection = url.openConnection();
                    connection.setReadTimeout(1000);
                    stream = connection.getInputStream();
                    while ((true)) {
                        while (System.currentTimeMillis() - this.lastRepaint < 10L) {
                            stream.skip(stream.available());
                            Thread.sleep(1L);
                        }
                        stream.skip(stream.available());

                        imageBuffer.reset();
                        for (int i = 0; i < this.START_BYTES.length;) {
                            int b = stream.read();
                            if (b == this.START_BYTES[i]) {
                                i++;
                            } else {
                                i = 0;
                            }
                        }
                        for (int i = 0; i < START_BYTES.length; i++) {
                            imageBuffer.write(START_BYTES[i]);
                        }
                        for (int i = 0; i < END_BYTES.length;) {
                            int b = stream.read();
                            imageBuffer.write(b);
                            if (b == END_BYTES[i]) {
                                i++;
                            } else {
                                i = 0;
                            }
                        }
                        if (System.currentTimeMillis() - this.lastFPSCheck > 500L) {
                            this.lastFPSCheck = System.currentTimeMillis();
                            this.lastFPS = (this.fpsCounter * 2);
                            this.fpsCounter = 0;
                        }
                        this.lastRepaint = System.currentTimeMillis();
                        ByteArrayInputStream tmpStream = new ByteArrayInputStream(imageBuffer.toByteArray());
                        this.imageToDraw = ImageIO.read(tmpStream);

                        byte[] data = ((DataBufferByte) imageToDraw.getRaster().getDataBuffer()).getData();
                        mat = new Mat(imageToDraw.getHeight(), imageToDraw.getWidth(), CvType.CV_8UC3);
                        mat.put(0, 0, data);
                        final Mat newMat = mat.clone();
                        new Thread(new Runnable() {
                            public void run() {
                                processImage(newMat);

                            }
                        }).start();
                    }
                } catch (Exception e) {
                    this.imageToDraw = null;
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException ex) {
                }
            }
        }

    }

    int minh = 0, mins = 0, minv = 0, maxh = 255, maxs = 255, maxv = 50, mode = 1, x = 0, y = 0;
    double cameraAngle = 0;
    double distance = 0, heading = 0;
    long start = 0;

    void processImage(Mat frames) {
        start = System.currentTimeMillis();
        Mat original = frames.clone();
        makeBinaryFromColorRange(minh, mins, minv, maxh, maxs, maxv, frames, frames);
        Mat binaryImage = frames.clone();
        ArrayList<MatOfPoint> list = new ArrayList<MatOfPoint>();
        findContours(frames, list);
        ArrayList<MatOfPoint> badTargets = new ArrayList<MatOfPoint>();
        ArrayList<MatOfPoint> goodTargets = new ArrayList<MatOfPoint>();
        getTargets(list, goodTargets, badTargets);
        proccesseImages(frames, goodTargets);
        System.out.println((System.currentTimeMillis() - start));

        //  jLabel1.setText("D: " + distance+ " H: " + heading);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                jLabel2.setText("D: " + distance + " H: " + heading);
            }
        });
        System.out.println("D: " + distance);
//                System.out.println("H: " + heading);
        paintGUI(goodTargets, badTargets, original, binaryImage);
    }

    private void makeBinaryFromColorRange(int minH, int minS, int minV, int maxH, int maxS, int maxV, Mat input, Mat output) {
        Imgproc.cvtColor(input, input, Imgproc.COLOR_BGR2HSV);
        Core.inRange(input, new Scalar(minH, minS, minV), new Scalar(maxH, maxS, maxV), output);
    }

    private void findContours(Mat input, ArrayList<MatOfPoint> outputlist) {
        Imgproc.findContours(input, outputlist, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_TC89_KCOS);
    }

    private void getTargets(ArrayList<MatOfPoint> input, ArrayList<MatOfPoint> positive, ArrayList<MatOfPoint> negative) {
        for (MatOfPoint l : input) {
            double area = Imgproc.moments(l, true).get_m00();
            if (area > 200) {
                Rect r = Imgproc.boundingRect(l);
                //RotatedRect rr = Imgproc.minAreaRect(new MatOfPoint2f(l.toArray()));
                //% area .32857
                //width/hight 20/14 = 1.4285
                if ((((double) area / (r.width * r.height)) < .5 && ((double) area / (r.width * r.height)) > .15) && (((double) r.width / r.height) < 2 && ((double) r.width / r.height) > 1.2) && r.width > r.height) {
                    positive.add(l);
                } else {
                    negative.add(l);
//                    System.out.println("%A: " + ((double) area / (r.width * r.height)));
//                    System.out.println("AR: " + ((double) r.width / r.height));
                }
            }
        }
    }

    private void proccesseImages(Mat inputMat, ArrayList<MatOfPoint> targetList) {
        if (targetList.size() == 1) {
            Rect goal;
            goal = Imgproc.boundingRect(targetList.get(0));
            double targetWidthInches = 20;

            //Distance
            double fieldwidthInches = ((double) inputMat.width() / (double) goal.width) * targetWidthInches;//
            distance = ((double) fieldwidthInches / 2) / Math.tan(Math.toRadians((double) 28.7 + this.cameraAngle)); //24.5. 30.7
            //^ Distance in "
            //Heading
            double o = (targetWidthInches / goal.width) * (((double) inputMat.width() / 2) - (goal.x + (goal.width / 2)));//In per pixel * pixels
            heading = Math.toDegrees(Math.atan(o / distance));
        } else if (targetList.isEmpty()) {
            heading = 0;
            distance = 0;
        }
    }

    private void paintGUI(ArrayList<MatOfPoint> positive, ArrayList<MatOfPoint> negative, Mat or, Mat bi) {

        //if (mode == 1) {
        if (!negative.isEmpty() || !positive.isEmpty()) {
            Core.circle(or, new Point(x, y), 2, new Scalar(255, 0, 0));

            for (MatOfPoint l : negative) {
                Rect r = Imgproc.boundingRect(l);
                Core.rectangle(or, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), new Scalar(255, 0, 0), 1);
            }
            for (MatOfPoint l : positive) {
                Rect r = Imgproc.boundingRect(l);
                Core.rectangle(or, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), new Scalar(0, 0, 255), 1);
            }
        } else {

        }
        nPanel1.setMem(or);
        nPanel1.repaint();
        //Highgui.imencode(".bmp", or, mem);
        //}
        //if (mode == 2) {
        Imgproc.cvtColor(bi, bi, Imgproc.COLOR_GRAY2RGB);
        if (!negative.isEmpty() || !positive.isEmpty()) {
            for (MatOfPoint l : negative) {
                Rect r = Imgproc.boundingRect(l);
                Core.rectangle(bi, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), new Scalar(255, 0, 0), 2);
                Core.circle(bi, new Point(r.x + (r.width / 2), r.y + (r.height / 2)), 3, new Scalar(255, 0, 0));

            }

            for (MatOfPoint l : positive) {
                Rect r = Imgproc.boundingRect(l);
                Core.rectangle(bi, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), new Scalar(0, 0, 255), 2);
            }
        } else {

        }
        cPanel1.setMem(bi);
        cPanel1.repaint();
            //Highgui.imencode(".bmp", bi, mem);
        // }
    }

    public void setNumbers(int minh, int mins, int minv, int maxh, int maxs, int maxv) {
        this.minh = minh;
        this.mins = mins;
        this.minv = minv;
        this.maxh = maxh;
        this.maxs = maxs;
        this.maxv = maxv;
    }

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
        new Thread(new CameraStream()).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        cPanel1 = new opencvhello.CPanel();
        jSlider2 = new javax.swing.JSlider();
        jSlider3 = new javax.swing.JSlider();
        jSlider4 = new javax.swing.JSlider();
        jSlider5 = new javax.swing.JSlider();
        jSlider6 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jSlider7 = new javax.swing.JSlider();
        jSlider8 = new javax.swing.JSlider();
        jButton1 = new javax.swing.JButton();
        nPanel1 = new opencvhello.NPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSlider1.setMaximum(179);
        jSlider1.setToolTipText("");
        jSlider1.setValue(89);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        cPanel1.setPreferredSize(new java.awt.Dimension(640, 480));

        javax.swing.GroupLayout cPanel1Layout = new javax.swing.GroupLayout(cPanel1);
        cPanel1.setLayout(cPanel1Layout);
        cPanel1Layout.setHorizontalGroup(
            cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        cPanel1Layout.setVerticalGroup(
            cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        jSlider2.setMaximum(255);
        jSlider2.setToolTipText("");
        jSlider2.setValue(128);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        jSlider3.setMaximum(255);
        jSlider3.setToolTipText("");
        jSlider3.setValue(128);
        jSlider3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        jSlider4.setMaximum(179);
        jSlider4.setToolTipText("");
        jSlider4.setValue(179);
        jSlider4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        jSlider5.setMaximum(255);
        jSlider5.setToolTipText("");
        jSlider5.setValue(255);
        jSlider5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        jSlider6.setMaximum(255);
        jSlider6.setToolTipText("");
        jSlider6.setValue(255);
        jSlider6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        jLabel1.setText("jLabel1");

        jSlider7.setMaximum(2);
        jSlider7.setPaintTicks(true);
        jSlider7.setSnapToTicks(true);
        jSlider7.setToolTipText("");
        jSlider7.setValue(1);
        jSlider7.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider7StateChanged(evt);
            }
        });

        jSlider8.setMaximum(50);
        jSlider8.setMinimum(-50);
        jSlider8.setValue(0);
        jSlider8.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider8StateChanged(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout nPanel1Layout = new javax.swing.GroupLayout(nPanel1);
        nPanel1.setLayout(nPanel1Layout);
        nPanel1Layout.setHorizontalGroup(
            nPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        nPanel1Layout.setVerticalGroup(
            nPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSlider5, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSlider6, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSlider7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jSlider8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addComponent(jButton1)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSlider5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSlider6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSlider7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSlider8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
        // TODO add your handling code here:
        setNumbers(jSlider1.getValue(), jSlider2.getValue(), jSlider3.getValue(), jSlider4.getValue(), jSlider5.getValue(), jSlider6.getValue());
        jLabel1.setText(jSlider1.getValue() + "," + jSlider2.getValue() + "," + jSlider3.getValue() + "," + jSlider4.getValue() + "," + jSlider5.getValue() + "," + jSlider6.getValue());
    }//GEN-LAST:event_jSlider2StateChanged

    private void jSlider7StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider7StateChanged
        // TODO add your handling code here:
        jLabel1.setText("D: " + distance + " H: " + heading);
        //cPanel1.setMode(jSlider7.getValue());
    }//GEN-LAST:event_jSlider7StateChanged

    private void jSlider8StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider8StateChanged
        // TODO add your handling code here:
        //cPanel1.setAngle(jSlider8.getValue());
    }//GEN-LAST:event_jSlider8StateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //cPanel1.getNetImage();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private opencvhello.CPanel cPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSlider jSlider3;
    private javax.swing.JSlider jSlider4;
    private javax.swing.JSlider jSlider5;
    private javax.swing.JSlider jSlider6;
    private javax.swing.JSlider jSlider7;
    private javax.swing.JSlider jSlider8;
    private opencvhello.NPanel nPanel1;
    // End of variables declaration//GEN-END:variables
}
