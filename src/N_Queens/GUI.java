package N_Queens;
//

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {
    private JComboBox<Integer> sizeComboBox;
    private JButton submitButton;
    private JPanel chessboardPanel;
    private JComboBox<String> algorithmComboBox;

    public GUI() {
        super("Chessboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setResizable(true);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel rightPanel = new JPanel(new GridLayout(0, 1));
        mainPanel.add(rightPanel, BorderLayout.EAST);

        JLabel sizeLabel = new JLabel("Choose the size of the chessboard:");
        rightPanel.add(sizeLabel);

        sizeComboBox = new JComboBox<Integer>(new Integer[] { 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 });
        sizeComboBox.setSelectedIndex(0);
        sizeComboBox.setPreferredSize(new Dimension(30, 20));
        sizeComboBox.addActionListener(this); // add ActionListener to sizeComboBox
        rightPanel.add(sizeComboBox);

        // label for the image
        JLabel solutionLabel = new JLabel();
        rightPanel.add(solutionLabel);

        JLabel algorithmLabel = new JLabel("Choose the search algorithm:");
        rightPanel.add(algorithmLabel);

        algorithmComboBox = new JComboBox<String>(new String[] { "BFS", "DFS", "A*" });
        algorithmComboBox.setSelectedIndex(0);
        algorithmComboBox.setPreferredSize(new Dimension(30, 20));
        rightPanel.add(algorithmComboBox);

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(30, 20));
        rightPanel.add(submitButton);

        // Create text areas
        JTextArea textArea1 = new JTextArea();
        textArea1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JTextArea textArea2 = new JTextArea();
        textArea2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JTextArea textArea3 = new JTextArea();
        textArea3.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Create labels for the text areas
        JLabel label1 = new JLabel("nombre de noeuds generer");
        JLabel label2 = new JLabel("temps d'execution");
        JLabel label3 = new JLabel("Text Area 3");

        // Add the menu to the menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItem1 = new JMenuItem("Item 1");
        JMenuItem menuItem2 = new JMenuItem("Item 2");
        menu.add(menuItem1);
        menu.add(menuItem2);
        menuBar.add(menu);

        // Set the menu bar for this frame
        setJMenuBar(menuBar);

        // Add text areas and labels to the right panel
        rightPanel.add(label1);
        rightPanel.add(textArea1);
        rightPanel.add(label2);
        rightPanel.add(textArea2);
        rightPanel.add(label3);
        rightPanel.add(textArea3);

        chessboardPanel = new JPanel(new GridLayout(0, 1));
        mainPanel.add(chessboardPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton || e.getSource() == sizeComboBox || e.getSource() == algorithmComboBox) {
            int size = ((Integer) sizeComboBox.getSelectedItem()).intValue();
            String algorithm = algorithmComboBox.getSelectedItem().toString();
            showChessboard(size, algorithm);
        }
    }

    // show the chessboard
    private void showChessboard(int size, String algorithm) {
        var state = Algorithm.execute(size, algorithm).getState();
        JPanel boardPanel = new JPanel(new GridLayout(size, size));
        for (int i = 0; i < size; i++) {
            var position = state.getValues().get(i);
            for (int j = 0; j < size; j++) {
                var panel = new JPanel();
                if ((i + j) % 2 == 1) {
                    panel.setBackground(Color.BLACK);
                }
                if (j == position) {
                    ImageIcon image = new ImageIcon(new ImageIcon("queen.png")
                            .getImage().getScaledInstance(400 / size, 400 / size, Image.SCALE_DEFAULT));
                    JLabel imageLabel = new JLabel(image);
                    imageLabel.setBackground(Color.white);
                    imageLabel.setOpaque(true);
                    imageLabel.setBackground(Color.BLACK);
                    imageLabel.setVerticalAlignment(JLabel.CENTER);
                    imageLabel.setHorizontalAlignment(JLabel.CENTER);
                    boardPanel.add(imageLabel);
                } else {
                    boardPanel.add(panel);
                }
            }
        }

        chessboardPanel.removeAll();
        chessboardPanel.add(boardPanel);
        chessboardPanel.revalidate();
        chessboardPanel.repaint();
    }

    public static void main(String[] args) {
        new GUI();
    }

}
