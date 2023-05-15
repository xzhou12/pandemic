package Paneles;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import backend.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCiudad extends JPanel {
	static String[] ciudades = new String[48];
	static JTextPane bloqueTexto;
	static int numeroCiudad;

	public PanelCiudad(int numero) {
		numeroCiudad = numero;
		setBounds(0, 0, 1550, 850);
		setBackground(new Color(6, 153, 209));
		setLayout(null);

		// boton de curar ciudad
		JButton curar = new JButton("Curar");
		curar.setBounds(250, 630, 150, 60);
		curar.setBackground(new Color(72, 72, 72));
		curar.setForeground(new Color(255, 255, 255));
		curar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(curar);
		curar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// si quedan mas de 1 accion
				if (jugar.acciones > 0) {
					int nivelBrote = Integer.parseInt((String) jugar.nivelBroteCiudades.get(numero).get(1));
					// si se puede curar(mas de 1 enfermedad)
					if (nivelBrote >= 1) {
						sonido.pulsarCurar();
						jugar.acciones--;
						accion.curar(numero, PanelTablero.nombres[numeroCiudad]);
						// si se ha ganado la partida
						if (jugar.comprobarVictoria()) {
							conexionBD.guardarPartidaAcabada(PanelConfig.difficultad);
							sonido.sonidaVictoria();
							JOptionPane.showMessageDialog(null, "¡Victoria! has curado a todo el mundo!");
							VolverMenu();
							sonido.pulsarBoton();
							// el tablero genera mas enfermedades y se actualiza el mapa
						} else {
							jugar.Main();
							// si al pasar esto has perdido
							if (jugar.comprobarDerrota()) {
								conexionBD.guardarPartidaAcabada(PanelConfig.difficultad);
								sonido.sonidaDerrota();
								VolverMenu();
							} // si ni has ganado ni perdido (sigues jugando)
							else if (jugar.comprobarDerrota() == false && jugar.comprobarVictoria() == false) {
								VolverJuego();
							}
						}
						// si la ciudad tiene nivel enfermedad 0
					} else {
						JOptionPane.showMessageDialog(null, "La ciudad ya esta curada! No se puede curar más");
						sonido.pulsarBoton();
					}
					// si no tienes suficientes acciones... Esto no tendria que ser posible pero
					// aqui esta, por si acaso? :/
				} else {
					JOptionPane.showMessageDialog(null,
							"No tienes suficientes acciones en este turno para curar.\nNecesarios: 1 Actuales: "
									+ jugar.acciones);
					sonido.pulsarBoton();
				}

			}
		});
		// boton de investigar ennfermedad
		JButton investigar = new JButton("Investigar");
		investigar.setBounds(730, 630, 150, 60);
		investigar.setBackground(new Color(72, 72, 72));
		investigar.setForeground(new Color(255, 255, 255));
		investigar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(investigar);

		investigar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// si tienes los cuatro puntos de accion
				if (jugar.acciones == 4) {
					sonido.pulsarInvestigar();
					jugar.acciones = 0;
					accion.investigar(numero, ciudades[numeroCiudad]);
					// si ganas la partida
					if (jugar.comprobarVictoria()) {
						conexionBD.guardarPartidaAcabada(PanelConfig.difficultad);
						sonido.sonidaVictoria();
						JOptionPane.showMessageDialog(null, "¡Victoria! has curado a todo el mundo!");
						VolverMenu();
						sonido.pulsarBoton();
					} else {
						// el tablero genera mas enfermedades y se actualiza el mapa
						jugar.Main();
						// si al pasar esto has perdido
						if (jugar.comprobarDerrota()) {
							conexionBD.guardarPartidaAcabada(PanelConfig.difficultad);
							sonido.sonidaDerrota();
							VolverMenu();
							// si ni has ganado ni perdido (sigues jugando)
						} else if (jugar.comprobarDerrota() == false && jugar.comprobarVictoria() == false) {
							VolverJuego();
						}
					}
					//si no tienes sufficientes puntos de accion
				} else {
					JOptionPane.showMessageDialog(null,
							"No tienes suficientes acciones en este turno para investigar.\nNecesarios: 4 Actuales: "
									+ jugar.acciones);
					sonido.pulsarBoton();
				}

			}
		});
		// boton de cancelar accion
		JButton cancelar = new JButton("Cancelar");
		cancelar.setBounds(1200, 630, 150, 60);
		cancelar.setBackground(new Color(72, 72, 72));
		cancelar.setForeground(new Color(255, 255, 255));
		cancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cancelar);
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.pulsarBoton();
				VolverJuego();
			}
		});
		// panel de teto para mostrar info de las ciudades y preguntar que quieres hcer
		// con la ciudad
		bloqueTexto = new JTextPane();
		bloqueTexto.setEditable(false);
		bloqueTexto.setOpaque(false);
		bloqueTexto.setForeground(new Color(255, 255, 255));
		bloqueTexto.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		bloqueTexto.setBounds(269, 140, 1000, 500);
		setText();
		add(bloqueTexto);
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

	// ----------------------------------------------------------
	// Obtiene la informacion de las ciudades(para el juagador)
	// ----------------------------------------------------------
	static void getText() {
		String textoArchivo = "";
		String texto = "";
		try {
			FileReader fr = new FileReader("ciudadesRedactadas.txt");
			BufferedReader br = new BufferedReader(fr);
			textoArchivo = br.readLine();
			while (textoArchivo != null) {
				texto = texto + "\n" + textoArchivo;
				textoArchivo = br.readLine();
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			if (textoArchivo == null) {
				texto = "Error al leer los archivos!!\nvuelve a instalar el juego!";
			}
		}
		ciudades = texto.split("/");
	}

	// ----------------------------------------------
	// establece el texto que se mostrra por pantalla
	// ----------------------------------------------
	public static void setText() {
		String ciudad = (String) jugar.nivelBroteCiudades.get(numeroCiudad).get(0);
		int codEnfermedad = vacunas.sacarEnfermedadCiudad(ciudad);
		String nivelBrote = (String) jugar.nivelBroteCiudades.get(numeroCiudad).get(1);
		String nombreEnfermedad = (String) jugar.vacunasCura.get(codEnfermedad).get(0);

		bloqueTexto.setText(ciudades[numeroCiudad] + "\n\n\nEnfermedad principal: " + nombreEnfermedad
				+ "\n\nNivel de enfermedad de la ciudad: " + nivelBrote
				+ "\n\n\n\n\n\t\t\t\t\t\t       Que quieres hacer?");
	}

	// ------------------
	// Vuelve al tablero
	// -------------------
	void VolverJuego() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.add(new PanelTablero());
		menu.repaint();
	}

	// -------------------------
	// vuelve al menu principal
	// -------------------------
	void VolverMenu() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.add(new PanelMenu());
		menu.repaint();
	}
}
