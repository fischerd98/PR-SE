package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.FreiformSudoku;
import Model.SimpleSudoku;
import Model.SudokuHistoryItem;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class CreateSudokuWin extends JFrame {

	private JPanel contentPane;
	private JPanel panel;

	private JButton[][] field;
	private JButton[] inputButtons;

	private SimpleSudoku ss;
	private FreiformSudoku ff;

	private boolean ssGame = false;
	private boolean ffGame = false;

	private int selectedLevel = 0;

	private boolean continueGame = false;

	private Color[] c;

	// ausgewähltes Feld
	private int actX = -1;
	private int actY = -1;

	private Color actColor;

	ArrayList<SudokuHistoryItem> history = new ArrayList<>();
	private JTextField textField_Name;

	/**
	 * Create the frame.
	 */
	public CreateSudokuWin(boolean isSs, boolean isFf) {

		setColors();

		if (isSs && !isFf) {
			ss = new SimpleSudoku();
			ssGame = true;
			ffGame = false;
		} else {
			ff = new FreiformSudoku();
			ffGame = true;
			ssGame = false;
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 514, 683);

		setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Datei");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Spielvorlage Speichern");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = textField_Name.getText();
				String schwierigkeit = "new";

				if (!name.equals("")) {
					if (selectedLevel == 0) {
						schwierigkeit = "Anfaenger";
					} else if (selectedLevel == 1) {
						schwierigkeit = "Fortgeschritten";
					} else if (selectedLevel == 2) {
						schwierigkeit = "Profi";
					}

					if (ss != null) {

						String link = "Sudokus\\SimpleSudoku\\" + schwierigkeit + "\\" + name + ".csv";

						ss.saveNewSudoku(link);
					} else {
						ff.saveSudoku();
					}
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

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 170, 400, 400);
		panel.setBackground(Color.white);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Spielname:");
		lblNewLabel.setBounds(44, 30, 119, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.BOLD));

		textField_Name = new JTextField();
		textField_Name.setBounds(204, 27, 156, 20);
		contentPane.add(textField_Name);
		textField_Name.setColumns(10);

		JLabel lblSchwierigkeitsgrad = new JLabel("Schwierigkeitsgrad:");
		lblSchwierigkeitsgrad.setFont(
				lblSchwierigkeitsgrad.getFont().deriveFont(lblSchwierigkeitsgrad.getFont().getStyle() | Font.BOLD));
		lblSchwierigkeitsgrad.setBounds(44, 69, 119, 14);
		contentPane.add(lblSchwierigkeitsgrad);

		ButtonGroup bg = new ButtonGroup();

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Einfach");
		rdbtnNewRadioButton.setBounds(204, 65, 156, 23);
		bg.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Mittel");
		rdbtnNewRadioButton_1.setBounds(204, 91, 156, 23);
		bg.add(rdbtnNewRadioButton_1);
		contentPane.add(rdbtnNewRadioButton_1);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Schwer");
		rdbtnNewRadioButton_2.setBounds(204, 117, 156, 23);
		bg.add(rdbtnNewRadioButton_2);
		contentPane.add(rdbtnNewRadioButton_2);

		ActionListener rdbtnActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				JRadioButton rb = (JRadioButton) actionEvent.getSource();

				switch (rb.getText()) {
				case "Einfach":
					selectedLevel = 0;
					break;
				case "Mittel":
					selectedLevel = 1;
					break;
				case "Schwer":
					selectedLevel = 2;
					break;
				}
			}
		};

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
		int posY = 180;
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
}
