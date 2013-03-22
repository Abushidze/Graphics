package FIT_0204_Shelestova.Plotter;

import FIT_0204_Shelestova.Common.GUIStandarts;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class FunctionBuilder {
    private int xNull;
    private int yNull;

    public int[] getBits() {
        doIt();
        return bits;
    }

    private class Point {
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            f();
            pw = model.getPanelWidth();

        }

        private int x;
        private int y;
        public double dF;
        public int pw;
        public void setX(int x) {
            this.x = x;
            f();
        }

        public void setY(int y) {
            this.y = y;
            f();
        }

        public void draw() {
            if (y > -yNull && y < yNull && x < xNull && x > -xNull) {
                bits[(yNull -y) * pw + x + xNull] = GUIStandarts.figureColor;
            }
        }


        public void f() {
            this.dF = model.func(x, y);
        }
    }

    private static PlotterModel model;
    private int[] bits;

    public static void setModel(PlotterModel model) {
        FunctionBuilder.model = model;
    }

    FunctionBuilder(PlotterModel model, int[] bits) {
        setModel(model);
        xNull = model.getXnull();
        yNull = model.getYnull();
        this.bits = bits;

    }

    private void doIt() {
        Point firstPoint = null;
        if (model.getY1() > - yNull && model.getY1() < yNull){
            firstPoint = xSearchFirstPoint(new Point(-xNull, model.getY1()));
        }
        else if (model.getX1() > -xNull && model.getX1() < xNull){
            firstPoint = ySearchFirstPoint(new Point(model.getX1(), -yNull));
        }
        else if(model.getPanelWidth() > model.getPanelHeight()){
            if (model.getY1() < -yNull){
                firstPoint = xSearchFirstPoint(new Point(-xNull, -yNull));
            }
            else{
                firstPoint = xSearchFirstPoint(new Point(-xNull, yNull));
            }
        }
        else if (model.getPanelHeight() > model.getPanelWidth()){
            if (model.getX1() < -yNull){
                firstPoint = ySearchFirstPoint(new Point(-xNull, -yNull));
            }
            else{
                firstPoint = ySearchFirstPoint(new Point(xNull, -yNull));
            }
        }
        drawByBresenchem(firstPoint);
    }

    private Point xSearchFirstPoint(Point startPoint) {
        //Point startPoint = new Point(-xNull, model.getY1());
        Point currPoint = new Point(startPoint.x, startPoint.y);

        while (startPoint.dF * currPoint.dF > 0) {
            if (currPoint.x <= xNull) {
                currPoint.setX(currPoint.x + 1);
            } else {
                break;
            }
        }
        Point preCurrPoint = new Point(currPoint.x - 1, currPoint.y);

        if (abs(preCurrPoint.dF) < abs(currPoint.dF)) {
            return preCurrPoint;
        } else {
            return currPoint;
        }
    }
    private Point ySearchFirstPoint(Point startPoint) {
        Point currPoint = new Point(startPoint.x, startPoint.y);

        while (startPoint.dF * currPoint.dF > 0) {
            if (currPoint.y <= yNull) {
                currPoint.setY(currPoint.y + 1);
            } else {
                break;
            }
        }
        Point preCurrPoint = new Point(currPoint.x , currPoint.y - 1);

        if (abs(preCurrPoint.dF) < abs(currPoint.dF)) {
            return preCurrPoint;
        } else {
            return currPoint;
        }
    }

    private void drawByBresenchem(Point startPoint) {
        List<Point> drawedPoints = new ArrayList<Point>();
        startPoint.draw();
        drawedPoints.add(startPoint);
        Point firstPoint = startPoint;
        Point prePoint = startPoint;

        Point[] points = new Point[8];
        Point bigPoint = new Point(model.getX1(), model.getY1()); // здесь гарантированно большое абсолютное значение
        Point nextPoint;

        do {
            nextPoint = bigPoint;
            points[0] = new Point(prePoint.x - 1, prePoint.y - 1);
            points[1] = new Point(prePoint.x - 1, prePoint.y);
            points[2] = new Point(prePoint.x - 1, prePoint.y + 1);
            points[3] = new Point(prePoint.x, prePoint.y - 1);
            points[4] = new Point(prePoint.x, prePoint.y + 1);
            points[5] = new Point(prePoint.x + 1, prePoint.y + 1);
            points[6] = new Point(prePoint.x + 1, prePoint.y - 1);
            points[7] = new Point(prePoint.x + 1, prePoint.y);
            for (int i = 0; i < 8; i++) {
                if (!isDrawed(drawedPoints, points[i])) {
                    if (abs(points[i].dF) < abs(nextPoint.dF)) {
                        nextPoint = points[i];
                    }
                } else if (drawedPoints.size() > 2 && (points[i].x == firstPoint.x && points[i].y == firstPoint.y)) {
                    if (abs(points[i].dF) < abs(nextPoint.dF)) {
                        nextPoint = points[i];
                    }
                }
            }

            nextPoint.draw();
            drawedPoints.add(nextPoint);
            prePoint = nextPoint;
        }
        while (!(nextPoint.x == firstPoint.x && nextPoint.y == firstPoint.y));
    }
    private boolean isDrawed(List<Point> points, Point p){
        for (Point point : points){
            if (p.x == point.x && p.y == point.y){
                return true;
            }
        }
        return false;
    }
}
