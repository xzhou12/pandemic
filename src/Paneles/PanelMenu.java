package Paneles;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import backend.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
* Menu principal del juego.
* @author Albert Barrachina
* @version 1.0
*/

public class PanelMenu extends JPanel implements ActionListener {
// string para sobreescribir el panel de texto
	JButton NuevaPartida;
	JButton CargarPartida;
	JButton ResumenPuntuaciones;
	JButton Informacion;
	JButton Autores;
	JButton Version;
	JButton Salir;
	JLabel ConfigLabel;
	JLabel MapaMundi;

	public PanelMenu() {

		// panel botones 1(botnes del menu principal)--------------------------
		setBounds(0, 0, 1550, 850);
		setBackground(new Color(6, 153, 209));
		setLayout(null);

		// nueva partida
		NuevaPartida = new JButton();
		NuevaPartida.setText("Nueva partida");
		NuevaPartida.setBounds(670, 330, 180, 40);
		NuevaPartida.addActionListener(this);
		add(NuevaPartida);

		// cargar partida
		CargarPartida = new JButton();
		CargarPartida.setText("Cargar partida");
		CargarPartida.setBounds(670, 373, 180, 40);
		CargarPartida.addActionListener(this);
		add(CargarPartida);

		// resumen de puntuaciones
		ResumenPuntuaciones = new JButton();
		ResumenPuntuaciones.setText("Resumen de puntuaciones");
		ResumenPuntuaciones.setBounds(660, 453, 200, 40);
		ResumenPuntuaciones.addActionListener(this);
		add(ResumenPuntuaciones);

		// informacion del juego (instrucciones de juego)
		Informacion = new JButton();
		Informacion.setText("Informacion");
		Informacion.setBounds(670, 660, 180, 40);
		Informacion.addActionListener(this);
		add(Informacion);

		// autores
		Autores = new JButton();
		Autores.setText("Autores");
		Autores.setBounds(710, 730, 100, 40);
		Autores.addActionListener(this);
		add(Autores);

		// version
		Version = new JButton();
		Version.setText("Version");
		Version.setBounds(50, 730, 100, 40);
		Version.addActionListener(this);
		add(Version);

		// salir del juego
		Salir = new JButton();
		Salir.setText("Salir");
		Salir.setName("");
		Salir.setBounds(1320, 730, 100, 40);
		Salir.addActionListener(this);
		add(Salir);
		setVisible(true);

		// configuracion
		ImageIcon imagen = new ImageIcon("config.png");
		Image image = imagen.getImage();
		image = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		imagen = new ImageIcon(image);
		ConfigLabel = new JLabel(imagen);
		ConfigLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sonido.pulsarBoton();
				config();
			}
		});
		ConfigLabel.setBounds(20, 20, 45, 45);
		add(ConfigLabel);

		// imagen de fondo(mapa)
		imagen = new ImageIcon("mapa_mundo.png");
		// cambia las dimensiones de la imagen
		image = imagen.getImage();
		image = image.getScaledInstance(1550, 850, java.awt.Image.SCALE_SMOOTH);
		imagen = new ImageIcon(image);
		MapaMundi = new JLabel(imagen);
		MapaMundi.setFocusable(false);
		MapaMundi.setBounds(0, 0, 1550, 850);
		add(MapaMundi);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == NuevaPartida) {
			sonido.pulsarBoton();
			JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
			menu.remove(this);
			menu.add(new PanelNuevaPartida());
			menu.repaint();
		}
		if (e.getSource() == CargarPartida) {
			sonido.pulsarBoton();
			JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
			menu.remove(this);
			menu.add(new PanelCargarPartida());
			menu.repaint();
		}
		if (e.getSource() == ResumenPuntuaciones) {
			sonido.pulsarBoton();
			JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
			menu.remove(this);
			menu.add(new PanelRanking());
			menu.repaint();
		}
		if (e.getSource() == Informacion) {
			sonido.pulsarBoton();
			AccionBoton.SetTextoInfo("informacion");
			info();
		}
		if (e.getSource() == Autores) {
			sonido.pulsarBoton();
			AccionBoton.SetTextoInfo("autores");
			info();
		}
		if (e.getSource() == Version) {
			sonido.pulsarBoton();
			AccionBoton.SetTextoInfo("version");
			info();
		}
		if (e.getSource() == Salir) {
			AccionBoton.Salir();
		}
		
	}

	// -------------------------
	// entra en el menu de info
	// -------------------------
	/**
	* Abre el panel de informacion, puede ser sobre los autores, la version o las normas del juego.
	*/
	private void info() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.add(new PanelInfo());
		menu.repaint();

	}

	// ----------------------------------
	// entra en el menu de configuracion
	// ----------------------------------
	/**
	* Abre el panel de configuracion de dificultad y volumen del juego.
	*/
	private void config() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.add(new PanelConfig());
		menu.repaint();
	}

}
