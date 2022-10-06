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
		
		ImageIcon deadCell, aliveCell, startIcon, startHighlightIcon;
		
		frame = new JFrame("Life");
		frame.setSize(1000,800);
		frame.setLocationRelativeTo(null);
		
		//Set Icons
		deadCell = new ImageIcon(getClass().getClassLoader().getResource("dead cell.png"));
		aliveCell = new ImageIcon(getClass().getClassLoader().getResource("alive cell.png"));
		startIcon = new ImageIcon(getClass().getClassLoader().getResource("start button.png"));
		startHighlightIcon = new ImageIcon(getClass().getClassLoader().getResource("start button blue.png"));
		
		panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.darkGray);
		
		begin = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		questx = new JTextField(10);
		questx.setPreferredSize(new Dimension(96, 32));
		questx.setOpaque(false);
		c.gridx = 1;
		c.gridy = 4;
		begin.add(questx,c);
		
		questy = new JTextField(10);
		questy.setPreferredSize(new Dimension(96, 32));
		questy.setOpaque(false);
		c.gridx = 1;
		c.gridy = 5;
		begin.add(questy,c);
		
		
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
				
				cell = new JButton[x][y];
				a = new String[x][y];
				
				for(int i=0; i<x; i++) {
					for(int j=0; j<y; j++) {
						a[i][j] = i+""+j;
					}
				}
				
				for(int i=0; i<cell[0].length; i++) {
					for(int j=0; j<cell[0].length; j++) {
						System.out.println(i + " " + j);
						cell[i][j] = new JButton(deadCell);
						cell[i][j].setActionCommand(a[i][j]);
						cell[i][j].addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								int x =  Integer.valueOf(e.getActionCommand()) / 10;
								int y = Integer.valueOf(e.getActionCommand()) % 10;
								if(life.checkCellState(x, y))
								{
									cell[x][y].setIcon(aliveCell);
									life.aliveCell(x, y);
								}
								else
								{
									cell[x][y].setIcon(deadCell);
									life.killCell(x, y);
								}
							}	
						});
						cell[i][j].setPreferredSize(new Dimension(32, 32));
						cell[i][j].setContentAreaFilled(false);
						cell[i][j].setBorderPainted(false);
						cell[i][j].setFocusPainted(false); 
						cell[i][j].setOpaque(false);
						c.gridx = i;
						c.gridy = j;
						run.add(cell[i][j],c);
					}
				}

				run.setVisible(true);
				begin.setVisible(false);
			}
		});
		c.gridx = 1;
		c.gridy = 7;
		begin.add(start,c);
		
		run = new JPanel(new GridBagLayout());
		
		System.out.println("check");
		
		begin.setVisible(true);
		run.setVisible(false);
		
		panel.add(begin);
		panel.add(run);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
}
