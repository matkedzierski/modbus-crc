package modbus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.time.Duration;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Kalkulator CRC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        setMinimumSize(new Dimension(400, 0));

        BoxLayout box = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
        mainPanel.setLayout(box);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        addLabel(mainPanel, "Wejście (hex):");

        JTextField input = createInput();
        mainPanel.add(input);
        addLabel(mainPanel, "Liczba iteracji:");

        JTextField input2 = createInput();
        mainPanel.add(input2);

        JButton convertButton = addButton(mainPanel);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(2, 3, 10, 3));

        addLabel(resultPanel, "CRC (hex):");
        addLabel(resultPanel, "Czas łączny:");
        addLabel(resultPanel, "Czas iteracji:");
        JTextField output = addOutput(resultPanel);
        JTextField output2 = addOutput(resultPanel);
        JTextField output3 = addOutput(resultPanel);

        mainPanel.add(resultPanel);

        convertButton.addActionListener((event) -> {
            input.setEnabled(false);
            input2.setEnabled(false);
            convertButton.setEnabled(false);
            try{
                calculate(input.getText(), input2.getText(), output, output2, output3);                
            } catch(Exception e){
                JOptionPane.showMessageDialog(this, "Niepoprawne dane wejściowe!",
               "Błąd danych", JOptionPane.ERROR_MESSAGE);
            }

            input.setEnabled(true);
            input2.setEnabled(true);
            convertButton.setEnabled(true);
        });

        setContentPane(mainPanel);
    }

    public void calculate(String inText, String inIterations, JTextField out1, JTextField out2, JTextField out3) {
        String inputText = inText.replace(" ", "");
        int iterations = Integer.parseInt(inIterations);
        int[] inputBytes = CRC.hexStringToByteArray(inputText);
        int[] outputBytes = null;
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            outputBytes = new CRC().calculate(inputBytes);
        }
        long time = System.nanoTime() - start;
        out1.setText(CRC.bytesToHex(outputBytes));
        out2.setText(format(time));
        out3.setText(format(time / iterations));
    }

    public JRadioButton addRadioButton(Container c, String label, boolean selected) {
        JRadioButton radio1 = new JRadioButton(label, selected);
        radio1.setAlignmentX(0.5f);
        c.add(radio1);
        return radio1;
    }

    public JTextField addOutput(Container c) {
        JTextField tf = new JTextField();
        tf.setAlignmentX(0.5f);
        tf.setEditable(false);
        tf.setHorizontalAlignment(SwingConstants.CENTER);
        c.add(tf, BorderLayout.WEST);
        return tf;
    }

    public JButton addButton(Container c) {
        JButton button = new JButton("Oblicz CRC");
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);
        JPanel panel = new JPanel();
        panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        panel.add(button);
        c.add(panel);
        return button;
    }

    public JTextField createInput() {
        JTextField tf = new JTextField();
        tf.setAlignmentX(0.5f);
        tf.setHorizontalAlignment(SwingConstants.CENTER);
        return tf;
    }

    public void addLabel(Container c, String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        c.add(label);
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MainFrame frame = new MainFrame();

        // frame.setIconImage(ImageIO.read(MainFrame.class.getResource("/icon.png")));
        frame.pack();
        frame.setVisible(true);
    }

    public static String format(long nanos) {
        Duration d = Duration.ofNanos(nanos);
        long hours = d.toHours();
        d = d.minusHours(hours);
        long minutes = d.toMinutes();
        d = d.minusMinutes(minutes);
        long seconds = d.getSeconds();
        d = d.minusSeconds(seconds);
        double ms = (double)d.getNano()/1000000d;
        return (hours == 0 ? "" : hours + " h ") +
                (minutes == 0 ? "" : minutes + " m ") +
                (seconds == 0 ? "" : seconds + " s ") +
                (ms == 0 ? "" : ms + " ms ");
    }
}