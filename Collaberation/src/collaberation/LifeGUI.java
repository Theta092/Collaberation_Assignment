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

import java.awt.Color;
import java.awt.Dimension;
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
	JPanel panel, begin, run;
	JLabel label;
	JButton start;
	JButton[][] cell;
	String[][] a = new String[0][0];
	int x, y;
	String num;
	JTextField questx, questy;

	public LifeGUI() {
		
		for(int i=0; i<x; i++) {
			for(int j=0; j<y; j++) {
				a[i][j] = i+""+j;
			}
		}
		
		ImageIcon deadCell, aliveCell, startIcon;
		
		frame = new JFrame("Life");
		frame.setSize(1000,800);
		frame.setLocationRelativeTo(null);
		
		//Set Icons
		deadCell = new ImageIcon(getClass().getClassLoader().getResource("dead cell.png"));
		aliveCell = new ImageIcon(getClass().getClassLoader().getResource("alive cell.png"));
		startIcon = new ImageIcon(getClass().getClassLoader().getResource("start button.png"));
		
		panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.darkGray);
		
		begin = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		questx = new JTextField(10);
		c.gridx = 1;
		c.gridy = 4;
		begin.add(questx,c);
		
		questy = new JTextField(10);
		c.gridx = 1;
		c.gridy = 5;
		begin.add(questy,c);
		
		cell = new JButton[x][y];
		a = new String[x][y];
		
		start = new JButton(startIcon);
		start.setPreferredSize(new Dimension(96, 32));
		start.setContentAreaFilled(false);
		start.setBorderPainted(false);
		start.setFocusPainted(false); 
		start.setOpaque(false);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				num = questx.getText();
				x = Integer.valueOf(num);
				
				num = questy.getText();
				y = Integer.valueOf(num);
				
				run.setVisible(true);
				begin.setVisible(false);
			}
		});
		c.gridx = 3;
		c.gridy = 7;
		begin.add(start,c);
		
		run = new JPanel(new GridBagLayout());
		
		for(int i=0; i<x; i++) {
			for(int j=0; j<y; j++) {
				cell[i][j] = new JButton(deadCell);
				cell[i][j].setActionCommand(a[i][j]);
				cell[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {	
						int q, w;
						int num = Integer.valueOf(e.getActionCommand());
						q=num/10;
						w=num%10;
					}	
				});
				cell[i][j].setContentAreaFilled(false);
				cell[i][j].setBorderPainted(false);
				cell[i][j].setFocusPainted(false); 
				cell[i][j].setOpaque(false);
				
				c.gridx = i;
				c.gridy = j;
				run.add(cell[i][j],c);
			}
		}
		
		
		begin.setVisible(true);
		run.setVisible(false);
		
		panel.add(begin);
		panel.add(run);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
}
