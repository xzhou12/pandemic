package Paneles;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class menu extends JFrame {
	PanelMenu PanelMenu;

	public menu() {
		setTitle(
				"Pandemic");
		ImageIcon img = new ImageIcon("Pandemic.png");
		setIconImage(img.getImage());
		setBounds(150, 120, 1550, 850);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		PanelMenu = new PanelMenu();
		add(PanelMenu);
		setVisible(true);
	}

	

}