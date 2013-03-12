package FIT_0204_Shelestova.Plotter;

import FIT_0204_Shelestova.Common.Model;

import static java.lang.StrictMath.pow;

public class PlotterModel extends Model{
    private Integer x1 = 0 ;
    private Integer y1 = 0;
    private Integer r1 = 10;

    private Integer x2 = 0;
    private Integer y2 = 0;
    private Integer r2 = 5;

    private Integer n = 0;


    public Integer getX1() {
        return x1;
    }

    public void setX1(Integer x1) {
        this.x1 = x1;
    }

    public Integer getY1() {
        return y1;
    }

    public void setY1(Integer y1) {
        this.y1 = y1;
    }

    public Integer getR1() {
        return r1;
    }

    public void setR1(Integer r1) {
        this.r1 = r1;
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2(Integer x2) {
        this.x2 = x2;
    }

    public Integer getY2() {
        return y2;
    }

    public void setY2(Integer y2) {
        this.y2 = y2;
    }

    public Integer getR2() {
        return r2;
    }

    public void setR2(Integer r2) {
        this.r2 = r2;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }
    public Integer funcF(int x, int y){
        return (x*x + y*y - r1*r1);
    }
    public Integer funcQ(int x, int y){
        return (x*x + y*y - r2*r2);
    }

    public Integer funcR(int x, int y){
        int q = funcQ(x, y);
        if (q < 0) return 0;
        int result = (int)pow(q, n);
        return result;
    }
    public Integer goalFunction(int x, int y){
        return funcF(x,y) + funcR(x, y);
    }
}
