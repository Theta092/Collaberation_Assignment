package collaberation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LifeGUI {
	Life life = new Life(0, 0);
	JFrame frame;
	JPanel panel;
	JLabel label;
	JButton[][] cell;
	String[][] a = new String[0][0];

	public LifeGUI() {
		frame = new JFrame("Life");
		frame.setSize(1000,1000);
		frame.setLocationRelativeTo(null);
		
		
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		for(int i=0; i<cell[0].length; i++) {
			for(int j=0; j<cell[1].length; j++) {
				cell[i][j] = new JButton();
				cell[i][j].setActionCommand(a[i][j]);
				cell[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {		
					}	
				});
			}
		}		
	}
}
