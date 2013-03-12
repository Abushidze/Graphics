package FIT_0204_Shelestova.Rectangle;


import FIT_0204_Shelestova.Common.ControlGroup;
import FIT_0204_Shelestova.Common.Controller;

public class RectangleGUIFirst extends RectangleGUI {
    private ControlGroup sizeControlGroup;
    private ControlGroup xControlGroup;
    private ControlGroup yControlGroup;
    RectangleGUIFirst(Controller controller){
        super(controller);
        setTitle("Rectangle");
    }
}
