package FIT_0204_Shelestova.Common;

import java.io.File;
import java.io.IOException;


public abstract class BaseController implements Controller {
    private Model model;

    protected void setModel(Model model){
        this.model = model;
    }

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
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setYposition(Integer newYposition) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setSize(Integer newSize) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void loadPropertiesFromFile(File fileName) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void storePropertiesToFile(File fileName) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void fillMemory(int[] memory) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    protected void prepareCanvas(int[] bits){
        for(int i = 0; i < bits.length; i++){
            bits[i] = GUIStandarts.backgroundColor;
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

}
