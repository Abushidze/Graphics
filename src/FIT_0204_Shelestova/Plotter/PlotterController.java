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
        //TODO: подумать о корректности модели
        prepareCanvas(bits);
        drawAxis(bits);
        drawCircle(bits, getX1(),getY1(), getR1());
        drawCircle(bits, getX2(), getY2(), getR2());
    }

    private void drawCircle(int[] bits, int x, int y, int r) {
        int d = 2*r + 1;
        int pw = getPanelWidth();
        int ph = getPanelHeight();

        int[] circle = createCircle(r);

        //TODO: подумать о функции, отражающей окружность в массив,
        // т.к. нам необходимо еще и центры отбражать в окружности. для захвата.
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
        drawCenter(circle, r);
        return circle;
    }
    private void drawCenter(int[] circle, int r){
        int d = 2*r + 1;
        circle[(r*d) + r] = drawColor;
    }
    private void plot8(int[] circle, int x, int y, int r){
        int d = 2*r + 1;
        paintXY(circle, r - x, r - y, d);
        paintXY(circle, r - x, r + y, d);
        paintXY(circle, r + x, r - y, d);
        paintXY(circle, r + x, r + y, d);

        paintXY(circle, r - y, r - x, d);
        paintXY(circle, r + y, r - x, d);
        paintXY(circle, r - y , r + x, d);
        paintXY(circle, r + y, r + x, d);
    }
    private void paintXY(int[] bits, int x, int y, int bitsXdim){
        bits[y * bitsXdim + x] = drawColor;
    }

    private void drawAxis(int[] bits){
        int pw = getPanelWidth();
        int ph = getPanelHeight();

        int xNull = getXnull();
        int yNull = getYnull();

        for (int i = 0; i < ph; i++){
            bits[i*pw + xNull] = axisColor;
        }

        for (int i = 0; i < pw; i++){
            bits[yNull*pw + i] = axisColor;
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

}
