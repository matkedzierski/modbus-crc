package modbus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame()
    {
        setTitle("Kalkulator CRC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
 
        JPanel mainPanel = new JPanel();
        setMinimumSize(new Dimension(400, 0));
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        mainPanel.setLayout(gridbag);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        constraints.gridwidth = 1;

        constraints.gridx = 0;
        constraints.gridy = 0;
        addLabel(mainPanel, "Wejście (hex):", gridbag, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        JTextField input = createInput();
        mainPanel.add(input, constraints);
        constraints.gridwidth = 1;

        constraints.gridx = 0;
        constraints.gridy = 1;
        addLabel(mainPanel, "Liczba iteracji:", gridbag, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        JTextField input2 = createInput();
        mainPanel.add(input2, constraints);
        constraints.gridwidth = 1;

        // constraints.gridx = 2;
        // constraints.gridy = 2;
        // constraints.gridwidth = 1;
        // JButton convertButton = createButton();
        // mainPanel.add(convertButton, constraints);

        // constraints.gridx = 0;
        // constraints.gridy = 3;
        // constraints.gridwidth = 1;
        // addLabel(mainPanel, "Wyjście (bin):", gridbag, constraints);

        // constraints.gridx = 1;
        // constraints.gridy = 3;
        // constraints.gridwidth = 8;
        // JTextField output = addOutput(mainPanel, constraints);
    
        // convertButton.addActionListener((event)->{
        //     String b = input.getText();
        //     String out;
        // });
        
        
        setContentPane(mainPanel);
    }

    public JRadioButton addRadioButton(Container c, String label, boolean selected){
        JRadioButton radio1 = new JRadioButton(label, selected);
        radio1.setAlignmentX(0.5f);
        c.add(radio1);
        return radio1;
    }

    public JTextField addOutput(Container c, GridBagConstraints con){
        JTextField tf = new JTextField();
        tf.setAlignmentX(0.5f);
        tf.setEditable(false);
        tf.setHorizontalAlignment(SwingConstants.CENTER);
        c.add(tf, con);
        return tf;
    }

    public JButton createButton(){
        JButton button = new JButton("Oblicz CRC");
        button.setAlignmentX(0.5f);
        return button;
    }
    public JTextField createInput(){
        JTextField tf = new JTextField();
        tf.setAlignmentX(0.5f);
        tf.setHorizontalAlignment(SwingConstants.CENTER);
        return tf;
    }
    public void addLabel(Container c, String text, GridBagLayout bag, GridBagConstraints con){
        JLabel label = new JLabel(text);
        c.add(label, con);
        
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MainFrame frame = new MainFrame();
        
        frame.setIconImage(ImageIO.read(MainFrame.class.getResource("/icon.png")));    
        frame.pack();
        frame.setVisible(true);
    }
}