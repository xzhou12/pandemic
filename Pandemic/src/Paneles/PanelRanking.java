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
import java.awt.Dimension;

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
		ranking = backend.conexionBD.cargarRanking();
		for (int i = 0; i < ranking.size(); i++) {
			puntuaciones = puntuaciones + "\n\n" + (i+1)+":\t"+((String) ranking.get(i).get(0)) + " : "
					+ ((String) ranking.get(i).get(1)) + "\n";
		}
		// textpanel con las puntuaciones
		JTextPane posicion = new JTextPane();
		posicion.setForeground(new Color(255, 255, 255));
		posicion.setOpaque(false);
		posicion.setText(puntuaciones);
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
				volver();
			}
		});
	}

	private void volver() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.getContentPane().add(new PanelMenu());
		menu.repaint();
	}
}
