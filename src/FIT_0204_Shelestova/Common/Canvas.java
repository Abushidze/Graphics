package FIT_0204_Shelestova.Common;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import static java.lang.StrictMath.abs;

public class Canvas extends MyPanel{
    private Color color = new Color(255, 255, 240);
    private Controller controller;
    private BufferedImage bimage;
    private TexturePaint texture;
    private Rectangle rect;
    private int[] bits;
    private MouseEvent event;
    private int currentX;
    private int currentY;
    private boolean move1 = false;
    private boolean move2 = false;
    private int x;
    private int y;
    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public Canvas(CommonGUI gui, Controller controller){
        super(gui);
        this.controller = controller;
       final  Controller c = controller;

        // drag-n-drop
       /* addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                event = e;
                changed();
            }

        });
        */
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                x = e.getX() - c.getXnull();
                y  = c.getYnull() - e.getY();
                if (move1){
                    if(c.canSetXY(x, y, c.getX2(), c.getY2(), c.getR1(), c.getR2())){
                        c.setX1(x); c.setY1(y);
                    }
                }
                if (move2){
                    if(c.canSetXY(c.getX1(), c.getY1(), x, y, c.getR1(), c.getR2())){
                        c.setX2(x); c.setY2(y);
                    }
                }
                repaint();;
            }
        });
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {

                x = e.getX() - c.getXnull();
                y  = c.getYnull() - e.getY();
                int dx1 = c.getX1() - x;
                int dx2 = c.getX2() - x;
                int dy1 = c.getY1() - y;
                int dy2 = c.getY2() - y;

                if ( abs(dx1) < 4 && abs(dy1) < 4){
                   move1 = true;
                }
                if( abs(dx2) < 4 && abs(dy2) < 4){
                   move2 = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x = e.getX() - c.getXnull();
                y  = c.getYnull() - e.getY();
                if(move1 && move2){
                    c.setX1(x); c.setY1(y);
                    c.setX2(x); c.setY2(y);
                }
                else if (move1){
                    if(c.canSetXY(x, y, c.getX2(), c.getY2(), c.getR1(), c.getR2())){
                        c.setX1(x); c.setY1(y);
                    }
                }
                else if (move2){
                    if(c.canSetXY(c.getX1(), c.getY1(), x, y, c.getR1(), c.getR2())){
                        c.setX2(x); c.setY2(y);
                    }
                }
                repaint();
                move1 = false;
                move2 = false;

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });



    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        Dimension size = getSize();
        Insets insets = getInsets();


        int width = size.width - insets.left -insets.right;
        int height = size.height - insets.top - insets.bottom;

        controller.setPanelWidth(width);
        controller.setPanelHeight(height);

        bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        bits = bimage.getRGB(
                0,
                0,
                width,
                height,
                null,
                0,
                width);
        controller.fillMemory(bits);
        bimage.setRGB(
                0,
                0,
                width,
                height,
                bits,
                0,
                width);



        rect = new Rectangle(0,0,
                bimage.getWidth(), bimage.getHeight() );
        texture = new TexturePaint(bimage, rect);



        g2d.setPaint(texture);
        g2d.fillRect(0,0, width , height);

    }
    public void changed(){
        currentX = event.getX();
        currentY = event.getY();
        super.changed();
    }
}
