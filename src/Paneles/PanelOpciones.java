package Paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import backend.*;

/**
* Panel para guardar partida y volver al menu principal.
* @author Albert Barrachina
* @version 1.0
*/

public class PanelOpciones extends JPanel {

	public PanelOpciones() {
		setBounds(0, 0, 1550, 850);
		setBackground(new Color(6, 153, 209));
		setLayout(null);

		// boton para salir de la partida
		JButton Salir = new JButton("Salir");
		Salir.setBounds(350, 630, 150, 60);
		Salir.setBackground(new Color(72, 72, 72));
		Salir.setForeground(new Color(255, 255, 255));
		Salir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(Salir);
		Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.pulsarBoton();
				String[] opciones = { "Guardar y salir", "Salir sin guardar", "cancelar" };
				int opcionDialogo = JOptionPane.showOptionDialog(getParent(), "Saliendo al menu principal",
						"¿Guardar partida?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
						opciones, opciones[2]);

				if (opcionDialogo == JOptionPane.YES_OPTION) {
					sonido.pulsarBoton();
					VolverMenu();
					conexionBD.guardarPartida();
				} else if (opcionDialogo == JOptionPane.NO_OPTION) {
					sonido.pulsarBoton();
					VolverMenu();
				} else if (opcionDialogo == JOptionPane.CANCEL_OPTION) {
					sonido.pulsarBoton();
				} else if (opcionDialogo == JOptionPane.CLOSED_OPTION) {
					sonido.pulsarBoton();
					JOptionPane.showMessageDialog(null, "Has perdido el progreso, felicidades! :3");
					sonido.pulsarBoton();
					VolverMenu();
				}
			}
		});

		// boton de guardar partida
		JButton Guardar = new JButton("Guardar partida");
		Guardar.setBounds(730, 630, 150, 60);
		Guardar.setBackground(new Color(72, 72, 72));
		Guardar.setForeground(new Color(255, 255, 255));
		Guardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(Guardar);
		Guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.pulsarBoton();
				conexionBD.guardarPartida();
				JOptionPane.showMessageDialog(null, "Se ha guardado la partida.");
				sonido.pulsarBoton();
			}
		});

		// boton de volver
		JButton Volver = new JButton("Volver");
		Volver.setBounds(1200, 630, 150, 60);
		Volver.setBackground(new Color(72, 72, 72));
		Volver.setForeground(new Color(255, 255, 255));
		Volver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(Volver);
		Volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.pulsarBoton();
				VolverJuego();
			}
		});

		// imagen semitransparente para estetica
		ImageIcon Imagen = new ImageIcon("transparente2.png");
		JLabel transparente2 = new JLabel(Imagen);
		transparente2.setBounds(0, 0, 1550, 850);
		add(transparente2);

		// imagen de fondo(mapa)
		Imagen = new ImageIcon("mapa_mundo.png");
		// cambia las dimensiones de la imagen
		Image image = Imagen.getImage();
		image = image.getScaledInstance(1550, 850, java.awt.Image.SCALE_SMOOTH);
		Imagen = new ImageIcon(image);
		setLayout(null);
		JLabel MapaMundi = new JLabel(Imagen);
		MapaMundi.setBounds(0, 0, 1550, 850);
		add(MapaMundi);
	}

	// ---------------------------
	// vuelve al tablero de juego
	// ---------------------------
	/**
	 * Vuelve al tablero de juego.
	 */
	void VolverJuego() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.add(new PanelTablero());
		menu.repaint();
	}

	// -------------------------
	// vuelve al menu principal
	// -------------------------
	/**
	 * Vuelve al menu princiapl del juego.
	 */
	void VolverMenu() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.add(new PanelMenu());
		menu.repaint();
	}
}
