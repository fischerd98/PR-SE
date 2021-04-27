package View;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Model.Model;
import Model.SudokuListItems;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;

public class MainWindow {

	private JFrame frame;
	private Model model;
	
	private JComboBox comboBox_weiterspielen;
	private JComboBox comboBox_neu;
	
	
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
		frame.setBounds(100, 100, 538, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Sudoku weiterspielen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SudokuWindow_Sicherung w = new SudokuWindow_Sicherung((SudokuListItems)comboBox_weiterspielen.getSelectedItem());
				w.setVisible(true);
			}
		});
		btnNewButton.setBounds(195, 75, 135, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Zuf\u00E4lliges Sudoku");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SudokuWindow_Sicherung w = new SudokuWindow_Sicherung((SudokuListItems)comboBox_weiterspielen.getSelectedItem());
				w.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(50, 109, 135, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Sudoku ausw\u00E4hlen");
		btnNewButton_2.setBounds(340, 75, 135, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		comboBox_weiterspielen = new JComboBox();
		comboBox_weiterspielen.setBounds(195, 109, 135, 22);
		for(SudokuListItems s : model.loadSimpleSudokus()) {
			comboBox_weiterspielen.addItem(s);
		}		
		frame.getContentPane().add(comboBox_weiterspielen);
		
		comboBox_neu = new JComboBox();
		comboBox_neu.setBounds(340, 109, 135, 22);
		frame.getContentPane().add(comboBox_neu);
		
		JLabel lblNewLabel = new JLabel("Sudoku Hauptmen\u00FC");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(170, 30, 181, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_3 = new JButton("Sudoku erstellen");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateSudokuWindow csw = new CreateSudokuWindow();
				csw.setVisible(true);				
			}
		});
		btnNewButton_3.setBounds(74, 280, 111, 23);
		frame.getContentPane().add(btnNewButton_3);
	}
}
