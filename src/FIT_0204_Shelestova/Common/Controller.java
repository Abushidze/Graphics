package FIT_0204_Shelestova.Common;


import java.io.File;
import java.io.IOException;

public interface Controller {
    public void setPanelWidth(Integer newPanelWidth);
    public void setPanelHeight(Integer newPanelHeight);
    public int getPanelWidth();
    public int getPanelHeight();
    public void setXposition(Integer newXposition);
    public void setYposition(Integer newYposition);
    public void setSize(Integer newSize);
    public void loadPropertiesFromFile(File fileName) throws IOException;
    public void storePropertiesToFile(File fileName) throws IOException;
    public void fillMemory(int[] memory);
    public Integer getXnull();
    public Integer getYnull();
    public Integer getX1();

    public void setX1(Integer x1);

    public Integer getY1();

    public void setY1(Integer y1);
    public Integer getR1();

    public void setR1(Integer r1);

    public Integer getX2();

    public void setX2(Integer x2);

    public Integer getY2();

    public void setY2(Integer y2);

    public Integer getR2();


    public void setR2(Integer r2);

    public Integer getN();

    public void setN(Integer n);
    public void setK(Integer k);

    public void moveFigure(int x, int y);
    public boolean canSetXY(int x1, int y1, int x2, int y2, int r1, int r2);

}
