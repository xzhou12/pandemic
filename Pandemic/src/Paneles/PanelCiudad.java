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
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

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
//				accion.curarCiudades(accion.broteCiudades, ciudades[numeroCiudad]);
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
//				accion.inversigarCura(accion.vacunasCura, numero);
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
		getText();
		setText();
		add(bloqueTexto);
		// imagen semitransparente para estetica
		ImageIcon Imagen = new ImageIcon("transparente2.png");
		JLabel test = new JLabel(Imagen);
		test.setBounds(0, 0, 1550, 850);
		add(test);
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
	static void setText() {
		bloqueTexto.setText(ciudades[numeroCiudad] + "\n\n\nEnfermedad principal: "
				+ "\n\nNivel de enfermedad de la ciudad: " + "\n\n\n\n\n\t\t\t\t\t\t       Que quieres hacer?");
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
}
