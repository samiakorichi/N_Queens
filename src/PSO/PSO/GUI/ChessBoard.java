package PSO.PSO.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import PSO.PSO.PSO_Sol.Particle;

public class ChessBoard extends JPanel {


	private static final long serialVersionUID = 1L;

	public int ChessBoardLength = 8;
	private ArrayList<Particle> solutions;
	private int numOfSolutions;
	private int temp = 0;

	public ChessBoard() {

	}

	public void setChessBoardLength(int chessBoardLength) {
		ChessBoardLength = chessBoardLength;
	}

	public void setSolutions(ArrayList<Particle> solutions) {
		this.solutions = solutions;
		this.numOfSolutions = solutions.size();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int length = ChessBoardLength;

		g.fillRect(10, 10, length * 50, length * 50);
		for (int i = 10; i <= length * 50; i += 100) {

			for (int j = 10; j <= length * 50; j += 100) {
				g.clearRect(i, j, 50, 50);
			}

		}

		for (int i = 60; i <= length * 50 + 50; i += 100) {
			for (int j = 60; j <= length * 50 + 50; j += 100) {
				g.clearRect(i, j, 50, 50);
			}
		}

		for (int x = 0; x < length; x++) {
			if (temp < numOfSolutions) {
				int y = solutions.get(temp).getData(x);
				g.setColor(new Color(110, 60, 23));
				g.fillOval(20 + 50 * x, 20 + 50 * y, 30, 30);

			}

		}
		temp++;

	}
}
