import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


class DisplayPanel extends JPanel {

    DoilyFrame frame;
    private PolyLine currentLine;  // the current line (for capturing)

    //Constructor
    public DisplayPanel(DoilyFrame frame) {

        super();
        setPreferredSize(new Dimension(1000, 1000));
        this.setBackground(Color.BLACK);
        this.frame=frame;

        addMouseListener(new MouseAdapter() {
            @Override
            // Start a new line
            public void mousePressed(MouseEvent e) {
                currentLine = new PolyLine(DisplayPanel.this,frame.penSize,frame.reflect,frame.color);
                frame.lines.add(currentLine);
                currentLine.addPoint(e.getX(), e.getY());
                currentLine.addPoint(e.getX(), e.getY());
            }
            @Override
            // Makes a point when mouse is clicked
            public void mouseClicked(MouseEvent e) {
                currentLine = new PolyLine(DisplayPanel.this,frame.penSize,frame.reflect,frame.color);
                frame.lines.add(currentLine);
                currentLine.addPoint(e.getX(), e.getY());
                repaint(); // invoke paintComponent()

            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            //While the mouse is dragged, ads mouse coordinates to the line
            public void mouseDragged(MouseEvent evt) {
                currentLine.addPoint(evt.getX(),evt.getY());
                repaint();  // invoke paintComponent()
            }
        });
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);

        /*if the reflection button is selected, draws the lines and their reflection in every sector
          otherwise, draws the lines in every sector
         */
        if(frame.reflect){
            for (PolyLine line : frame.lines) {
                for (int i = 0; i < frame.getSectors(); i++) {

                    ((Graphics2D) g).rotate(2 * Math.PI / frame.getSectors(), this.getWidth() / 2, this.getHeight() / 2);
                    line.draw(g2d);
                    line.drawReflection(g2d);
                }
            }
        }

        else {
            for (PolyLine line : frame.lines) {
                for (int i = 0; i < frame.getSectors(); i++) {

                    ((Graphics2D) g).rotate(2 * Math.PI / frame.getSectors(), this.getWidth() / 2, this.getHeight() / 2);
                    line.draw(g2d);
                }
            }
        }

        g2d.translate(this.getWidth() / 2, this.getHeight() / 2);
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(1));

        //draw sectors by rotating
        if(frame.show == true) {
            for (int i = 0; i < frame.getSectors(); i++) {
                ((Graphics2D) g).rotate(2 * Math.PI / frame.getSectors(), 0, 0);
                g2d.drawLine(0, 0, this.getWidth()/2, 0);
            }
        }
    }
}

