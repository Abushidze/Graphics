package FIT_0204_Shelestova.Plotter;

import FIT_0204_Shelestova.Common.GUIStandarts;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.nextAfter;

public class FunctionBuilder {
    private int xNull;
    private int yNull;

    public int[] getBits() {
        doIt();
        return bits;
    }

    private class Point{
        public Point(int x, int y){
            this.x = x;
            this.y = y;
            dF = model.func(x, y);
            pw = model.getPanelWidth();

        }

        private int x;
        private int y;
        public double dF;
        public int pw;

        public void setX(int x) {
            this.x = x;
            dF = model.func(x,y);
        }

        public void setY(int y) {
            this.y = y;
            dF = model.func(x,y);
        }
        public void draw(){
            bits[(y+yNull) * pw + x + xNull] = GUIStandarts.figureColor;
        }
        public boolean isPainted (){
            if (bits[(y+yNull) * pw + x + xNull] == GUIStandarts.figureColor)
                return true;
            else
                return false;
        }
    }
    private static PlotterModel model;
    private int[] bits;

    public static void setModel(PlotterModel model) {
        FunctionBuilder.model = model;
    }

    FunctionBuilder(PlotterModel model, int[] bits){
        setModel(model);
        xNull = model.getXnull();
        yNull = model.getYnull();
        this.bits = bits;
        Point p = new Point(300, 0);
    }
    private void doIt(){
        Point firstPoint = xSearchFirstPoint();
        drawByBresenchem(firstPoint);
        //System.out.println(firstPoint.x + " : " + firstPoint.y + " = " + firstPoint.dF);
    }

    private Point xSearchFirstPoint(){
        Point startPoint = new Point(-xNull, model.getY1());
        Point currPoint = new Point(-xNull, model.getY1());

        while (startPoint.dF * currPoint.dF > 0){
            if(currPoint.x <= xNull){
                currPoint.setX(currPoint.x + 1);
            }
            else {
                break;
            }
        }
        Point preCurrPoint = new Point(currPoint.x - 1, currPoint.y);

        if (abs(preCurrPoint.dF) < abs(currPoint.dF)){
            return preCurrPoint;
        }
        else {
            return currPoint;
        }
    }

    private void drawByBresenchem(Point startPoint){
        Point firstPoint = startPoint;
        int j = 50;
        while(j > 0){
            startPoint.draw();
            Point[] points = new Point[8];
            points[0] = new Point(startPoint.x - 1, startPoint.y - 1);
            points[1] = new Point(startPoint.x - 1, startPoint.y);
            points[2] = new Point(startPoint.x - 1, startPoint.y + 1);
            points[3] = new Point(startPoint.x, startPoint.y -1);
            points[4]= new Point(startPoint.x, startPoint.y + 1);
            points[5] = new Point(startPoint.x + 1, startPoint.y + 1);
            points[6] = new Point(startPoint.x + 1, startPoint.y - 1);
            points[7]= new Point(startPoint.x + 1, startPoint.y);
            Point next = points[0];
            for (int i = 1; i < 8; i++){
                if (points[i].isPainted() ){
                    continue;
                }
                else if( points[i].dF < next.dF){
                    next = points[i];
                }
            }
            startPoint = next;
            j--;
        }

    }

}
