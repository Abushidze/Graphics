package FIT_0204_Shelestova.Plotter;

import FIT_0204_Shelestova.Common.BaseController;

public class PlotterController extends BaseController{
    private PlotterModel model = new PlotterModel();

    public PlotterController(){
        super.setModel(model);
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
        drawCircle(bits, getX1(),getY1(), getR1());
        drawCircle(bits, getX2(), getY2(), getR2());
    }

    private void drawCircle(int[] bits, int x, int y, int r) {
        int d = 2*r + 1;
        int pw = getPanelWidth();
        int ph = getPanelHeight();

        int[] circle = createCircle(r);

        int xStart = getXnull() + x - r;
        int yStart = getYnull() - y - r;

        int xStop = xStart + d;
        int yStop = yStart + d;

        int dx = 0;
        int dy = 0;

        for(int i = 0; i < d; i++){
            if (i + xStart > xStop || i + xStart > pw) break; //TODO: убрать такой бред
            if (xStart < 0) continue;
            for(int j = 0; j < d; j++ ){
                if (j + yStart > yStop || yStop > ph - 1) break;           //TODO: убрать такой бред
                if (yStart < 0) continue;

                if( circle[j*d + i] == drawColor){
                    bits[(i + xStart) + (j+ yStart)*pw] = circle[j*d + i];
                }
            }
        }
    }

    private int[] createCircle(int r){
        int d = 2*r + 1;
        int[] circle = new int[d*d];
        prepareCanvas(circle);

        int x = -r;
        int y = 0;

        int F = 1 - r;
        int delta_Fs = 3;
        int delta_Fd = 5 - 2*r;
        while( x + y < 0){

            plot8(circle, x, y, r);
            if(F > 0){  // diag
                F += delta_Fd;
                x++; y++;
                delta_Fs += 2;
                delta_Fd += 4;
            }
            else { // vert
                F += delta_Fs;
                y++;
                delta_Fs += 2;
                delta_Fd += 2;
            }
        }
        return circle;
    }
    private void plot8(int[] bits, int x, int y, int r){
        paintXY(bits, r - x, r - y, r);
        paintXY(bits, r - x, r + y, r);
        paintXY(bits, r + x, r - y, r);
        paintXY(bits, r + x, r + y, r);

        paintXY(bits, r - y, r - x, r);
        paintXY(bits, r + y, r - x, r);
        paintXY(bits, r - y , r + x, r);
        paintXY(bits, r + y, r + x, r);
    }
    private void paintXY(int[] bits, int x, int y, int r){
        bits[y * (2*r + 1) + x] = drawColor;
    }


}
