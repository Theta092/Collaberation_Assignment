package collaberation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;

public class LifeGUI {
	Scanner input = new Scanner(System.in);
	Life life = new Life(0, 0);
	JFrame frame;
	JPanel panel;
	JLabel label;
	JButton[][] cell;
	String[][] a = new String[0][0];
	int x, y;
	JTextField quest;

	public LifeGUI() {
		
		frame = new JFrame("Life");
		frame.setSize(1000,1000);
		frame.setLocationRelativeTo(null);
		
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		quest = new JTextField("what is the x value of the grid");
		
		for(int i=0; i<cell[0].length; i++) {
			for(int j=0; j<cell[1].length; j++) {
				cell[i][j] = new JButton();
				cell[i][j].setActionCommand(a[i][j]);
				cell[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {		
					}	
				});
				c.gridx = 1;
				c.gridy = 4;
				panel.add(quest,c);
				frame.setContentPane(panel);
				frame.setVisible(true);
			}
		}
	}
}
