package Paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JList;
import javax.swing.JOptionPane;

import backend.*;

public class PanelCargarPartida extends JPanel {
	public PanelCargarPartida() {
		setBounds(0, 0, 1550, 850);
		setBackground(new Color(6, 153, 209));
		setLayout(null);

		// boton para volver al menu principal
		JButton VolverConf = new JButton();
		VolverConf.setBackground(new Color(72, 72, 72));
		VolverConf.setForeground(new Color(255, 255, 255));
		VolverConf.setText("Volver");
		VolverConf.setBounds(200, 20, 100, 40);
		add(VolverConf);
		// texto
		JTextPane Partida;
		Partida = new JTextPane();
		Partida.setText("Selecciona una partida.");
		Partida.setOpaque(false);
		Partida.setEditable(false);
		Partida.setBounds(640, 200, 200, 100);
		Partida.setForeground(new Color(255, 255, 255));
		Partida.setFont(new Font("Tahoma", Font.BOLD, 24));
		StyledDocument doc = Partida.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		add(Partida);
		// desplegable con las partidas guardadas (nombres de usuarios)

		ArrayList<String> nombreJugador = conexionBD.cargarNombresPartidas();
		JComboBox<String> cajaOpciones = new JComboBox<String>();
		for (int i = 0; i < nombreJugador.size(); i++) {
			cajaOpciones.addItem(nombreJugador.get(i));
		}
		cajaOpciones.setBounds(640, 300, 200, 30);
		add(cajaOpciones);
		// boton de cargar partida
		JButton CargarPartida = new JButton();
		CargarPartida.setBackground(new Color(72, 72, 72));
		CargarPartida.setForeground(new Color(255, 255, 255));
		CargarPartida.setText("Cargar partida");
		CargarPartida.setBounds(640, 615, 200, 40);
		add(CargarPartida);
		// imagen trasparente de fondo para efecto de menu
		ImageIcon imagen = new ImageIcon("transparente.png");
		Image image = imagen.getImage();
		image = image.getScaledInstance(1500, 800, java.awt.Image.SCALE_SMOOTH);
		imagen = new ImageIcon(image);
		JLabel ConfigBoxImage = new JLabel(imagen);
		ConfigBoxImage.setBounds(195, 15, 1140, 780);
		add(ConfigBoxImage);

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

		// vuelve al menu prinpal
		VolverConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.pulsarBoton();
				volver();
			}
		});
		// selecciona la partida que has elejido y la carga
		CargarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.pulsarBoton();
				if ((String) cajaOpciones.getSelectedItem() != null) {
					jugar.inicializarPartida();
					conexionBD.cargarPartida((String) cajaOpciones.getSelectedItem());
					iniciarJuego();
					PanelTablero.TextoAnimado();
				} else {
					JOptionPane.showMessageDialog(null,
							"Necesitas guardar una partida para poder cargar una partida.  -_-");
					sonido.pulsarBoton();
				}
			}
		});
	}

	// ----------------------------------------------------
	// se va al tablero de juego e inicia la partida
	// ----------------------------------------------------
	public void iniciarJuego() {
		// carga la info de las ciudades
		PanelCiudad.getText();
		// Va a la clase jugar
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.add(new PanelTablero());
		menu.repaint();
	}

	// ---------------------------
	// velve al menu principal
	// ---------------------------
	private void volver() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.getContentPane().add(new PanelMenu());
		menu.repaint();
	}

}
