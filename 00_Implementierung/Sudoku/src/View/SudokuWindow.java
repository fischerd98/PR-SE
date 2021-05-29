package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.SimpleSudoku;
import Model.SudokuHistoryItem;
import Model.SudokuListItems;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class SudokuWindow extends JFrame {

	private JPanel contentPane;
	private JPanel panel;

	private JButton[][] field;
	private JButton[] inputButtons;

	private JLabel lblState;

	private SimpleSudoku ss;

	private boolean continueGame = false;
	
	// ausgewähltes Feld
	private int actX = -1;
	private int actY = -1;

	private Color actColor;
	
	ArrayList<SudokuHistoryItem> history = new ArrayList<>();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SudokuWindow frame = new SudokuWindow(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//	public SudokuWindow(SudokuListItems sudokuitem) {
//		ss = new SimpleSudoku(sudokuitem);
//		continueGame = true;
//		
//		SudokuWindow();
//	}

	/**
	 * Create the frame.
	 */
	public SudokuWindow(SudokuListItems sudokuitems) {

		if (sudokuitems == null) {
			ss = new SimpleSudoku();
		} else {
			ss = new SimpleSudoku(sudokuitems);
			continueGame = true;
		}

//		if(!continueGame) {
//			ss = new SimpleSudoku();
//		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1025, 728);

		setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Men\u00FC");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Spielstand Speichern");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ss.saveSudoku();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem2 = new JMenuItem("Zurück zum Hauptmenü");
		mntmNewMenuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		mnNewMenu.add(mntmNewMenuItem2);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 504, 533);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnCheckComplete = new JButton("Pr\u00FCfen");
		btnCheckComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SudokuWindow.this.checkComplete();

			}
		});
		btnCheckComplete.setBounds(265, 604, 89, 23);
		contentPane.add(btnCheckComplete);

		lblState = new JLabel("Zustand");
		lblState.setBounds(385, 608, 130, 14);
		contentPane.add(lblState);
		
		JButton btn_deleteChoice = new JButton("Auwahl l\u00F6schen");
		btn_deleteChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actX = -1;
				actY = -1;
				setBtnColors();
				enableDisableInputButtons();
			}
		});
		btn_deleteChoice.setBounds(265, 638, 165, 23);
		contentPane.add(btn_deleteChoice);
		
		JButton btn_undo = new JButton("R\u00FCckg\u00E4ngig");
		btn_undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(history.size() >= 1) {
					SudokuHistoryItem sh = history.remove(history.size()-1);
					setFieldUndo(sh.getX(), sh.getY(), sh.getvBefore());
				}	
			}
		});
		btn_undo.setBounds(265, 573, 89, 23);
		contentPane.add(btn_undo);

		field = new JButton[9][9];

//		JLabel lblNewLabel = new JLabel("9x9 Sudoku");
//		lblNewLabel.setBounds(179, 59, 98, 14);
//		contentPane.add(lblNewLabel);

		initField();
		initInputButtons();
		fillField();
		enableDisableInputButtons();
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

						sudokuButtonClicked(btn.getName());

						// EingabeWindow ew = new EingabeWindow(btn.getName(), SudokuWindow.this);
						// ew.setVisible(true);
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

		setBtnColors();
	}

	public void setBtnColors() {

		for (int i = 0; i < field.length; i++) {
			for (int y = 0; y < field[i].length; y++) {

				int startX = i / 3;
				startX *= 3;

				int startY = y / 3;
				startY *= 3;

				if ((startX % 2 == 0 && startY % 2 != 0) || (startX % 2 != 0 && startY % 2 == 0)) {
					field[i][y].setBackground(Color.WHITE);
				} else {
					field[i][y].setBackground(Color.LIGHT_GRAY);
				}

			}
		}
	}

	public void initInputButtons() {

		int posX = 600;
		int posY = 131;
		int width = 43;

		int count = 1;

		inputButtons = new JButton[9];
		
		for (int i = 0; i < 3; i++) {
			for (int y = 0; y < 3; y++) {

				// SudokuButton btn = new SudokuButton(i + "", i, y);
				JButton btn = new JButton();
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						setField(btn.getText());

					}
				});
				btn.setText(count + "");
				btn.setBounds(posX, posY, width, width);
				btn.setVisible(true);
				contentPane.add(btn);
				posX += width;
				
				inputButtons[count - 1] = btn;

				count++;
			}

			posY += width;
			posX = 600;
		}
	}
	
	public void enableDisableInputButtons() {
		
		boolean activate = false;
		
		if(actX >= 0 && actY >= 0) {
			activate = true;
		}
		
		for(JButton inb : inputButtons) {
			inb.setEnabled(activate);
		}
	}

	public void sudokuButtonClicked(String name) {
		
		//int num = Integer.parseInt(val);

		String[] arr = name.split(";");

		int posX = Integer.parseInt(arr[0]);
		int posY = Integer.parseInt(arr[1]);

		setBtnColors();
		
		actX = posX;
		actY = posY;
		
		field[posX][posY].setBackground(Color.RED);
		
		enableDisableInputButtons();
	}

	public void setField(String val) {

		int num = Integer.parseInt(val);

		setField(actX, actY, num);
		
		//this.initField();
		
//		String[] arr = this.pos.split(";");
//
//		int posX = Integer.parseInt(arr[0]);
//		int posY = Integer.parseInt(arr[1]);
//
//		if (sw != null) {
//			sw.setField(posX, posY, num);
//		} else if (csw != null) {
//			csw.setField(posX, posY, num);
//		}
//
//		this.dispose();
	}

	public void fillField() {

		int start[][] = ss.getStart();

		for (int i = 0; i < start.length; i++) {

			for (int y = 0; y < start[i].length; y++) {

				int val = start[i][y];

				if (val != 0) {
					field[i][y].setEnabled(false);
					field[i][y].setText(val + "");
				} else {
					field[i][y].setText("");
				}
			}
		}
	}

	public void setField(int posX, int posY, int num) {
		
		if (ss.checkVal(posX, posY, num)) {
			
			SudokuHistoryItem sh = new SudokuHistoryItem(posX, posY, num, ss.getVal(posX, posY));
			history.add(sh);
			
			ss.setField(posX, posY, num);
			field[posX][posY].setText(num + "");
		}
	}
	
	public void setFieldUndo(int posX, int posY, int num) {
		
		//this.reset
		
		//if (ss.checkVal(posX, posY, num)) {
			ss.setField(posX, posY, num);
			
			if(num == 0) {
				field[posX][posY].setText("");
			} else {
				field[posX][posY].setText(num + "");
			}
			
		//}
	}

	public void checkComplete() {
		boolean state = ss.checkComplete();

		if (state)
			lblState.setText("Fertig!");
		else
			lblState.setText("Weitermachen!");
	}
}
