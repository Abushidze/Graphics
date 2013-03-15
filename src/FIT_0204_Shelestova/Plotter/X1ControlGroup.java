package FIT_0204_Shelestova.Plotter;

import FIT_0204_Shelestova.Common.CommonGUI;
import FIT_0204_Shelestova.Common.ControlGroup;
import FIT_0204_Shelestova.Common.Controller;

public class X1ControlGroup extends PlotterControlGroup {
    public X1ControlGroup(CommonGUI gui, String name, final int min, final int max, final int init) {
        super(gui, name, min, max, init);
    }

    protected boolean canChange(int value){
        Controller c = gui.getController();
        if (centerCause(value, c.getX2(), c.getY1(), c.getY2(), c.getR1(), c.getR2())){
            return true;
        }
        else{
            showCenterWarning();
            return false;
        }
    }
}
