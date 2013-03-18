package FIT_0204_Shelestova.Plotter;

import static java.lang.Math.abs;

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


        }
        public int x;
        public int y;
        public double dF;
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
        binarySearch();
    }
    private Point binarySearch(){
        Point firstPoint = null;
        firstPoint = yBinarySearch(new Point(model.getX1(), yNull), new Point(model.getX1(), 0));
        System.out.println(firstPoint.x + " : " + firstPoint.y + " == " + firstPoint.dF);
        if(firstPoint != null) return firstPoint;
        return firstPoint;
    }
    private Point xBinarySearch(Point left, Point right){
        if (left.dF == 0) return left;
        if (right.dF == 0) return right;
        int dx = (right.x - left.x)/2;
        if (dx == 0){
            if(left.dF < right.dF) return left;
            else return right;
        }
        if (abs(left.dF) > abs(right.dF)){
            return xBinarySearch(new Point(left.x + dx, left.y), right);
        }
        else {
            return xBinarySearch(right, new Point(right.x - dx, right.y));
        }

    }
    private Point yBinarySearch(Point top, Point bottom){
        System.out.println("top " + top.y + " : " + top.dF + " ::: " + "bottom " + bottom.y + " : " + bottom.dF);
        if (top.dF == 0) return top;
        if (bottom.dF == 0) return bottom;
        int dy = (top.y - bottom.y)/2;
        if (dy == 0){
            if(top.dF < bottom.dF) return top;
            else return bottom;
        }
        if (top.dF * bottom.dF < 0){
            if (abs(top.dF) > abs(bottom.dF)){
                return yBinarySearch(new Point(top.x, top.y - dy), bottom);
            }
            else {
                return yBinarySearch(top, new Point(bottom.x, bottom.y + dy));
            }
        }
        else{
            if (abs(top.dF) > abs(bottom.dF)) return bottom;
            else return top;
        }
    }
}
