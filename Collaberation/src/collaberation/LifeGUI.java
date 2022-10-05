/*
*     __       __   _______  _______ 
*    |  |     |  | |   ____||   ____|
*    |  |     |  | |  |__   |  |__   
*    |  |     |  | |   __|  |   __|  
*    |  `----.|  | |  |     |  |____ 
*    |_______||__| |__|     |_______|
*                                    
*/
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
	String num;
	JTextField quest;

	public LifeGUI() {
		
		ImageIcon deadCell, aliveCell, startIcon;
		
		frame = new JFrame("Life");
		frame.setSize(1000,800);
		frame.setLocationRelativeTo(null);
		
		//Set Icons
		deadCell = new ImageIcon(getClass().getClassLoader().getResource("dead cell.png"));
		aliveCell = new ImageIcon(getClass().getClassLoader().getResource("alive cell.png"));
		startIcon = new ImageIcon(getClass().getClassLoader().getResource("start button.png"));
		
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		quest = new JTextField(10);
		c.gridx = 1;
		c.gridy = 4;
		panel.add(quest,c);
		
		quest = new JTextField(10);
		c.gridx = 1;
		c.gridy = 5;
		panel.add(quest,c);
		
		cell = new JButton[x][y];
		a = new String[x][y];
		
		
		for(int i=0; i<cell.length; i++) {
			for(int j=0; j<cell[1].length; j++) {
				cell[i][j] = new JButton(deadCell);
				cell[i][j].setActionCommand(a[i][j]);
				cell[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {		
					}	
				});
				cell[i][j].setContentAreaFilled(false);
				cell[i][j].setBorderPainted(false);
				cell[i][j].setFocusPainted(false); 
				cell[i][j].setOpaque(false);
			}
		}
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
}
