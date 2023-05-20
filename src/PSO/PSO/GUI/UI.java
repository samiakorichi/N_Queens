package PSO.PSO.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import PSO.PSO.PSO_Sol.Options;
import PSO.PSO.PSO_Sol.ParticleSwarmOptimization;
import PSO.PSO.PSO_Sol.TesterPSO;

public class UI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Thread runThread = null;

	// Timer related
	private Timer timer;
	private int refreshTime = 1500; // refresh timer

	private JFrame Frame = null;
	private JPanel centralPanel = new JPanel();
	private JPanel sliderPanel = new JPanel();
	private JSlider ChessBoardLength = new JSlider(4, 20, 4);
	private JLabel _ChessBoardLength = new JLabel("ChessBoardLength : 4");
	private JSlider maxVelocity = new JSlider(4, 20 ,4);
	private JLabel _maxVelocity = new JLabel("maxVelocity : 4");
	private JSlider maxIterationTime = new JSlider(1000, 50000, 5000);
	private JLabel _maxIterationTime = new JLabel("maxIterationTime : 5000");

	JButton startButton = new JButton("start");
	JButton stopButton = new JButton("stop");

	private int width = 1100;
	private int height = 800;

	private ChessBoard chessboard;
	private Options options;
	private ParticleSwarmOptimization PSO;

	public UI(Options options) {

		this.options = options;

		sliderPanel.setLayout(new GridLayout(4, 2));
		sliderPanel.add(ChessBoardLength);
		sliderPanel.add(_ChessBoardLength);
		sliderPanel.add(maxVelocity);
		sliderPanel.add(_maxVelocity);
		sliderPanel.add(maxIterationTime);
		sliderPanel.add(_maxIterationTime);

		sliderPanel.add(startButton);
		sliderPanel.add(stopButton);


		sliderPanel.setBackground(Color.GRAY);

		// ---SLIDER LISTENERS---//
		SliderListener listener = new SliderListener();
		ChessBoardLength.addChangeListener(listener);
		maxVelocity.addChangeListener(listener);
		maxIterationTime.addChangeListener(listener);

		// ---BUTTON LISTENER---//

		startButton.addActionListener(this);
		stopButton.addActionListener(this);

		// ---SET INITIAL VALUES---//
		this.options.chessBoardLength = 4;
		this.options.maxVelocity = 4;
		this.options.maxIterationTime = 5000;

		// ---CREATE PANEL---//
		Border thickBorder = new LineBorder(Color.black, 1);
		centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
		centralPanel.setBorder(thickBorder);
		centralPanel.add(sliderPanel);

		// ---ADD CHESSBOARD---//
		
		chessboard = new ChessBoard();
		
		
		Frame = new JFrame();

		Frame.setAlwaysOnTop(isAlwaysOnTop());
		Frame.getContentPane().setLayout(new BorderLayout());
		Frame.add(centralPanel, BorderLayout.NORTH);
		Frame.add(chessboard, BorderLayout.CENTER);
		Frame.setTitle("N-Queen_PSO");
		Frame.setBackground(Color.WHITE);
		Frame.setSize(width, height);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setVisible(true);
		Frame.setResizable(true);
		Frame.setLocationRelativeTo(null);
		Frame.setLocation(100, 10);

	}

	private class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent ce) {
			if (ce.getSource() == ChessBoardLength) {
				int val = ChessBoardLength.getValue();
				_ChessBoardLength.setText("ChessBoardLength : " + val);
				options.chessBoardLength = val;
			} else if (ce.getSource() == maxVelocity) {
				int val = maxVelocity.getValue();
				_maxVelocity.setText("maxVelocity : " + val);
				options.maxVelocity = val;
			} else if (ce.getSource() == maxIterationTime) {
				_maxIterationTime.setText("maxIterationTime : " + maxIterationTime.getValue());
				options.maxIterationTime = maxIterationTime.getValue();
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Runnable runnable = new Runnable() {
			public void run() {
				timer = new Timer(refreshTime, new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						chessboard.repaint();
					}
				});

				timer.start();
			}
		};

		if (e.getActionCommand().equals("start")) {

			
			this.PSO = new ParticleSwarmOptimization(this.options);
			TesterPSO tester = new TesterPSO(this.PSO);
			chessboard.setChessBoardLength(PSO.getChessBoardLength());
			chessboard.setSolutions(tester.test());
			
			runThread = new Thread(runnable);
			runThread.start();

		}

		if (e.getActionCommand().equals("stop")) {

			timer.stop();
			runThread.interrupt();

		}

	}

}
