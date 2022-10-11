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
	Life life;
	JFrame frame;
	JPanel panel, begin, run;
	JLabel label;
	JButton start, step, play;
	JButton[][] cell;
	String[][] a = new String[0][0];
	int x, y;
	String num;
	JTextField questx, questy;

	public LifeGUI() {
		
		ImageIcon deadCell, aliveCell, startIcon, startHighlightIcon, playIcon, stepIcon;
		
		frame = new JFrame("Life");
		frame.setSize(1000,800);
		frame.setLocationRelativeTo(null);
		
		//Set Icons
		deadCell = new ImageIcon(getClass().getClassLoader().getResource("dead cell.png"));
		aliveCell = new ImageIcon(getClass().getClassLoader().getResource("alive cell.png"));
		startIcon = new ImageIcon(getClass().getClassLoader().getResource("start button.png"));
		startHighlightIcon = new ImageIcon(getClass().getClassLoader().getResource("start button blue.png"));
		stepIcon = new ImageIcon(getClass().getClassLoader().getResource("step button.png"));
		playIcon = new ImageIcon(getClass().getClassLoader().getResource("play button.png"));
		
		panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.darkGray);
		
		begin = new JPanel(new GridBagLayout());
		run = new JPanel(new GridBagLayout());
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
				x = Integer.valueOf(questx.getText());
				
				y = Integer.valueOf(questy.getText());
				
				cell = new JButton[x][y];
				a = new String[x][y];
				
				life  = new Life(x, y);
				
				for(int i=0; i<cell.length; i++) {
					for(int j=0; j<cell[0].length; j++) {
						System.out.println(i + " " + j);
						cell[i][j] = new JButton(deadCell);
						cell[i][j].setActionCommand(String.valueOf((i*100) + j));
						cell[i][j].addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								int x2 =  Integer.valueOf(e.getActionCommand()) / 10;
								int y2 = Integer.valueOf(e.getActionCommand()) % 10;
								if(life.checkCellState(x2, y2))
								{
									cell[x2][y2].setIcon(aliveCell);
									life.aliveCell(x2, y2);
								}
								else
								{
									cell[x][y].setIcon(deadCell);
									life.killCell(x2, y2);
								}
								int x =  Integer.valueOf(e.getActionCommand()) / 100;
								int y = Integer.valueOf(e.getActionCommand()) % 100;
								updateCellIcon(x, y, aliveCell, deadCell);
								
							}	
						});
						cell[i][j].setPreferredSize(new Dimension(32, 32));
						cell[i][j].setContentAreaFilled(false);
						cell[i][j].setBorderPainted(false);
						cell[i][j].setFocusPainted(false); 
						cell[i][j].setOpaque(false);
						c.gridx = i;
						c.gridy = j + 1;
						run.add(cell[i][j],c);
					}
				}

				/*
				 * Simulation user input
				 */
				//stepper button
				step = new JButton(stepIcon);
				step.setPreferredSize(new Dimension(32, 32));
				step.setContentAreaFilled(false);
				step.setBorderPainted(false);
				step.setFocusPainted(false); 
				step.setOpaque(false);
				step.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						life.updateCellStates();
						for(int i=0; i<cell.length; i++) {
							for(int j=0; j<cell[0].length; j++) {
								if(life.checkCellState(i, j))
								{
									cell[i][j].setIcon(aliveCell);
									System.out.println(i + " " + j + " is alive");
								}
								else
								{
									cell[i][j].setIcon(deadCell);
								}
							}
						}
					}
					
				});
				c.gridx = 0;
				c.gridy = 0;
				run.add(step, c);
				//play button
				play = new JButton(playIcon);
				play.setPreferredSize(new Dimension(32, 32));
				play.setContentAreaFilled(false);
				play.setBorderPainted(false);
				play.setFocusPainted(false); 
				play.setOpaque(false);
				c.gridx = cell.length - 1;
				c.gridy = 0;
				run.add(play, c);
				
				run.setVisible(true);
				begin.setVisible(false);
			}
		});
		c.gridx = 1;
		c.gridy = 7;
		begin.add(start,c);
		
		System.out.println("check");
		
		begin.setVisible(true);
		run.setVisible(false);
		
		panel.add(begin);
		panel.add(run);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
	
	public void updateCellIcon(int x, int y, ImageIcon alive, ImageIcon dead)
	{
		if(life.checkCellState(x, y))
		{
			life.killCell(x, y);
			System.out.println(life.checkCellState(x, y));
			cell[x][y].setIcon(dead);
		}
		else
		{
			life.aliveCell(x, y);
			System.out.println(life.checkCellState(x, y));
			cell[x][y].setIcon(alive);
		}
	}
}
