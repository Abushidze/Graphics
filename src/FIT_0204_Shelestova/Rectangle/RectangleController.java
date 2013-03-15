package FIT_0204_Shelestova.Rectangle;

import FIT_0204_Shelestova.Common.Controller;

import java.io.*;
import java.util.Properties;

public class RectangleController implements Controller {
    RectangleModel model = new RectangleModel();
    @Override
    public void setPanelWidth(Integer newPanelWidth) {
        model.setPanelWidth(newPanelWidth);
    }

    @Override
    public void setPanelHeight(Integer newPanelHeight) {
        model.setPanelHeight(newPanelHeight);
    }

    @Override
    public int getPanelWidth() {
        return model.getPanelWidth();
    }

    @Override
    public int getPanelHeight() {
        return model.getPanelHeight();
    }

    @Override
    public void setXposition(Integer newXposition) {
        model.setX(newXposition);
    }

    @Override
    public void setYposition(Integer newYposition) {
        model.setY(newYposition);
    }

    @Override
    public void setSize(Integer newSize) {
        model.setSize(newSize);
    }


    @Override
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

    @Override
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

    @Override
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

    @Override
    public Integer getX1() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setX1(Integer x1) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Integer getY1() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setY1(Integer y1) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Integer getR1() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setR1(Integer r1) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Integer getX2() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setX2(Integer x2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Integer getY2() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setY2(Integer y2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Integer getR2() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setR2(Integer r2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Integer getN() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setN(Integer n) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void moveFigure(int x, int y) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean canChangeX2(int x2) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean canChangeY2(int y2) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean canChangeX1(int x1) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean canChangeY1(int y1) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
