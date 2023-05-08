package Paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import backend.*;

public class PanelNuevaPartida extends JPanel {
	JTextField NombreUsuario;
	JButton IniciarPartida, Cancelar;
	JLabel LabelNombre;
	JLabel LimagenNueva;
	public static String nombreUsuario;

	public PanelNuevaPartida() {
		// panel para nueva partida
		setBounds(0, 0, 1550, 850);
		setBackground(new Color(6, 153, 209));
		setLayout(null);

		// area de texto para poner el nombre
		NombreUsuario = new JTextField();
		NombreUsuario.setForeground(new Color(255, 255, 255));
		NombreUsuario.setBackground(new Color(192, 192, 192));
		NombreUsuario.setBounds(670, 250, 180, 30);
		NombreUsuario.setColumns(10);
		add(NombreUsuario);

		// iniciar la partida, abirendo el juego
		IniciarPartida = new JButton("Iniciar Partida");
		IniciarPartida.setBackground(new Color(128, 128, 128));
		IniciarPartida.setForeground(new Color(255, 255, 255));
		IniciarPartida.setHideActionText(true);
		IniciarPartida.setBounds(550, 340, 180, 40);
		add(IniciarPartida);

		// cancelar iniciar partida nueva
		Cancelar = new JButton("Cancelar");
		Cancelar.setBackground(new Color(128, 128, 128));
		Cancelar.setForeground(new Color(255, 255, 255));
		Cancelar.setHideActionText(true);
		Cancelar.setBounds(790, 340, 180, 40);
		add(Cancelar);

		// label indicando donde poner el nombre
		LabelNombre = new JLabel("Introduze tu nombre");
		LabelNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
		LabelNombre.setForeground(new Color(255, 255, 255));
		LabelNombre.setBounds(660, 170, 250, 40);
		add(LabelNombre);

		// imagen transparente que hace de marco para el menu
		ImageIcon imagen = new ImageIcon("transparente.png");
		Image image = imagen.getImage();
		image = image.getScaledInstance(500, 300, java.awt.Image.SCALE_SMOOTH);
		imagen = new ImageIcon(image);
		LimagenNueva = new JLabel(imagen);
		LimagenNueva.setBounds(410, 100, 700, 400);
		add(LimagenNueva);

		// imagen de fondo(mapa)
		imagen = new ImageIcon("mapa_mundo.png");
		// cambia las dimensiones de la imagen
		image = imagen.getImage();
		image = image.getScaledInstance(1550, 850, java.awt.Image.SCALE_SMOOTH);
		imagen = new ImageIcon(image);
		JLabel MapaMundi = new JLabel(imagen);
		MapaMundi.setFocusable(false);
		MapaMundi.setBounds(0, 0, 1550, 850);
		add(MapaMundi);

//			 inicia la partida cargando el mapa
		IniciarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.pulsarBoton();
				nombreUsuario = NombreUsuario.getText();
				if (nombreUsuario.length() < 3 || nombreUsuario.length() > 50) {
					JOptionPane.showMessageDialog(null, "Introduce un nombre de entre 3 y 50 caracteres.");
					sonido.pulsarBoton();
				} else {
					if (conexionBD.comprobarUsuario(nombreUsuario)) {
						conexionBD.guardarUsuario(nombreUsuario);
					iniciarJuego();
					} else {
						iniciarJuego();
					}
				}
			}
		});
		// inicia la partida cargando el mapa
		Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.pulsarBoton();
				VolverMenuPrincipal();
			}
		});
	}

	// ----------------------------------------------------
	// se va al panel de iniciar juego e inicia la partida
	// ----------------------------------------------------
	public void iniciarJuego() {
		// carga la info de las ciudades
		PanelCiudad.getText();
		// Va a la clase jugar
		jugar.inicializarPartida();
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.add(new PanelTablero());
		menu.repaint();
	}

	// ----------------------------------
	// se va al panel del menu principal
	// ----------------------------------
	public void VolverMenuPrincipal() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.add(new PanelMenu());
		menu.repaint();
	}

}
