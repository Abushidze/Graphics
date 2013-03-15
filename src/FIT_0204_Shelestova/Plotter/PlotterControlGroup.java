package FIT_0204_Shelestova.Plotter;

import FIT_0204_Shelestova.Common.CommonGUI;
import FIT_0204_Shelestova.Common.ControlGroup;

import javax.swing.*;

import static java.lang.Math.pow;

public class PlotterControlGroup extends ControlGroup {
    public PlotterControlGroup(CommonGUI gui, String name, int min, int max, int init) {
        super(gui, name, min, max, init);
    }

    protected boolean centerCause(int x1, int x2, int y1, int y2, int r1, int r2){
        double d = pow(x2 - x1, 2) + pow(y1 - y2, 2);

        if (d <= pow(r1, 2)){
            return true;
        }
        else {
            return false;
        }
    }


    protected void showCenterWarning(){
        JOptionPane.showMessageDialog(this,
                "Bad value. The center of second circle must be in first circle.",
                "Value warning",
                JOptionPane.WARNING_MESSAGE);
    }


}
