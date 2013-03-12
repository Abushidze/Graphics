package FIT_0204_Shelestova.Rectangle;

import FIT_0204_Shelestova.Common.CommonGUI;
import FIT_0204_Shelestova.Common.ControlGroup;
import FIT_0204_Shelestova.Common.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// выполняет роль посредника между всеми виджетами -- паттерн Mediator
public class RectangleGUI extends CommonGUI {
    private ControlGroup sizeControlGroup;
    private ControlGroup xControlGroup;
    private ControlGroup yControlGroup;

    public RectangleGUI(Controller controller){
        super(controller);
        setTitle("Rectangle");
    }

    protected void setHeights() {
        super.setHeights();
        int controlPanelHeight =  getFullHeight(sizeControlGroup) + getFullHeight(controlPanel);
        Dimension controlDim = new Dimension(controlPanel.getMinimumSize().width, controlPanelHeight);
        controlPanel.setPreferredSize(new Dimension(width, controlPanelHeight));
        controlPanel.setMinimumSize(controlDim);
        int frameHeight =  getFullHeight(controlPanel) + getFullHeight(drawPanel);
        Dimension frameDim = new Dimension(this.getMinimumSize().width, frameHeight);
        setPreferredSize(new Dimension(width, height));

        setMinimumSize(frameDim);

    }


    protected void createWidgets(){
        super.createWidgets();

        sizeControlGroup = new ControlGroup(this, "Size", 0, 1000, 20);
        xControlGroup = new ControlGroup(this, "Position X", -1000, 1000, 0);
        yControlGroup = new ControlGroup(this, "Position Y", -1000, 1000, 0);

    }

    protected void addWidgetsToControlPanel() {
        super.addWidgetsToControlPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        controlPanel.add(sizeControlGroup, gbc);

        gbc.gridx = 1;
        controlPanel.add(xControlGroup, gbc);

        gbc.gridx = 2;
        controlPanel.add(yControlGroup, gbc);

    }

    public void widgetChanged(JComponent widget){
        super.widgetChanged(widget);
        if (widget == sizeControlGroup){
             controller.setSize(sizeControlGroup.getCurrentValue());
        }
        if(widget == xControlGroup){
            controller.setXposition(xControlGroup.getCurrentValue());
        }
        if (widget == yControlGroup){
            controller.setYposition(yControlGroup.getCurrentValue());
        }
        drawPanel.repaint();

    }


}
