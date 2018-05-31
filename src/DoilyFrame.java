import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class DoilyFrame extends JFrame {


    public DoilyFrame() {
        super("Doily Frame");
    }

    public java.util.List<PolyLine> lines = new ArrayList<PolyLine>();
    public int sectors=12;
    public boolean show = true;
    public boolean reflect;
    public int penSize=1;
    public Color color=Color.RED;

    public void init() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GalleryPanel galleryPanel = new GalleryPanel();
        galleryPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        DisplayPanel displayPanel = new DisplayPanel(this);
        ControlPanel controlPanel = new ControlPanel(this, displayPanel, galleryPanel);
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        Container panel = this.getContentPane();
        panel.setLayout(new BorderLayout());
        panel.add(displayPanel, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.NORTH);
        panel.add(galleryPanel, BorderLayout.EAST);

        setVisible(true);
        pack();
    }

    public void setSectors(int sectors) {
        this.sectors = sectors;
    }
    public int getSectors() {
        return sectors;
    }

}
