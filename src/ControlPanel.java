import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {

    Color defColor = Color.RED;
    int imgCount = 0;

    public ControlPanel(DoilyFrame frame, DisplayPanel displayPanel, GalleryPanel galleryPanel) {

        super();
        setPreferredSize(new Dimension(1000, 150));
        JPanel check = new JPanel();
        check.setLayout(new GridLayout(2, 1));
        JPanel sliders = new JPanel();
        sliders.setLayout(new GridLayout(4, 1));
        JButton btnColor = new JButton("Change Color");
        this.add(btnColor);

        //Attaches a listener to the color changer button
        btnColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                Color color = JColorChooser.showDialog(ControlPanel.this,
                        "Choose a color", defColor);
                if (color != null) { // new color selected
                    frame.color = color;
                }
            }
        });

        //Clears all the lines
        JButton clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.lines.clear();
                frame.repaint();
            }
        });

        //Saves the images
        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BufferedImage img = new BufferedImage(displayPanel.getWidth(), displayPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g = img.createGraphics();
                displayPanel.print(g);

                try {
                    ImageIO.write(img, "png", new File("image" + imgCount + ".png"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                frame.repaint();

                if (imgCount == 12)
                    imgCount = 1;
                else
                    imgCount++;
            }
        });

        //if button is selected, show sector lines
        JCheckBox toggleSec = new JCheckBox("Toggle Sector Lines");
        toggleSec.setSelected(true);
        toggleSec.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    frame.show = true;
                else
                    frame.show = false;
                frame.repaint();
            }
        });


        //If button is selected, reflect drawn points
        JCheckBox points = new JCheckBox("Reflect Drawn Points");
        points.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    frame.reflect = true;
                else
                    frame.reflect = false;
                frame.repaint();
            }
        });


        //Check that arraylist is not empty and remove only the last line
        JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!frame.lines.isEmpty()) {
                    frame.lines.remove(frame.lines.size() - 1);
                    frame.repaint();
                }
            }
        });

        //set the size of the pen to the size received from the slider
        JSlider penSize = new JSlider(0, 50, 1);
        penSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                frame.penSize = penSize.getValue();
                frame.repaint();
            }
        });


        //set the numbers of sectors to the value received from the slider
        JSlider sectorNr = new JSlider(1, 60, 12);
        sectorNr.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                frame.setSectors(sectorNr.getValue());
                frame.repaint();
            }
        });

        check.add(toggleSec);
        check.add(points);

        JLabel secTitle = new JLabel("Number of sectors");
        JLabel penTitle = new JLabel("Pen size");
        sliders.add(penTitle);
        sliders.add(penSize);
        sliders.add(secTitle);
        sliders.add(sectorNr);

        this.add(clear);
        this.add(btnColor);
        this.add(sliders);
        this.add(check);
        this.add(undo);
        this.add(save);

        setLayout(new FlowLayout());
    }
}
