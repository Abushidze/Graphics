package FIT_0204_Shelestova.Plotter;

import FIT_0204_Shelestova.Common.BaseController;

public class PlotterController extends BaseController{
    private PlotterModel model = new PlotterModel();

    public PlotterController(){
        super.setModel(model);
    }

    public void fillMemory(int[] bits ){
        prepareCanvas(bits);
        int[] circle = createCircle(model.getR1());

        int xDimSize = model.getPanelWidth();
        int circleXStart = 0;
        int circleYStart = 0;
        int circleXStop = 2 * model.getR1() + 1;
        int circleYStop = 2 * model.getR1() + 1;

        int xStart = model.getXnull() + model.getX1() - model.getR1();
        int yStart = model.getYnull() - model.getY1() + model.getR1();

        int	xStop = xStart + model.getR1() * 2 + 1;
        int yStop = yStart - model.getR1() * 2 - 1;

        if (xStart < - model.getR1() * 2){
            xStart = 0;
            xStop = 0;
        }

        if (yStart > model.getPanelHeight() - 1){
            yStart = model.getPanelHeight() - 1;

        }

        if (xStop > model.getPanelWidth()){
            xStop = model.getPanelWidth();
        }

        if (yStop < 0){
            yStop = 0;
        }
        for (int i = xStart, ci = 0; i < xStop; i++, ci++){
            for(int j = yStart, cj = 2*model.getR1(); j > yStop; j--, cj--){
                bits[j* xDimSize + i] = circle[model.getR1()*2 * cj + ci];
            }
        }

    }

    private int[] createCircle(int r){
        int d = 2*r +1;
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
        bits[y * 2*r + x] = drawColor;
    }


}
