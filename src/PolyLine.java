import java.awt.*;
import java.util.ArrayList;

class PolyLine {
    private java.util.List<Integer> xList;  // List of x-coord
    private java.util.List<Integer> yList;  // List of y-coord
    DisplayPanel displayPanel;
    int penSize;
    boolean reflect;
    Color color;

    // Constructor
    public PolyLine(DisplayPanel displayPanel,int penSize, boolean reflect, Color color) {
        this.displayPanel = displayPanel;
        this.penSize = penSize;
        this.reflect = reflect;
        this.color = color;
        xList = new ArrayList<Integer>();
        yList = new ArrayList<Integer>();
    }

    // Add a point to this PolyLine
    public void addPoint(int x, int y) {
        xList.add(x);
        yList.add(y);
    }

    // This PolyLine paints itself given the Graphics context
    public void draw(Graphics g) {
        ((Graphics2D) g).setStroke(new BasicStroke(penSize));
        ((Graphics2D) g).setColor(color);
        for (int i = 0; i < xList.size() - 1; ++i) {
            g.drawLine(xList.get(i), yList.get(i), xList.get(i + 1), yList.get(i + 1));
        }
    }
    // This reflection of the PolyLine paints itself given the Graphics context
    public void drawReflection(Graphics g) {
        if(reflect) {
            ((Graphics2D) g).setStroke(new BasicStroke(penSize));
            ((Graphics2D) g).setColor(color);
            for (int i = 0; i < xList.size() - 1; ++i) {
                g.drawLine(xList.get(i), displayPanel.getHeight() - yList.get(i), xList.get(i + 1), displayPanel.getHeight() - yList.get(i + 1));
            }
        }
    }

}