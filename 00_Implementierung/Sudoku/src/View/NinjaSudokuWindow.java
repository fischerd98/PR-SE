package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.NinjaSudoku;
import Model.SudokuHistoryItem;
import Model.SudokuListItems;

public class NinjaSudokuWindow extends JFrame {

	private JPanel contentPane;
	private JPanel panel;

	private JButton[][] field;
	private JButton[] inputButtons;

	private JLabel lblState;
	private JLabel lbl_hints;

	private NinjaSudoku ninjaSudoku;

	private boolean continueGame = false;

	// ausgewähltes Feld
	private int actX = -1;
	private int actY = -1;

	private Color actColor;

	ArrayList<SudokuHistoryItem> history = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public NinjaSudokuWindow(SudokuListItems sudokuitems) {

		ninjaSudoku = new NinjaSudoku();

		if (sudokuitems != null) {
			ninjaSudoku = new NinjaSudoku(sudokuitems);
			continueGame = true;
		}

		if (!continueGame) {
			ninjaSudoku = new NinjaSudoku();
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1205, 851);

		setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Datei");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Spielstand Speichern");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ninjaSudoku.saveSudoku();
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

				emptyFields = ninjaSudoku.getEmptyFields();

				if (emptyFields.length > 0) {

					int idx = (int) (Math.random() * (emptyFields.length - 1));

					// Achtung: gedreht
					int x = emptyFields[idx][1];
					int y = emptyFields[idx][0];

					System.out.println(idx + " " + x + " " + y);

					boolean solvable = false;

					solvable = ninjaSudoku.solveSudokuNextStep(x, y);

					if (!solvable) {
						System.out.println("nicht lösbar");
						fillField();
						setBtnColors();
					} else {
						fillField();
						setBtnColors();

						lbl_hints.setText(ninjaSudoku.incrementCountHints() + "");
					}
				}
			}
		});
		mnSolver.add(mntmNchstenSchritt);

		JMenuItem mntmKomplettLsen = new JMenuItem("L\u00F6sen");
		mntmKomplettLsen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean solvable = false;

				solvable = ninjaSudoku.solveSudoku();

				if (solvable) {
					fillField();
					setBtnColors();
					lbl_hints.setText(ninjaSudoku.getCountHints() + "");

				} else {
					System.out.println("keine Lösung gefunden");
				}
			}
		});
		mnSolver.add(mntmKomplettLsen);

		JMenuItem mntmPrfen = new JMenuItem("Pr\u00FCfen");
		mntmPrfen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//checkComplete();
			}
		});
		mnSolver.add(mntmPrfen);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 799, 800);
		panel.setBackground(Color.white);
		contentPane.add(panel);
		panel.setLayout(null);

		lblState = new JLabel("Weitermachen!");
		lblState.setBounds(994, 463, 136, 14);
		contentPane.add(lblState);

		JLabel lblNewLabel = new JLabel("Erhaltene Hinweise:");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.BOLD));
		lblNewLabel.setBounds(971, 386, 136, 14);
		contentPane.add(lblNewLabel);

		lbl_hints = new JLabel("0");
		lbl_hints.setBounds(975, 413, 21, 14);
		contentPane.add(lbl_hints);

		JLabel lblNewLabel_1 = new JLabel("Zustand Spiel:");
		lblNewLabel_1.setFont(lblNewLabel_1.getFont().deriveFont(lblNewLabel_1.getFont().getStyle() | Font.BOLD));
		lblNewLabel_1.setBounds(972, 438, 95, 14);
		contentPane.add(lblNewLabel_1);

		field = new JButton[21][21];

		initField();
		initInputButtons();
		fillField();
		enableDisableInputButtons();
	}

	public void initField() {

		int initialPosX = 10;
		int initialPosY = 10;

		int posX = 10;
		int posY = initialPosY;
		int width = 36;

		for (int i = 0; i < field.length; i++) {
			for (int y = 0; y < field[i].length; y++) {

				if (!((i >= 9 && i <= 11 && (y <= 5 || y >= 15)) || (y >= 9 && y <= 11 && (i <= 5 || i >= 15)))) {
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

					field[i][y] = btn;
				}

				posX += width;
			}
			posY += width;
			posX = initialPosX;
		}

		setBtnColors();
	}

	public void setBtnColors() {

		for (int i = 0; i < field.length; i++) {
			for (int y = 0; y < field[i].length; y++) {

				int startX = i / 3;
				startX *= 3;

				int startY = y / 3;
				startY *= 3;

				if (field[i][y] != null) {

					if ((startX == 6 && startY == 6) || (startX == 12 && startY == 6) || (startX == 6 && startY == 12)
							|| (startX == 12 && startY == 12)) {
						field[i][y].setBackground(Color.yellow);
					} else if ((startX % 2 == 0 && startY % 2 != 0) || (startX % 2 != 0 && startY % 2 == 0)) {
						field[i][y].setBackground(Color.WHITE);
					} else {
						field[i][y].setBackground(Color.LIGHT_GRAY);
					}

					System.out.println(startX + " " + startY);
				}
			}
		}

	}

	public void initInputButtons() {

		int initialPosY = 850;

		int posX = initialPosY;
		int posY = 80;
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

		field[posX][posY].setBackground(Color.RED);

		for (int i : ninjaSudoku.getFieldNums(posX, posY)) {
			System.out.print(i + "  ");
		}

		System.out.println("kkkkk");

		enableDisableInputButtons();
	}

	public void setField(String val) {

		int num = Integer.parseInt(val);

		setField(actX, actY, num);
		ninjaSudoku.setField(actX, actY, num);
	}

	public void fillField() {

		int start[][];

		start = ninjaSudoku.getStart();

		for (int i = 0; i < start.length; i++) {

			for (int y = 0; y < start[i].length; y++) {

				if (field[i][y] != null) {
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
	}

	public void setField(int posX, int posY, int num) {

		if (ninjaSudoku.checkVal(posX, posY, num)) {

			SudokuHistoryItem sh = new SudokuHistoryItem(posX, posY, num, ninjaSudoku.getVal(posX, posY));
			history.add(sh);

			ninjaSudoku.setField(posX, posY, num);

			field[posX][posY].setText(num + "");
		}

	}

	public void setFieldUndo(int posX, int posY, int num) {

		ninjaSudoku.setField(posX, posY, num);

		if (num == 0) {
			field[posX][posY].setText("");
		} else {
			field[posX][posY].setText(num + "");
		}
	}
}
