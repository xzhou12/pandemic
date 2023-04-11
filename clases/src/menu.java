import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;

public class menu extends JFrame {
	public menu() {
		setTitle("Pandemic");
		setBounds(100, 100, 1550, 850);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		//panel del menu
		JPanel Pciudades = new JPanel();
		Pciudades.setBounds(0, 0, 1550, 850);
		Pciudades.setBackground(new Color(5, 153, 209));
		//imagen de fondo
		ImageIcon Imagen = new ImageIcon("mapa_mundo.png");
		Pciudades.setLayout(null);
		JButton NuevaPartida = new JButton();
		NuevaPartida.setText("Nueva partida");
		NuevaPartida.setBounds(619, 357, 139, 53);
		JButton CargarPartida = new JButton();
		CargarPartida.setText("Cargar partida");
		CargarPartida.setBounds(619, 421, 150, 50);
		JButton Informacion = new JButton();
		Informacion.setText("Informacion");
		Informacion.setBounds(621, 494, 109, 41);
		JButton ResumenPuntuaciones = new JButton();
		ResumenPuntuaciones.setText("Resumen de puntuaciones");
		ResumenPuntuaciones.setBounds(619, 585, 173, 41);
		JButton Autores = new JButton();
		Autores.setText("Autores");
		Autores.setBounds(630, 730, 150, 50);
		JButton Version = new JButton();
		Version.setText("Version");
		Version.setBounds(50, 730, 250, 50);
		Pciudades.add(NuevaPartida);
		Pciudades.add(CargarPartida);
		Pciudades.add(Informacion);
		Pciudades.add(ResumenPuntuaciones);
		Pciudades.add(Autores);
		Pciudades.add(Version);
		getContentPane().add(Pciudades);
		
		JButton Salir = new JButton();
		Salir.setText("Salir");
		Salir.setName("");
		Salir.setBounds(1320, 730, 150, 50);
		Pciudades.add(Salir);
		JLabel MapaMundi = new JLabel(Imagen);
		MapaMundi.setFocusable(false);
		MapaMundi.setBounds(0,0,1540,811);
		//añade la imagen al menu
		Pciudades.add(MapaMundi);
		setVisible(true);
	}

	static void NuevaPartida() {

	}

	static void CargarPartida() {

	}

	static void Informacion() {

	}

	static void ResumenPuntuaciones() {

	}

	static void Autores() {
		System.out.println("Autores: Albert Barrachina & Xiaobin Zhou");

	}

	static void Version() {

	}

	static void Salir() {

	}
}