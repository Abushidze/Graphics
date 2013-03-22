package FIT_0204_Shelestova.Plotter;

import FIT_0204_Shelestova.Common.BaseController;
import FIT_0204_Shelestova.Common.GUIStandarts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

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
        FunctionBuilder fb = new FunctionBuilder(model, bits);
        bits = fb.getBits();
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
        x -= getXnull();
        y = getYnull() - y;
        int dx1 = getX1() - x;
        int dx2 = getX2() - x;
        int dy1 = getY1() - y;
        int dy2 = getY2() - y;

        if ( abs(dx1) < 4 && abs(dy1) < 4){
            if(canSetXY(x, y, getX2(), getY2())){
            setX1(x);
            setY1(y);
            }
        }
        if( abs(dx2) < 4 && abs(dy2) < 4){
            if(canSetXY(getX1(), getY1(), x, y)) {
            setX2(x);
            setY2(y);
            }
        }
    }
    private boolean canSetXY(int x1, int y1, int x2, int y2){
        double d = pow(x2 - x1, 2) + pow(y2 - y1, 2);
        if (d <= pow(getR1(), 2)){
            return true;
        }
        else {
            return false;
        }
    }
    private boolean canSetXY(int x1, int y1, int x2, int y2, int r1, int r2){
        double d = pow(x2 - x1, 2) + pow(y2 - y1, 2);
        if (d <= pow(r1, 2)){
            return true;
        }
        else {
            return false;
        }
    }

    public void loadPropertiesFromFile(File fileName) throws IOException {
        FileInputStream in = new FileInputStream(fileName);
        Properties properties = new Properties();
        properties.loadFromXML(in);

        int x1 =(new Integer(properties.getProperty("x1")));
        int y1 =(new Integer(properties.getProperty("y1")));
        int r1 =(new Integer(properties.getProperty("r1")));

        int x2 =(new Integer(properties.getProperty("x2")));
        int y2 =(new Integer(properties.getProperty("y2")));
        int r2 =(new Integer(properties.getProperty("r2")));

        int n =(new Integer(properties.getProperty("n")));

        if (canSetXY(x1, y1, x2, y2, r1, r2)){
            setX1(x1);
            setX2(x2);
            setY1(y1);
            setY2(y2);
            setR1(r1);
            setR2(r2);
            setN(n);
        }

        try{
            setPanelWidth(new Integer(properties.getProperty("PanelWidth")));
            setPanelHeight(new Integer(properties.getProperty("PanelHeight")));
        } catch (Exception e){

        }
        in.close();
    }


    public void storePropertiesToFile(File fileName) throws IOException {
        FileOutputStream out = new FileOutputStream(fileName);
        Properties properties = new Properties();

        properties.setProperty("x1", model.getX1().toString());
        properties.setProperty("x2", model.getX2().toString());
        properties.setProperty("y1", model.getY1().toString());
        properties.setProperty("y2", model.getY2().toString());
        properties.setProperty("r1", model.getR1().toString());
        properties.setProperty("r2", model.getR2().toString());
        properties.setProperty("n", model.getN().toString());
        properties.setProperty("PanelWidth", model.getPanelWidth().toString());
        properties.setProperty("PanelHeight", model.getPanelHeight().toString());

        properties.storeToXML(out, null);
        out.close();
    }

}
