package FIT_0204_Shelestova.Common;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawPanel extends MyPanel{
    private String title = "Draw Panel";
    private Color color = new Color(240, 255, 255);
    private int[] bits;

    public DrawPanel(CommonGUI gui){
        super(gui);
        setBackground(color);
        setBorder(BorderFactory.createTitledBorder(
                new LineBorder(Color.GRAY, 1, true), title));
    }
}
