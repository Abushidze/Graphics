package FIT_0204_Shelestova.Common;

import java.io.File;
import java.io.IOException;


public abstract class BaseController implements Controller {
    private Model model;
    protected int backgroundColor = (255 << 16) | (255 << 8) | 240;
    protected int drawColor = (140 << 16) | (140 << 8) | 140;

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
            bits[i] = backgroundColor;
        }
    }
}
