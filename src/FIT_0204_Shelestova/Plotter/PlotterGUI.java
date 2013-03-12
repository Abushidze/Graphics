package FIT_0204_Shelestova.Plotter;

import FIT_0204_Shelestova.Common.*;

import javax.swing.*;
import java.awt.*;

public class PlotterGUI extends CommonGUI{
    private ControlGroup x1ControlGroup;
    private ControlGroup y1ControlGroup;
    private ControlGroup r1ControlGroup;

    private ControlGroup x2ControlGroup;
    private ControlGroup y2ControlGroup;
    private ControlGroup r2ControlGroup;

    private ControlGroup nControlGroup;

    PlotterGUI(Controller controller){
        super(controller);
        setTitle("Plotter");
    }

    protected void createWidgets(){
        super.createWidgets();

        x1ControlGroup = new ControlGroup(this, "x1", -1000, 1000, 0);
        y1ControlGroup = new ControlGroup(this, "y1", -1000, 1000, 0);
        r1ControlGroup = new ControlGroup(this, "r1", -1000, 1000, 10);

        x2ControlGroup = new ControlGroup(this, "x2", -1000, 1000, 0);
        y2ControlGroup = new ControlGroup(this, "y2", -1000, 1000, 0);
        r2ControlGroup = new ControlGroup(this, "r2", -1000, 1000, 5);

        nControlGroup = new ControlGroup(this, "n", 2, 10, 2);

    }
    protected void addWidgetsToControlPanel() {
        super.addWidgetsToControlPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        controlPanel.add(x1ControlGroup, gbc);

        gbc.gridx = 1;
        controlPanel.add(y1ControlGroup, gbc);

        gbc.gridx = 2;
        controlPanel.add(r1ControlGroup, gbc);

        gbc.gridx = 3;
        controlPanel.add(x2ControlGroup, gbc);

        gbc.gridx = 4;
        controlPanel.add(y2ControlGroup, gbc);

        gbc.gridx = 5;
        controlPanel.add(r2ControlGroup, gbc);

        gbc.gridx = 6;
        controlPanel.add(nControlGroup, gbc);
    }
    protected void setHeights() {
        super.setHeights();
        int controlPanelHeight =  getFullHeight(nControlGroup) + getFullHeight(controlPanel);
        Dimension controlDim = new Dimension(controlPanel.getMinimumSize().width, controlPanelHeight);
        controlPanel.setPreferredSize(new Dimension(width, controlPanelHeight));
        controlPanel.setMinimumSize(controlDim);
        int frameHeight =  getFullHeight(controlPanel) + getFullHeight(drawPanel);
        Dimension frameDim = new Dimension(this.getMinimumSize().width, frameHeight);
        setPreferredSize(new Dimension(width, height));

        setMinimumSize(frameDim);

    }
    public void widgetChanged(JComponent component){
        super.widgetChanged(component);
    }
}
