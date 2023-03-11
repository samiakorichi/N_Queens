package N_Queens;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {
    private JComboBox<Integer> sizeComboBox;
    private JButton submitButton;
    private JPanel chessboardPanel;

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

    sizeComboBox = new JComboBox<Integer>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});
    sizeComboBox.setSelectedIndex(0);
    sizeComboBox.setPreferredSize(new Dimension(30, 20));
    sizeComboBox.addActionListener(this); // add ActionListener to sizeComboBox
    rightPanel.add(sizeComboBox);


    //label for the image
    JLabel solutionLabel = new JLabel();
    rightPanel.add(solutionLabel);


  
    JLabel algorithmLabel = new JLabel("Choose the search algorithm:");
    rightPanel.add(algorithmLabel);

    JComboBox<String> algorithmComboBox = new JComboBox<String>(new String[] {"BFS", "DFS", "A*"});
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
    showChessboard(((Integer) sizeComboBox.getSelectedItem()).intValue());
}


public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submitButton || e.getSource() == sizeComboBox) { // check if source is submitButton or sizeComboBox
        int size = ((Integer) sizeComboBox.getSelectedItem()).intValue();

        showChessboard(size);
        
        // Check if the BFS algorithm is selected and show the solution image
        //check if bfs is selected

    
        String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();

        if(selectedAlgorithm.equals("BFS")){
            Node initial = new Node(size);
            Node result;
            BFS bfs_visitor = new BFS(initial); 
            result = bfs_visitor.runBfs();
            
            
            
            
        } else {
            if (selectedAlgorithm.equals("DFS")){



            }
        }
    }
}

//show the chessboard
private void showChessboard(int size) {
    JPanel boardPanel = new JPanel(new GridLayout(size, size));
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            JPanel square = new JPanel();
            square.setBackground((i+j)%2 == 0 ? Color.WHITE : Color.BLACK);
            boardPanel.add(square);
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