package FIT_0204_Shelestova.Plotter;

import FIT_0204_Shelestova.Common.BaseController;
import FIT_0204_Shelestova.Common.GUIStandarts;

public class PlotterController extends BaseController{
    private PlotterModel model = new PlotterModel();
    private Circle circle1;
    private Circle circle2;

    public PlotterController(){
        super.setModel(model);
        circle1 = new Circle(model.getR1());
        circle2 = new Circle(model.getR2());
    }

    public Integer getX1() {
        return model.getX1();
    }

    public void setX1(Integer x1) {
        model.setX1(x1);
    }

    public Integer getY1() {
        return model.getY1();
    }

    public void setY1(Integer y1) {
        model.setY1(y1);
    }

    public Integer getR1() {
        return model.getR1();
    }

    public void setR1(Integer r1) {
        circle1.setR(r1);
        model.setR1(r1);
    }

    public Integer getX2() {
        return model.getX2();
    }

    public void setX2(Integer x2) {
        model.setX2(x2);
    }

    public Integer getY2() {
        return model.getY2();
    }

    public void setY2(Integer y2) {
        model.setY2(y2);
    }

    public Integer getR2() {
        return model.getR2();
    }

    public void setR2(Integer r2) {
        circle2.setR(r2);
        model.setR2(r2);
    }

    public Integer getN() {
        return model.getN();
    }

    public void setN(Integer n) {
        model.setN(n);
    }

    public int getXnull(){
        return model.getXnull();
    }
    public int getYnull(){
        return model.getYnull();
    }
    public void fillMemory(int[] bits ){
        prepareCanvas(bits);
        drawAxis(bits);
        drawCircle(bits, circle1.getCircleBits(), getX1(),getY1(), getR1());
        drawCircle(bits, circle2.getCircleBits(), getX2(), getY2(), getR2());
        drawFunction(bits);
    }

    private void drawFunction(int[] bits){
        // seek first poit
        Point.setxNull(getXnull());
        Point.setyNull(getYnull());
        Point.setModel(model);


    }
    private  Point xBinarySearch(Point left, Point right){

    }

    private void drawCircle(int[] bits, int[] circle, int x, int y, int r) {
        int d = 2*r + 1;
        int pw = getPanelWidth();
        int ph = getPanelHeight();

        int xStart = getXnull() + x - r, xCircleStart = 0;
        int yStart = getYnull() - y - r, yCircleStart = 0;

        int xStop = xStart + d, xCircleStop = d;
        int yStop = yStart + d, yCircleStop = d;

        //  boundary cases:
        int dx = 0;
        int dy = 0;

        if( xStart < 0 ){
            dx = -xStart;
            xCircleStart += dx;
        }
        if (yStart < 0 ){
            dy = -yStart;
            yCircleStart += dy;
        }
        if (xStop > pw){
            dx = xStop - pw;
            xCircleStop -= dx;
        }
        if (yStop > ph){
            dy = yStop - ph;
            yCircleStop -= dy;
        }

        for(int i = xCircleStart; i < xCircleStop; i++){
            for(int j = yCircleStart; j < yCircleStop; j++ ){
                if( circle[j*d + i] == GUIStandarts.drawColor){
                    bits[(i + xStart) + (j+ yStart)*pw] = circle[j*d + i];
                }
            }
        }
    }

    private void drawAxis(int[] bits){
        int pw = getPanelWidth();
        int ph = getPanelHeight();

        int xNull = getXnull();
        int yNull = getYnull();

        for (int i = 0; i < ph; i++){
            bits[i*pw + xNull] = GUIStandarts.axisColor;
        }

        for (int i = 0; i < pw; i++){
            bits[yNull*pw + i] = GUIStandarts.axisColor;
        }
    }

    @Override
    public void moveFigure(int x, int y) {
        //TODO: сделать захват не 1 писксель, а 3. сейчас захватить невозможно.
        x -= getXnull();
        y = getYnull() - y;

        if ( x == getX1() && y == getY1()){
            setX1(x);
            setY1(y);
        }
        if( x == getX2() && y == getY2()){
            setX2(x);
            setY2(y);
        }
    }

    @Override
    public boolean canChangeX2(int x2) {
        if (getX1() + getR1() > x2 || getX1() - getR1() < x2){
            return false;
        }
        return true;
    }

    @Override
    public boolean canChangeY2(int y2) {
        if (getY1() + getR1() > y2 || getY1() - getY2() < y2){
            return false;
        }
        return true;
    }

    @Override
    public boolean canChangeX1(int x1) {
        if (getX2() + getR2() < x1 || getX2() - getR2() > x1){
            return false;
        }
        return true;
    }

    @Override
    public boolean canChangeY1(int y1) {
        if (getY2() + getR2() < y1 || getY2() - getR2() < y1){
            return false;
        }
        return true;
    }

}
