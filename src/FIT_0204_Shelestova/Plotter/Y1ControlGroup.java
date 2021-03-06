package FIT_0204_Shelestova.Plotter;

import FIT_0204_Shelestova.Common.CommonGUI;
import FIT_0204_Shelestova.Common.ControlGroup;
import FIT_0204_Shelestova.Common.Controller;

public class Y1ControlGroup extends PlotterControlGroup {
    public Y1ControlGroup(CommonGUI gui, String name, final int min, final int max, final int init) {
        super(gui, name, min, max, init);
    }
    protected boolean canChange(int value){
        Controller c = gui.getController();
        if (centerCause(c.getX1(), c.getX2(), value, c.getY2(), c.getR1(), c.getR2())){
            return true;
        }
        else{
            showCenterWarning();
            return false;
        }
    }
}
