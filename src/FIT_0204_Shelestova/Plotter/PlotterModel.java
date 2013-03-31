package FIT_0204_Shelestova.Plotter;

import FIT_0204_Shelestova.Common.Model;

import static java.lang.Math.pow;

public class PlotterModel extends Model{
    private Integer x1 = 0 ;
    private Integer y1 = 0;
    private Integer r1 = 10;

    private Integer x2 = 0;
    private Integer y2 = 0;
    private Integer r2 = 5;

    private Integer n = 2;
    private Integer k = 100;

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

    public double funcF(int x, int y){
        return (pow(r1,2) -pow(x - x1,2) - pow(y - y1,2)) /1000000.;
    }
    public double funcQ(int x, int y){
        return  (pow(r2,2) - pow((x - x2),2) - pow(y - y2,2))/1000000. ;
    }
    public double funcR(int x, int y){
        double  q = k * funcQ(x, y);
        if (q >= 0){
            return pow(q, n);
        }
        else {
            return 0;
        }
    }
    public double func(int x, int y){
        return funcF(x, y) + funcR(x, y);
    }

    public void setK(Integer k) {
        this.k = k;
    }
}
