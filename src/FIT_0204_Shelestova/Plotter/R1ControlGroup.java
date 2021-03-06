package FIT_0204_Shelestova.Plotter;

import FIT_0204_Shelestova.Common.CommonGUI;
import FIT_0204_Shelestova.Common.ControlGroup;
import FIT_0204_Shelestova.Common.Controller;


public class R1ControlGroup extends PlotterControlGroup {
    public R1ControlGroup(CommonGUI gui, String name, int min, int max, int init) {
        super(gui, name, min, max, init);
    }
    protected boolean canChange(int value){
        Controller c = gui.getController();
        if (centerCause(c.getX1(), c.getX2(), c.getY1(), c.getY2(), value, c.getR2())){
            return true;
        }
        else{
            showCenterWarning();
            return false;
        }
    }
}
