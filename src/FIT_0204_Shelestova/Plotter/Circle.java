package FIT_0204_Shelestova.Plotter;

public class Circle {
    private int[] circleBits;
    private int r;
    public Circle(int r){
        this.r = r;
        circleBits = fillCircleBits();
    }
    private int[] fillCircleBits(){            //TODO: move method from controller and change logic there
        return null;
    }

    public int[] getCircleBits() {
        return circleBits;
    }
    private int getD(){
        return 2*r + 1;
    }
}
