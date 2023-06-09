package Paneles;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import backend.conexionBD;
import backend.sonido;

import java.awt.Dimension;

/**
* Ranking con de las partidas acabadas, separado por dificultades.
* @author Albert Barrachina
* @version 1.0
*/

public class PanelRanking extends JPanel {

	public PanelRanking() {
		setBounds(0, 0, 1550, 850);
		setBackground(new Color(6, 153, 209));
		setLayout(null);

		// boton para volver al menu principal
		JButton VolverConf = new JButton();
		VolverConf.setBackground(new Color(72, 72, 72));
		VolverConf.setForeground(new Color(255, 255, 255));
		VolverConf.setText("Volver");
		VolverConf.setBounds(30, 30, 100, 40);
		add(VolverConf);
		// scrollpane para que se autoextenda
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(410, 100, 700, 600);
		scrollPane.getViewport().setViewPosition(new Point(0, 0));
		add(scrollPane);

		// aqui van los generados
		// preparaciones para el textpanel
		ArrayList<ArrayList> ranking = new ArrayList<ArrayList>();
		String puntuaciones = "";
		// si la dificlutad esta en dificil muestra el ranking de partidas en dificil
		if (PanelConfig.difficultad == 3) {
			ranking = conexionBD.cargarRanking();
			puntuaciones = "----------Dificil----------";
			// si la dificlutad esta en normal muestra el ranking de partidas en normal
		} else if (PanelConfig.difficultad == 2) {
			ranking = conexionBD.cargarRanking();
			puntuaciones = "----------Normal----------";
			// si la dificlutad esta en facil muestra el ranking de partidas en facil
		} else if (PanelConfig.difficultad == 1) {
			ranking = conexionBD.cargarRanking();
			puntuaciones = "----------Facil----------";
		}
		for (int i = 0; i < ranking.size(); i++) {
			puntuaciones += "\n" + (i + 1) + ":\t" + ((String) ranking.get(i).get(0)) + " : "
					+ ((String) ranking.get(i).get(1)) + "\n";
		}

		// textpanel con las puntuaciones
		JTextPane posicion = new JTextPane();
		posicion.setForeground(new Color(255, 255, 255));
		posicion.setOpaque(false);
		posicion.setFocusable(false);
		posicion.setText("VICTORIAS: \n" + puntuaciones);
		posicion.setFont(new Font("Constantia", Font.BOLD, 25));
		posicion.setEditable(false);
		posicion.setBounds(500, 200, 500, 400);
		StyledDocument doc = posicion.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		posicion.setCaretPosition(0);
		scrollPane.setViewportView(posicion);

		// imagen trasparente de fondo para efecto de menu
		ImageIcon imagen = new ImageIcon("transparente.png");
		Image image = imagen.getImage();
		image = image.getScaledInstance(1500, 800, java.awt.Image.SCALE_SMOOTH);
		imagen = new ImageIcon(image);
		JLabel transparente = new JLabel(imagen);
		transparente.setBounds(410, 100, 700, 600);
		add(transparente);
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

		VolverConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.pulsarBoton();
				volver();
			}
		});
	}

	// ------------------------
	// vuelve al menu principal
	// ------------------------
	/**
	* Vuelve al menu principal del juego.
	*/
	private void volver() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.getContentPane().add(new PanelMenu());
		menu.repaint();
	}
}
