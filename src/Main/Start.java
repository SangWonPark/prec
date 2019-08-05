package Main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import GUI.InGame;

public class Start extends JFrame implements ActionListener{
	ImageIcon mainImg =  new ImageIcon("imgsource/anipung.jpg");
	JButton startB = null;
	JPanel back = null;
	JPanel panelB = new JPanel();
	
	JTextField ID_TF = new JTextField(10);

	public Start() {
		
		super("LOGIN");
		this.setBounds(100, 100, 600, 450);
		this.setLayout(new BorderLayout());
		inti();

		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
	}


	private void inti() {
		
		back = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(mainImg.getImage(), 0, 0, null);
			}
		};
		back.setLayout(null);
		startB = new JButton(new ImageIcon("imgsource/startB.png"));
		panelB.setLayout(new BorderLayout());
		panelB.setBounds(200, 300, 200, 100);
		panelB.add(ID_TF,"Center");
		panelB.add(startB, "South");
		back.add(panelB);

		this.add(back);

		this.setVisible(true);
		startB.addActionListener(this);
	}

	public static void main(String[] args) {
		new Start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = ID_TF.getText();
		new InGame(name);
		this.setVisible(false);
	}

}
