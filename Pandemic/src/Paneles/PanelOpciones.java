package Paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class PanelOpciones extends JPanel {
	JLabel PuntoCiudad;

	public PanelOpciones() {
		setBounds(0, 0, 1550, 850);
		setBackground(new Color(6, 153, 209));
		setLayout(null);

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
}
