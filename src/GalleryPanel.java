import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class GalleryPanel extends JPanel {

    Image img;
    int imgCount=0;

    public GalleryPanel() {

        super();
        setPreferredSize(new Dimension(200, 1200));
        setLayout(new GridLayout(12, 1));

    }

    JPanel panel1= new JPanel();
    JPanel panel2= new JPanel();
    JPanel panel3= new JPanel();
    JPanel panel4= new JPanel();
    JPanel panel5= new JPanel();
    JPanel panel6= new JPanel();

    JScrollPane scrollPane = new JScrollPane();

    //adds images to the gallery
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        try {
            img = ImageIO.read(new File("image"+ imgCount + ".png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (img != null) {
            imgCount++;
            ImageIcon imageIcon = new ImageIcon(img);
            JLabel label = new JLabel(imageIcon);
            panel1.add(label);
            Image scaleImage = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            g.drawImage(scaleImage, 0, 0, null);
        }
    }
}
