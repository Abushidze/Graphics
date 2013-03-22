package FIT_0204_Shelestova.Rectangle;

import FIT_0204_Shelestova.Common.BaseController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class RectangleController extends BaseController{
    RectangleModel model = new RectangleModel();

    public void setPanelWidth(Integer newPanelWidth) {
        model.setPanelWidth(newPanelWidth);
    }


    public void setPanelHeight(Integer newPanelHeight) {
        model.setPanelHeight(newPanelHeight);
    }


    public int getPanelWidth() {
        return model.getPanelWidth();
    }


    public int getPanelHeight() {
        return model.getPanelHeight();
    }


    public void setXposition(Integer newXposition) {
        model.setX(newXposition);
    }


    public void setYposition(Integer newYposition) {
        model.setY(newYposition);
    }


    public void setSize(Integer newSize) {
        model.setSize(newSize);
    }



    public void loadPropertiesFromFile(File fileName) throws IOException {
        FileInputStream in = new FileInputStream(fileName);
        Properties properties = new Properties();
        properties.loadFromXML(in);

        setSize(new Integer(properties.getProperty("Size")));
        setXposition(new Integer(properties.getProperty("PosX")));
        setYposition(new Integer(properties.getProperty("PosY")));

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

        properties.setProperty("Size", model.getSize().toString());
        properties.setProperty("PosX", model.getX().toString());
        properties.setProperty("PosY", model.getY().toString());
        properties.setProperty("PanelWidth", model.getPanelWidth().toString());
        properties.setProperty("PanelHeight", model.getPanelHeight().toString());

        properties.storeToXML(out, null);
        out.close();
    }


    public void fillMemory(int[] memory) {
        int xDimSize = model.getPanelWidth();

        int xStart = model.getXnull() + model.getX() - model.getSize()/2;
        int yStart = model.getYnull() - model.getY() + model.getSize()/2;

        int	xStop = xStart + model.getSize();
        int yStop = yStart - model.getSize();

        if (xStart < - model.getSize()){
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

        int r = 255;
        int g = 255;
        int b = 240;
        int color = (r << 16) | (g << 8) | b;


        for(int i = 0; i < memory.length; i++){
            memory[i] = color;
        }

        r = 140;
        g = 140;
        b = 140;
        color = (r << 16) | (g << 8) | b;

        for (int i = xStart; i < xStop; i++){
            if (i < 0 ) i = 0;
            for(int j = yStart; j > yStop; j--){
                memory[j* xDimSize + i] = color;
            }
        }
    }


}
