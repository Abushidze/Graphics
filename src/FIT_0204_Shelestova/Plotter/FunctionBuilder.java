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
                bits[(yNull - y) * pw + x + xNull] = GUIStandarts.figureColor;
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
        Point firstPoint = xBinarySearch(new Point(-1000, model.getY1()), new Point(model.getX1(), model.getY1())) ;
        drawByBresenchem(firstPoint);
    }

    private Point xSearchFirstPoint(Point startPoint) {

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

    private Point xBinarySearch(Point left, Point right) {
        int y = left.y; // the same for right and left
        Point mid;
        while (true) {
            if (abs(left.x - right.x) == 1) {
                if (abs(left.dF) <= abs(right.dF)) {
                    return left;
                } else {
                    return right;
                }
            }

            mid = new Point((right.x + left.x) / 2, y);
            if (mid.dF == 0 || mid.dF == left.dF || mid.dF == right.dF) return mid;

            //System.out.println((mid.dF + " : " + left.dF + " :: " + right.dF));
            //System.out.println("left.x : " + left.x + " right x : " + right.x + " x : " + mid.x);
            if (mid.dF * left.dF < 0) {
                right = mid;
            }
            if (mid.dF * right.dF < 0) {
                left = mid;
            }

        }

    }

    private Point yBinarySearch(Point bottom, Point top) {
        int x = bottom.x; // the same for right and left
        Point mid;
        while (true) {
            if (abs(bottom.y - top.y) == 1) {
                if (abs(bottom.dF) <= abs(top.dF)) {
                    return bottom;
                } else {
                    return top;
                }
            }

            mid = new Point(x,  (top.y + bottom.y) / 2);
            if (mid.dF == 0 || mid.dF == bottom.dF || mid.dF == top.dF) return mid;

            //System.out.println((mid.dF + " : " + left.dF + " :: " + right.dF));
            //System.out.println("left.x : " + left.x + " right x : " + right.x + " x : " + mid.x);
            if (mid.dF * bottom.dF < 0) {
                bottom = mid;
            }
            if (mid.dF * top.dF < 0) {
                top = mid;
            }

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
        Point preCurrPoint = new Point(currPoint.x, currPoint.y - 1);

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
        Point bigPoint = new Point(model.getX1(), model.getY1());
        bigPoint.dF = Double.MAX_VALUE;// здесь гарантированно большое абсолютное значение
        Point nextPoint = bigPoint;
        Point preprePoint = null;
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
                /*if (!isDrawed(drawedPoints, points[i])) {
                    if (abs(points[i].dF) < abs(nextPoint.dF) &&
                            scalar(preprePoint, prePoint, points[i]) >= 0) {
                        nextPoint = points[i];
                    }
                } else if (drawedPoints.size() > 2 && (points[i].x == firstPoint.x && points[i].y == firstPoint.y)) {
                    if (abs(points[i].dF) < abs(nextPoint.dF)) {
                        nextPoint = points[i];
                    }
                }*/
                if (abs(points[i].dF) < abs(nextPoint.dF)){
                    if (preprePoint == null){
                        nextPoint = points[i];
                    }
                    else if (scalar(preprePoint, prePoint, points[i]) >= 0){
                        nextPoint = points[i];
                    }
                }

            }
            if (nextPoint.equals(bigPoint)) break;
            nextPoint.draw();
            //drawedPoints.add(nextPoint);
            preprePoint = new Point(prePoint.x, prePoint.y);
            prePoint = nextPoint;
        }
        while (!(nextPoint.x == firstPoint.x && nextPoint.y == firstPoint.y));
    }

    private boolean isDrawed(List<Point> points, Point p) {
        for (Point point : points) {
            if (p.x == point.x && p.y == point.y) {
                return true;
            }
        }
        return false;
    }

    private int scalar(Point p0, Point p1, Point p2) {
        return (p1.x - p0.x) * (p2.x - p1.x) + (p1.y - p0.y) * (p2.y - p1.y);
    }
}
