//truck

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LifeGUI {
	Scanner input = new Scanner(System.in);
	Life life;
	JFrame frame;
	JPanel panel, begin, run;
	JLabel label;
	JButton start, step, play, reset;
	JButton[][] cell;
	String[][] a = new String[0][0];
	int x, y;
	String num;
	JTextField questx, questy;
	//timer vars
	int timerSpeed = 2600;
	final int FPS_MIN = 0;
	final int FPS_MAX = 30;
	final int FPS_INIT = 15;
	boolean playState = false;
	int tickSpeed;
	JSlider simSpeed = new JSlider(FPS_MIN, FPS_MAX, FPS_INIT);
	ImageIcon deadCell, aliveCell, startIcon, startHighlightIcon, playIcon, stepIcon, pauseIcon, resetIcon, scrollBar;

	public LifeGUI() {
		
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
		pauseIcon = new ImageIcon(getClass().getClassLoader().getResource("pause button.png"));
		resetIcon = new ImageIcon(getClass().getClassLoader().getResource("reset button.png"));
		scrollBar = new ImageIcon(getClass().getClassLoader().getResource("scroll bar bg.png"));
		
		
		panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.darkGray);
		
		begin = new JPanel(new GridBagLayout());
		begin.setBackground(Color.gray);
		begin.setSize(new Dimension(100, 100));
		run = new JPanel(new GridBagLayout());
		run.setBackground(Color.darkGray);
		GridBagConstraints c = new GridBagConstraints();
		
		
		//getting the x dimension for the grid
		label = new JLabel("enter the x dimension");
		label.setForeground(Color.white);
		c.gridx = 1;
		c.gridy = 1;
		begin.add(label,c);
		
		questx = new JTextField(10);
		questx.setPreferredSize(new Dimension(96, 32));
		questx.setOpaque(true);
		c.gridx = 1;
		c.gridy = 2;
		begin.add(questx,c);
		
		//getting the y dimension for the grid
		label = new JLabel("enter the y dimension");
		label.setForeground(Color.white);
		c.gridx = 1;
		c.gridy = 3;
		begin.add(label,c);
		
		questy = new JTextField(10);
		questy.setPreferredSize(new Dimension(96, 32));
		questy.setOpaque(true);
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
						cell[i][j] = new JButton(deadCell);
						cell[i][j].setActionCommand(String.valueOf((i*100) + j));
						cell[i][j].addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {

								int x =  Integer.valueOf(e.getActionCommand()) / 100;
								int y = Integer.valueOf(e.getActionCommand()) % 100;
								updateCellIcon(x, y);
								
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
						changeCells();
					}
					
				});
				c.gridx = 0;
				c.gridy = 0;
				run.add(step, c);
				
				//Reset button
				reset = new JButton(resetIcon);
				reset.setPreferredSize(new Dimension(32, 32));
				reset.setContentAreaFilled(false);
				reset.setBorderPainted(false);
				reset.setFocusPainted(false); 
				reset.setOpaque(false);
				reset.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						life.resetCells();
						for(int i = 0; i < x; i++)
						{
							for(int j = 0; j < y; j++)
							{
								if(life.checkCellState(i, j))
								{
									cell[i][j].setIcon(aliveCell);
								}
								else
								{
									cell[i][j].setIcon(deadCell);
								}
							}
						}
					}
					
				});
				c.gridx = 1;
				c.gridy = 0;
				run.add(reset, c);
				
				//timer setup
				tickSpeed = simSpeed.getValue();
				Timer timer = new Timer(0, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {            
						changeCells();
					}
				});
				
				//timer slider
				simSpeed.setPreferredSize(new Dimension(128, 32));
				simSpeed.setMajorTickSpacing(10);
				simSpeed.setMinorTickSpacing(1);
				simSpeed.setPaintTicks(true);
				simSpeed.setPaintLabels(true);
				simSpeed.addChangeListener(new ChangeListener() {

					@Override
					public void stateChanged(ChangeEvent e) {
						// TODO Auto-generated method stub
						tickSpeed = simSpeed.getValue();
						if(simSpeed.getValue() == 0)
						{
							timer.stop();
						}
						else
						{
							timerSpeed = 5000 - (tickSpeed * 160);
							timer.setDelay(timerSpeed);
						}
					}
					
				});
				simSpeed.setOrientation(SwingConstants.HORIZONTAL);
				c.gridx = 1;
				c.gridy = 0;
				panel.add(simSpeed, c);
				
				
				//play button
				play = new JButton(playIcon);
				play.setPreferredSize(new Dimension(32, 32));
				play.setContentAreaFilled(false);
				play.setBorderPainted(false);
				play.setFocusPainted(false); 
				play.setOpaque(false);
				play.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						timer.setRepeats(true);
						if(playState)
						{
							playState = false;
							play.setIcon(playIcon);
						}
						else
						{
							playState = true;
							play.setIcon(pauseIcon);
						}
						
						if(!playState)
						{
							timer.stop();
						}
						else
						{
							life.updateCellStates();
							
							for(int i=0; i<cell.length; i++) {
								for(int j=0; j<cell[0].length; j++) {
									if(life.checkCellState(i, j))
									{
										cell[i][j].setIcon(aliveCell);
									}
									else
									{
										cell[i][j].setIcon(deadCell);
									}
								}
							}
							timer.setDelay(5000 - (tickSpeed * 160));
							timer.start();
							timerSpeed = 5000 - (tickSpeed * 160);
							timer.setDelay(timerSpeed);
						}
					}
					
				});
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
		
		begin.setVisible(true);
		run.setVisible(false);
		
		panel.add(begin);
		panel.add(run);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
	
	public void updateCellIcon(int x, int y)
	{
		if(life.checkCellState(x, y))
		{
			life.killCell(x, y);
			cell[x][y].setIcon(deadCell);
		}
		else
		{
			life.aliveCell(x, y);
			cell[x][y].setIcon(aliveCell);
		}
	}
	
	public void changeCells()
	{
		life.updateCellStates();
		for(int i=0; i<cell.length; i++) {
			for(int j=0; j<cell[0].length; j++) {
				if(life.checkCellState(i, j))
				{
					cell[i][j].setIcon(aliveCell);
				}
				else
				{
					cell[i][j].setIcon(deadCell);
				}
			}
		}
	}
}
