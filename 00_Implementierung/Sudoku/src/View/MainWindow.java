package View;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Model.Model;
import Model.SudokuListItems;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;

public class MainWindow {

	private JFrame frame;
	private Model model;

	//private JComboBox comboBox_weiterspielen;
	//private JComboBox comboBox_neu;
	private JComboBox comboBox_sudokus;

	private ButtonGroup gbForm;

	private int selectedForm = 0; // 0 = 9x9; 1 = Freiform; // other = Ninja
	private int selectedLevel = 0; // 0 = Easy; 1 = Medium; // other = Hard

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {

		this.model = new Model();

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 382, 574);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		ActionListener rdbtnActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				JRadioButton rb = (JRadioButton) actionEvent.getSource();

				switch (rb.getText()) {
				case "9x9":
					selectedForm = 0;
					break;
				case "Freiform":
					selectedForm = 1;
					break;
				case "Ninja":
					selectedForm = 2;
					break;
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

				selectionChanged();
			}
		};

		JButton btnNewButton = new JButton("weiterspielen");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if(selectedForm == 0) {
					SudokuWindow w = new SudokuWindow((SudokuListItems) comboBox_sudokus.getSelectedItem(), true,
							false);
					w.setVisible(true);
				} else {
					
					SudokuListItems k = (SudokuListItems) comboBox_sudokus.getSelectedItem();
					
					SudokuWindow w = new SudokuWindow((SudokuListItems) comboBox_sudokus.getSelectedItem(), false,
							true);
					w.setVisible(true);
				}
				
				
			}
		});
		btnNewButton.setBounds(30, 237, 151, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Zuf\u00E4lliges Sudoku");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SudokuWindow w = new SudokuWindow((SudokuListItems) comboBox_sudokus.getSelectedItem(), true,
						false);
				w.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(30, 305, 309, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("neu starten");
		btnNewButton_2.setBounds(191, 237, 148, 23);
		frame.getContentPane().add(btnNewButton_2);

//		comboBox_weiterspielen = new JComboBox();
//		comboBox_weiterspielen.setBounds(204, 393, 135, 22);
//		for (SudokuListItems s : model.loadSimpleSudokus()) {
//			comboBox_weiterspielen.addItem(s);
//		}
//		frame.getContentPane().add(comboBox_weiterspielen);

//		comboBox_neu = new JComboBox();
//		comboBox_neu.setBounds(191, 357, 135, 22);
//		frame.getContentPane().add(comboBox_neu);

		JLabel lblNewLabel = new JLabel("Sudoku");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(141, 21, 65, 34);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton_3 = new JButton("Sudoku erstellen");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateSudokuWindow csw = new CreateSudokuWindow();
				csw.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(30, 271, 309, 23);
		frame.getContentPane().add(btnNewButton_3);

//		JButton btnNewButton_4 = new JButton("New button");
//		btnNewButton_4.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				////////////////// nur zum testen der GUI
//				SudokuListItems sli = new SudokuListItems("d", "d");
//				SudokuWindow swskjdf = new SudokuWindow(sli, false, true);
//				swskjdf.setVisible(true);
//			}
//		});
//		btnNewButton_4.setBounds(198, 501, 89, 23);
//		frame.getContentPane().add(btnNewButton_4);

//		JComboBox comboBoxFF = new JComboBox();
//		comboBoxFF.setBounds(204, 426, 135, 22);
//		for (SudokuListItems s : model.loadFFSudokus()) {
//			comboBoxFF.addItem(s);
//		}
//		frame.getContentPane().add(comboBoxFF);

//		JButton button = new JButton("FreiformSudoku weiterspielen");
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				SudokuWindow w = new SudokuWindow((SudokuListItems) comboBoxFF.getSelectedItem(), false, true);
//				w.setVisible(true);
//			}
//		});
//		button.setBounds(198, 454, 135, 23);
//		frame.getContentPane().add(button);

		JRadioButton rdbtn9x9 = new JRadioButton("9x9");
		rdbtn9x9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtn9x9.setBounds(30, 83, 109, 23);
		rdbtn9x9.setSelected(true);
		rdbtn9x9.addActionListener(rdbtnActionListener);
		frame.getContentPane().add(rdbtn9x9);

		JLabel lblNewLabel_1 = new JLabel("Sudoku Form");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(127, 62, 89, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JRadioButton rdbtnNinja = new JRadioButton("Ninja");
		rdbtnNinja.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNinja.setBounds(141, 83, 109, 23);
		rdbtnNinja.addActionListener(rdbtnActionListener);
		frame.getContentPane().add(rdbtnNinja);

		JRadioButton rdbtnFreiform = new JRadioButton("Freiform");
		rdbtnFreiform.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnFreiform.setBounds(252, 83, 109, 23);
		rdbtnFreiform.addActionListener(rdbtnActionListener);
		frame.getContentPane().add(rdbtnFreiform);

		gbForm = new ButtonGroup();
		gbForm.add(rdbtn9x9);
		gbForm.add(rdbtnFreiform);
		gbForm.add(rdbtnNinja);

		comboBox_sudokus = new JComboBox();
		comboBox_sudokus.setBounds(30, 204, 309, 22);
		frame.getContentPane().add(comboBox_sudokus);

		JLabel lblNewLabel_2 = new JLabel("Schwierigkeitsgrad");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(120, 117, 148, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JRadioButton rdbtnEasy = new JRadioButton("Einfach");
		rdbtnEasy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnEasy.setBounds(30, 138, 109, 23);
		rdbtnEasy.setSelected(true);
		rdbtnEasy.addActionListener(rdbtnActionListener);
		frame.getContentPane().add(rdbtnEasy);

		JRadioButton rdbtnMedium = new JRadioButton("Mittel");
		rdbtnMedium.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnMedium.setBounds(141, 138, 109, 23);
		rdbtnMedium.addActionListener(rdbtnActionListener);
		frame.getContentPane().add(rdbtnMedium);

		JRadioButton rdbtnHard = new JRadioButton("Schwer");
		rdbtnHard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnHard.setBounds(252, 140, 109, 23);
		rdbtnHard.addActionListener(rdbtnActionListener);
		frame.getContentPane().add(rdbtnHard);

		ButtonGroup gbLevel = new ButtonGroup();
		gbLevel.add(rdbtnEasy);
		gbLevel.add(rdbtnMedium);
		gbLevel.add(rdbtnHard);

		JLabel lblNewLabel_3 = new JLabel("Spiel ausw\u00E4hlen");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(127, 179, 174, 14);
		frame.getContentPane().add(lblNewLabel_3);

		selectionChanged();
	}

	public void selectionChanged() {		
		
		System.out.println(selectedForm + " " + selectedLevel);
		
		comboBox_sudokus.removeAllItems();
		
		for (SudokuListItems s : model.loadSudokus(selectedForm, selectedLevel)) {
			comboBox_sudokus.addItem(s);
		}

	}
}
