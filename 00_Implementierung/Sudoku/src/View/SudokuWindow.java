package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.SimpleSudoku;

import javax.swing.JLabel;
import javax.swing.JButton;

public class SudokuWindow extends JFrame {

	private JPanel contentPane;
	private JPanel panel;

	private JButton[][] field;
	
	private JLabel lblState;

	private SimpleSudoku ss = new SimpleSudoku();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SudokuWindow frame = new SudokuWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SudokuWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 728);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(27, 47, 504, 533);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnCheckComplete = new JButton("Pr\u00FCfen");
		btnCheckComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SudokuWindow.this.checkComplete();
				
			}
		});
		btnCheckComplete.setBounds(265, 628, 89, 23);
		contentPane.add(btnCheckComplete);
		
		lblState = new JLabel("Zustand");
		lblState.setBounds(387, 632, 130, 14);
		contentPane.add(lblState);

		field = new JButton[9][9];

//		JLabel lblNewLabel = new JLabel("9x9 Sudoku");
//		lblNewLabel.setBounds(179, 59, 98, 14);
//		contentPane.add(lblNewLabel);

		initField();
		fillField();
	}

	public void initField() {

		int posX = 77;
		int posY = 131;
		int width = 43;

		for (int i = 0; i < field.length; i++) {
			for (int y = 0; y < field[i].length; y++) {

				// SudokuButton btn = new SudokuButton(i + "", i, y);
				JButton btn = new JButton();
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EingabeWindow ew = new EingabeWindow(btn.getName(), SudokuWindow.this);
						ew.setVisible(true);
					}
				});
				btn.setName(i + ";" + y);
				btn.setBounds(posX, posY, width, width);
				btn.setVisible(true);
				panel.add(btn);
				posX += width;

				field[i][y] = btn;
			}
			posY += width;
			posX = 77;
		}
//		
//		this.revalidate();
//		int posX = 77;
//		int posY = 131;
//		int width = 43;
//
//		for (int i = 0; i < field.length; i++) {
//			for (int y = 0; y < field[i].length; y++) {
//				JButton btn = new JButton(i + "");
//				btn.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						System.out.println();
//					}
//				});
//				btn.setBounds(posX, posY, width, width);
//				contentPane.add(btn);
//				posX += width;
//				
//				field[i][y] = btn;
//			}
//			posY += width;
//			posX = 77;
//		}
	}

	public void fillField() {

		int start[][] = ss.getStart();		
		
		for (int i = 0; i < start.length; i++) {
			
			for (int y = 0; y < start[i].length; y++) {
				
				int val = start[i][y];
				
				if(val != 0) {
					field[i][y].setEnabled(false);
				}
				field[i][y].setText(val + "");
			}
		}
	}
	
	public void setField(int posX, int posY, int num) {
		
		if(ss.checkVal(posX, posY, num)) {
			ss.setField(posX, posY, num);
			field[posX][posY].setText(num + "");
		}
	}
	
	public void checkComplete() {
		boolean state = ss.checkComplete();
		
		if(state)
			lblState.setText("Fertig!");
		else
			lblState.setText("Weitermachen!");
	}
}
