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
		getContentPane().add(Pciudades);
		//imagen de fondo
		ImageIcon Imagen = new ImageIcon("mapa_mundo.png");
		//cambia las dimensiones de la imagen
		Image image = Imagen.getImage();
		Image newimg = image.getScaledInstance(1550, 850,  java.awt.Image.SCALE_SMOOTH); 
		Imagen = new ImageIcon(newimg);
		Pciudades.setLayout(null);
		//botones
		JButton NuevaPartida = new JButton();
		NuevaPartida.setText("Nueva partida");
		NuevaPartida.setBounds(670, 330, 180, 40);
		JButton CargarPartida = new JButton();
		CargarPartida.setText("Cargar partida");
		CargarPartida.setBounds(670, 373, 180, 40);
		JButton ResumenPuntuaciones = new JButton();
		ResumenPuntuaciones.setText("Resumen de puntuaciones");
		ResumenPuntuaciones.setBounds(660, 453, 200, 40);
		JButton Informacion = new JButton();
		Informacion.setText("Informacion");
		Informacion.setBounds(670, 660, 180, 40);
		JButton Autores = new JButton();
		Autores.setText("Autores");
		Autores.setBounds(710, 730, 100, 40);
		JButton Version = new JButton();
		Version.setText("Version");
		Version.setBounds(50, 730, 100, 40);
		Pciudades.add(NuevaPartida);
		Pciudades.add(CargarPartida);
		Pciudades.add(Informacion);
		Pciudades.add(ResumenPuntuaciones);
		Pciudades.add(Autores);
		Pciudades.add(Version);
		
		
		JButton Salir = new JButton();
		Salir.setText("Salir");
		Salir.setName("");
		Salir.setBounds(1320, 730, 100, 40);
		Pciudades.add(Salir);
		JLabel MapaMundi = new JLabel(Imagen);
		MapaMundi.setFocusable(false);
		MapaMundi.setBounds(0,0,1550, 850);
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