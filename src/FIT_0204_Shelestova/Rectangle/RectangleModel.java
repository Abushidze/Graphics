package FIT_0204_Shelestova.Rectangle;


public class RectangleModel {
    private int panelWidth = 0;
    private int panelHeight = 0;

    private int x = 0;
    private int y = 0;
    private int size = 20;

    public Integer getPanelWidth() {
        return panelWidth;
    }

    public void setPanelWidth(int panelWidth) {
        this.panelWidth = panelWidth;
    }

    public Integer getPanelHeight() {
        return panelHeight;
    }

    public void setPanelHeight(int panelHeight) {
        this.panelHeight = panelHeight;
    }

    public Integer getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getXnull(){
        return panelWidth/2;
    }

    public int getYnull(){
        return panelHeight/2;
    }
}
