package FIT_0204_Shelestova.Common;

public abstract class Model {
    private Integer panelWidth = 0;
    private Integer panelHeight = 0;

    public Integer getPanelWidth() {
        return panelWidth;
    }

    public void setPanelWidth(Integer panelWidth) {
        this.panelWidth = panelWidth;
    }

    public Integer getPanelHeight() {
        return panelHeight;
    }

    public void setPanelHeight(Integer panelHeight) {
        this.panelHeight = panelHeight;
    }

    public int getXnull(){
        return panelWidth/2;
    }

    public int getYnull(){
        return panelHeight/2;
    }
}
