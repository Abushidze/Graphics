package FIT_0204_Shelestova.Common;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

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

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public Canvas(CommonGUI gui, Controller controller){
        super(gui);
        this.controller = controller;

        // drag-n-drop
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                event = e;
                changed();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                event = e;
                changed();
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
