package FIT_0204_Shelestova.Plotter;

import FIT_0204_Shelestova.Common.CommonGUI;
import FIT_0204_Shelestova.Common.Controller;

public class R2ControlGroup extends PlotterControlGroup {
    public R2ControlGroup(CommonGUI gui, String name, int min, int max, int init) {
        super(gui, name, min, max, init);
    }
    protected boolean canChange(int value){
        Controller c = gui.getController();
        if (centerCause(c.getX1(), c.getX2(), c.getY1(), c.getY2(), c.getR1(), value)){
            return true;
        }
        else{
            showCenterWarning();
            return false;
        }
    }
}
