package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.FreiformSudoku;
import Model.SimpleSudoku;
import Model.SudokuHistoryItem;
import Model.SudokuListItems;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Insets;

public class SudokuWindow extends JFrame {

	private JPanel contentPane;
	private JPanel panel;

	private JButton[][] field;
	private JButton[] inputButtons;

	private JLabel lblState;
	private JLabel lbl_hints;

	private SimpleSudoku ss;
	private FreiformSudoku ff;

	private boolean ssGame = false;
	private boolean ffGame = false;

	private boolean continueGame = false;

	private Color[] c;

	// ausgewähltes Feld
	private int actX = -1;
	private int actY = -1;

	private Color actColor;

	ArrayList<SudokuHistoryItem> history = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public SudokuWindow(SudokuListItems sudokuitems, boolean isSs, boolean isFf) {

		setColors();

		if (sudokuitems == null || (isSs && isFf) || (!isSs && !isFf)) {
			ss = new SimpleSudoku();
		} else {
			if (isSs && !isFf) {
				ss = new SimpleSudoku(sudokuitems);
				ssGame = true;
				ffGame = false;
			} else {
				ff = new FreiformSudoku(sudokuitems);
				ffGame = true;
				ssGame = false;
			}

			continueGame = true;
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 514, 531);

		setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Datei");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Spielstand Speichern");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ss != null) {
					ss.saveSudoku();
				} else {
					ff.saveSudoku();
				}
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

		JMenu mnBearbeiten = new JMenu("Bearbeiten");
		menuBar.add(mnBearbeiten);

		JMenuItem mntmRckgngig = new JMenuItem("R\u00FCckg\u00E4ngig");
		mntmRckgngig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (history.size() >= 1) {
					SudokuHistoryItem sh = history.remove(history.size() - 1);
					setFieldUndo(sh.getX(), sh.getY(), sh.getvBefore());
				}
			}
		});
		mnBearbeiten.add(mntmRckgngig);

		JMenuItem mntmAuswahlLschen = new JMenuItem("Auswahl l\u00F6schen");
		mntmAuswahlLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actX = -1;
				actY = -1;
				setBtnColors();
				enableDisableInputButtons();
				setBtnColors();
			}
		});
		mnBearbeiten.add(mntmAuswahlLschen);

		JMenu mnSolver = new JMenu("Solver");
		menuBar.add(mnSolver);

		JMenuItem mntmNchstenSchritt = new JMenuItem("Hinweis");
		mntmNchstenSchritt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int[][] emptyFields = null;

				if (ss != null) {
					emptyFields = ss.getEmptyFields();
				} else {
					emptyFields = ff.getEmptyFields();
				}

				if (emptyFields.length > 0) {

					int idx = (int) (Math.random() * (emptyFields.length - 1));

					// Achtung: gedreht
					int x = emptyFields[idx][1];
					int y = emptyFields[idx][0];

					boolean solvable = false;

					if (ss != null) {
						solvable = ss.solveSudokuNextStep(x, y);
					} else {
						solvable = ff.solveSudokuNextStep(x, y);
					}

					if (!solvable) {
						fillField();
						setBtnColors();
					} else {
						fillField();
						setBtnColors();

						if (ss != null) {
							lbl_hints.setText(ss.incrementCountHints() + "");
						} else {
							lbl_hints.setText(ff.incrementCountHints() + "");
						}
					}
				}
			}
		});
		mnSolver.add(mntmNchstenSchritt);

		JMenuItem mntmKomplettLsen = new JMenuItem("L\u00F6sen");
		mntmKomplettLsen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean solvable = false;

				if (ss != null) {
					solvable = ss.solveSudoku();
				} else {
					solvable = ff.solveSudoku();
				}

				if (solvable) {
					fillField();
					setBtnColors();
					if (ss != null) {
						lbl_hints.setText(ss.getCountHints() + "");
					} else {
						lbl_hints.setText(ff.getCountHints() + "");
					}

				} else {
					System.out.println("keine Lösung gefunden");
				}
			}
		});
		mnSolver.add(mntmKomplettLsen);

		JMenuItem mntmPrfen = new JMenuItem("Pr\u00FCfen");
		mntmPrfen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SudokuWindow.this.checkComplete();
			}
		});
		mnSolver.add(mntmPrfen);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 55, 400, 400);
		panel.setBackground(Color.white);
		contentPane.add(panel);
		panel.setLayout(null);

		lblState = new JLabel("0");
		lblState.setBounds(286, 30, 50, 14);
		contentPane.add(lblState);

		lbl_hints = new JLabel("0");
		lbl_hints.setBounds(172, 30, 21, 14);
		contentPane.add(lbl_hints);

		JLabel lblNewLabel_1 = new JLabel("Zeit:");
		lblNewLabel_1.setFont(lblNewLabel_1.getFont().deriveFont(lblNewLabel_1.getFont().getStyle() | Font.BOLD));
		lblNewLabel_1.setBounds(239, 30, 63, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Erhaltene Hinweise:");
		lblNewLabel.setBounds(44, 30, 119, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.BOLD));

		field = new JButton[9][9];

		initField();
		initInputButtons();
		fillField();
		enableDisableInputButtons();
	}

	public void setColors() {
		c = new Color[9];

		c[0] = Color.RED;
		c[1] = Color.BLUE;
		c[2] = Color.YELLOW;
		c[3] = Color.GRAY;
		c[4] = Color.CYAN;
		c[5] = Color.GREEN;
		c[6] = Color.PINK;
		c[7] = Color.MAGENTA;
		c[8] = Color.ORANGE;

	}

	public void initField() {

		int initialPosX = 10;
		int initialPosY = 10;

		int posX = 10;
		int posY = initialPosY;
		int width = 43;

		for (int i = 0; i < field.length; i++) {
			for (int y = 0; y < field[i].length; y++) {
				JButton btn = new JButton();
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						sudokuButtonClicked(btn.getName());
					}
				});
				btn.setName(i + ";" + y);
				btn.setBounds(posX, posY, width, width);
				btn.setMargin(new Insets(0, 0, 0, 0));
				btn.setFont(new Font("Arial", Font.BOLD, 24));
				btn.setForeground(Color.BLACK);
				btn.setVisible(true);
				panel.add(btn);
				posX += width;

				field[i][y] = btn;
			}
			posY += width;
			posX = initialPosX;
		}

		setBtnColors();
	}

	public void setBtnColors() {

		if (this.ssGame) {
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
					
					field[i][y].setForeground(Color.BLACK);
				}
			}
		} else if (this.ffGame) {
			for (int i = 0; i < field.length; i++) {
				for (int y = 0; y < field[i].length; y++) {

					Color col = c[ff.getFieldConstruction()[i][y] - 1];

					field[i][y].setBackground(col);
					field[i][y].setForeground(Color.BLACK);

				}
			}
		}
	}

	public void initInputButtons() {

		int initialPosY = 430;

		int posX = initialPosY;
		int posY = 65;
		int width = 43;

		int count = 1;

		inputButtons = new JButton[9];

		for (int i = 0; i < 9; i++) {

			JButton btn = new JButton();
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					setField(btn.getText());

				}
			});
			btn.setText(count + "");
			btn.setBounds(posX, posY, width, width);
			btn.setMargin(new Insets(0, 0, 0, 0));
			btn.setFont(new Font("Arial", Font.BOLD, 24));
			btn.setForeground(Color.BLACK);
			btn.setVisible(true);
			contentPane.add(btn);
			posX += width;

			inputButtons[count - 1] = btn;

			count++;

			posY += width;
			posX = initialPosY;

		}
	}

	public void enableDisableInputButtons() {

		boolean activate = false;

		if (actX >= 0 && actY >= 0) {
			activate = true;
		}

		for (JButton inb : inputButtons) {
			inb.setEnabled(activate);
		}
	}

	public void sudokuButtonClicked(String name) {

		// int num = Integer.parseInt(val);

		String[] arr = name.split(";");

		int posX = Integer.parseInt(arr[0]);
		int posY = Integer.parseInt(arr[1]);

		setBtnColors();

		actX = posX;
		actY = posY;

		if (ssGame && !ffGame) {
			field[posX][posY].setBackground(Color.RED);
		} else {
			field[posX][posY].setBackground(Color.WHITE);
		}

		enableDisableInputButtons();
	}

	public void setField(String val) {

		int num = Integer.parseInt(val);

		setField(actX, actY, num);
	}

	public void fillField() {

		int start[][];

		if (ssGame) {
			start = ss.getStart();
		} else {
			start = ff.getStart();
		}

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

		if (ss != null) {
			if (ss.checkVal(posX, posY, num)) {

				SudokuHistoryItem sh = new SudokuHistoryItem(posX, posY, num, ss.getVal(posX, posY));
				history.add(sh);

				ss.setField(posX, posY, num);
				field[posX][posY].setText(num + "");
			}
		} else {
			if (ff.checkVal(posX, posY, num)) {

				SudokuHistoryItem sh = new SudokuHistoryItem(posX, posY, num, ff.getVal(posX, posY));
				history.add(sh);

				ff.setField(posX, posY, num);
				field[posX][posY].setText(num + "");
			}
		}

	}

	public void setFieldUndo(int posX, int posY, int num) {

		// this.reset

		// if (ss.checkVal(posX, posY, num)) {

		if (ss != null) {
			ss.setField(posX, posY, num);
		} else {
			ff.setField(posX, posY, num);
		}

		if (num == 0) {
			field[posX][posY].setText("");
		} else {
			field[posX][posY].setText(num + "");
		}

		// }
	}

	public void checkComplete() {

		boolean state = false;

		if (ss != null) {
			state = ss.checkComplete();
		} else {
			// state = ff.checkComplete();
		}

		if (state)
			lblState.setText("Fertig!");
		else
			lblState.setText("Weitermachen!");
	}
}
