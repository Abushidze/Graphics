package FIT_0204_Shelestova.Common;


import java.io.File;
import java.io.FileNotFoundException;
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
}
