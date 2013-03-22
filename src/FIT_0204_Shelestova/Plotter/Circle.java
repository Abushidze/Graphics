package FIT_0204_Shelestova.Plotter;

import FIT_0204_Shelestova.Common.GUIStandarts;

public class Circle {
    private int[] circleBits;
    private int r;

    public void setR(int r){
        this.r = r;
        circleBits = fillCircleBits();
    }
    public Circle(int r){
        this.r = r;
        circleBits = fillCircleBits();
    }
    public int[] getCircleBits(){
        return circleBits;
    }

    private int[] fillCircleBits() {
        int d = getD();
        int[] circleBits = new int[d*d];
        prepareBits(circleBits);

        int x = -r;
        int y = 0;

        int F = 1 - r;
        int delta_Fs = 3;
        int delta_Fd = 5 - 2*r;
        while( x + y < 0){

            plot8(circleBits, x, y, r);
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
        drawCenter(circleBits, r);
        return circleBits;
    }

    private void prepareBits(int[] circleBits) {
        for(int i = 0; i < circleBits.length; i++){
            circleBits[i] = GUIStandarts.backgroundColor;
        }
    }

    private void drawCenter(int[] circle, int r){ //TODO: change
        int d = getD();
        for (int i = -3; i < 4; i++ ){
            circle[((r+i)*d) + r] = GUIStandarts.drawColor;
            circle[(r*d) + r + i] = GUIStandarts.drawColor;
        }

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
    private void paintXY(int[] bits, int x, int y, int d){
        bits[y * d + x] = GUIStandarts.drawColor;
    }
    public int getD(){
        return 2*r + 1;
    }
    public int getR(){
        return r;
    }

}
