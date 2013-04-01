package FIT_0204_Shelestova.Common;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    protected  CommonGUI gui;
    protected MyPanel(CommonGUI gui){
        super(new GridBagLayout());
        this.gui = gui;
    }
    protected void changed(){
        gui.widgetChanged(this);
    }
}
