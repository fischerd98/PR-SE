package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EingabeWindow extends JFrame {

	private JPanel contentPane;
	
	private SudokuWindow_Sicherung sw;
	private CreateSudokuWindow csw;
	private String pos;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EingabeWindow frame = new EingabeWindow();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public EingabeWindow(String pos, SudokuWindow_Sicherung sw) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 449);
		
		this.sw = sw;
		this.pos = pos;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initButtons();
	}
	
	public EingabeWindow(String pos, CreateSudokuWindow csw) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 449);
		
		this.csw = csw;
		this.pos = pos;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initButtons();
	}
	
	
	public void initButtons() {
		
		int posX = 77;
		int posY = 131;
		int width = 43;

		
		int count = 1;
		
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
				
				count++;
			}
			
			
			posY += width;
			posX = 77;
		}
	}
	
	public void setField(String val) {
		
		int num = Integer.parseInt(val);
		
		String[] arr = this.pos.split(";");
		
		int posX = Integer.parseInt(arr[0]);
		int posY = Integer.parseInt(arr[1]);
		
		if(sw != null) {
			sw.setField(posX, posY, num);
		} else if(csw != null) {
			csw.setField(posX, posY, num);
		}
		
		
		this.dispose();
	}

}
