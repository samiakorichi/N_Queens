package N_Queens;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
    private JButton submitButton;
    private JPanel chessboardPanel;
    private JComboBox<String> algorithmComboBox;
    private JTextField sizeField;
    private JLabel executionTime;
    private JLabel nodesGeneratedLabel;
    private JLabel nodesExpended;

    public GUI() {
        super("Chessboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setResizable(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        

        // Create the chessboard panel
        JPanel chessboardFrame = new JPanel(new BorderLayout());
        chessboardFrame.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "N Queens Board"));
         
        chessboardFrame.setBackground(Color.GRAY);
        chessboardPanel = new JPanel(new GridLayout(0, 1));
        chessboardFrame.add(chessboardPanel, BorderLayout.CENTER);
        mainPanel.add(chessboardFrame, BorderLayout.CENTER);

        // Create the input and output panel
        JPanel inputOutputFrame = new JPanel(new BorderLayout());
        inputOutputFrame.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Input"));
        JPanel inputOutputPanel = new JPanel(new GridLayout(0, 1));
        inputOutputFrame.setBackground(Color.GRAY);

        JLabel sizeLabel = new JLabel("Choose the size of the chessboard:");
        inputOutputPanel.add(sizeLabel);
        sizeField = new JTextField();
        sizeField.setPreferredSize(new Dimension(10, 30));

        // accept only numbers
        sizeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // Stop the event from propagating.
                }
            }
        });

        inputOutputPanel.add(sizeField);
        // label for the image
        JLabel solutionLabel = new JLabel();
        inputOutputPanel.add(solutionLabel);

        JLabel algorithmLabel = new JLabel("Choose the search algorithm:");
        inputOutputPanel.add(algorithmLabel);

        algorithmComboBox = new JComboBox<String>(new String[] { "BFS", "DFS", "A* heuristic 1", "A* heuristic 2" });
        algorithmComboBox.setSelectedIndex(0);
        algorithmComboBox.setPreferredSize(new Dimension(30, 20));
        inputOutputPanel.add(algorithmComboBox);

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(30, 20));
        submitButton.addActionListener(this); // Listen to submit button
        inputOutputPanel.add(submitButton);

        // Create text areas
        JTextArea textArea1 = new JTextArea();
        textArea1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JTextArea textArea2 = new JTextArea();
        textArea2.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        // Create labels for the text areas


        executionTime = new JLabel();
        nodesGeneratedLabel= new JLabel();
        nodesExpended= new JLabel();


        inputOutputPanel.add(executionTime);
        inputOutputPanel.add(nodesGeneratedLabel);
        inputOutputPanel.add(nodesExpended);



        inputOutputFrame.add(inputOutputPanel, BorderLayout.CENTER);
        mainPanel.add(inputOutputFrame, BorderLayout.EAST);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            if (sizeField.getText().length() == 0) {
                JOptionPane.showMessageDialog(this,
                        "Size is empty", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                // do not execute algorith and focus size text field
                sizeField.requestFocus();
                return;
            }
            int size = Integer.parseInt(sizeField.getText());
            if (sizeField.getText().length() > 0 && size < 4) {
                JOptionPane.showMessageDialog(this,
                        "Size must be bigger than 3", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                // do not execute algorith and focus size text field
                sizeField.requestFocus();
                return;
            }

            String algorithm = algorithmComboBox.getSelectedItem().toString();
            System.out.println("Size: " + Integer.toString(size) + ", Algorithm: " + algorithm);

            // calculate execution time
            var start = System.currentTimeMillis();
            // execute the algorithm
            
         // execute the algorithm and get the result object
            var result = Algorithm.execute(size, algorithm);
            var end = System.currentTimeMillis();
            executionTime.setText("execution time: " + Long.toString(end - start) + "ms");
            // get the generated nodes count from the result object
            var nodesGenerated = result.nodesGenerated;

            // get the resulting state from the result object
            var state = result.node.getState();


            
            
          //Show number of generated nodes
            if (algorithm.equals("BFS")) {
                nodesGeneratedLabel.setText("Number of nodes generated: " + String.valueOf(result.nodesGenerated));
            }
            
            if (algorithm.equals("DFS")) {
                nodesGeneratedLabel.setText("Number of nodes generated: " + String.valueOf(result.nodesGenerated));     
            }
            
            //Show the number of expended nodes
            
            if (algorithm.equals("BFS")) {
                nodesExpended.setText("Number of nodes expended: " + String.valueOf(result.nodesExpended));
            }
            
            if (algorithm.equals("DFS")) {
                nodesExpended.setText("Number of nodes expended: " + String.valueOf(result.nodesExpended));     
            }
            
            
            showChessboard(size, state);
        }
    }

    // show the chessboard
    private void showChessboard(int size, State state) {
        JPanel boardPanel = new JPanel(new GridLayout(size, size));
        for (int i = 0; i < size; i++) {
            var position = state.getValues().get(i);
            for (int j = 0; j < size; j++) {
                var panel = new JPanel();
        
                if ((i + j) % 2 == 1) {
                    panel.setBackground(Color.BLACK);
                }
                if (j == position) {
                	
                    ImageIcon image = new ImageIcon(new ImageIcon("queeen.png")
                            .getImage().getScaledInstance(400 / size, 400 / size, Image.SCALE_DEFAULT));
                    JLabel imageLabel = new JLabel(image);
                    imageLabel.setBackground(new Color(0, 0, 0, 0)); // Set background color to transparent
                    imageLabel.setOpaque(true); // Make the label opaque
                
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
