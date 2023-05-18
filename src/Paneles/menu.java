package Paneles;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
* Frame de la aplicacion.
* Esta clase es el marco de la aplicacion, se llama desde main y todos los paneles se ponen aqui.
* @author Albert Barrachina
* @version 1.0
*/
public class menu extends JFrame {
	PanelMenu PanelMenu;
	
	/**
	* 
	* .
	* @author Albertadsadasdas Barrachina
	* @version 1.0
	*/
	public menu() {
		setTitle("Pandemic");
		ImageIcon img = new ImageIcon("Pandemic.png");
		setIconImage(img.getImage());
		setBounds(150, 120, 1550, 850);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		//panel de menu principal
		PanelMenu = new PanelMenu();
		add(PanelMenu);
		//hace que el juego sea visible
		setVisible(true);
	}

}