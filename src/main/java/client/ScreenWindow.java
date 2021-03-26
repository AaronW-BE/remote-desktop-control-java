package client;

import utils.ScaleUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import static utils.Lang.L;

/**
 * @author AaronW
 */
public class ScreenWindow extends JFrame {

    ScreenWindow() throws AWTException {
        int windowWidth = 1440;
        int windowHeight = 810;

        this.setTitle(L("title"));
        this.setSize(windowWidth, windowHeight);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("screen size" + screenSize.toString());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocation(
                (int) (screenSize.getWidth() - windowWidth) / 2,
                (int) (screenSize.getHeight() - windowHeight) / 2
        );

        Robot robot = new Robot();

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("clicked");
                Point dstPoint = ScaleUtils.getPoint(
                        new Point(e.getX(), e.getY()),
                        new Dimension(windowWidth, windowHeight),
                        screenSize
                );
                System.out.println(String.format("dst point x %s, y %s ", dstPoint.x, dstPoint.y));
                robot.mouseMove((int) dstPoint.getX(), (int) dstPoint.getY());
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
//                robot.mouseMove(e.getX(), e.getY());
            }
        });

        Rectangle screenRectangle = new Rectangle(screenSize);
        Canvas c = new Canvas() {
            @Override
            void draw(Graphics g) {
                BufferedImage image = robot.createScreenCapture(screenRectangle);
                g.drawImage(image, 0, 0, windowWidth, windowHeight - 28, 0, 0, screenSize.width, screenSize.height, null);
//                g.drawImage(image, 0, 0, windowWidth, windowHeight, null);
            }
        };
        c.setSize(windowWidth, windowHeight);
        this.add(c);
    }
}

abstract class Canvas extends JPanel {
    public Canvas() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000 / 25);
                        repaint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * draw in every frame
     * @param g Graphics
     */
    abstract void draw(Graphics g);
}
