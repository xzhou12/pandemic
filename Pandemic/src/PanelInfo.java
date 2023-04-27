import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class PanelInfo extends JPanel {
	JButton VolverMenu;
	static JTextPane textMenu;
	JLabel TransparenteLabel;
	JLabel MapaMundi;

	public PanelInfo() {
		// pnael para poner texto (texto de informacion y boton
		// volver)---------------------------------
		setBounds(0, 0, 1550, 850);
		setBackground(new Color(6, 153, 209));
		setLayout(null);
		// botn de volver
		VolverMenu = new JButton();
		VolverMenu.setText("Volver");
		VolverMenu.setBounds(710, 730, 100, 40);
		add(VolverMenu);
		// texto usado en botones autores, informacion y varsion
		textMenu = new JTextPane();
		textMenu.setForeground(new Color(238, 238, 238));
		textMenu.setOpaque(false);
		getTexto();
		textMenu.setFont(
				new Font("Constantia", textMenu.getFont().getStyle() | Font.BOLD, textMenu.getFont().getSize() + 5));
		textMenu.setEditable(false);
		textMenu.setBounds(500, 200, 500, 400);
		StyledDocument doc = textMenu.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		add(textMenu);
		// marco translucido
		ImageIcon imagen = new ImageIcon("transparente.png");
		Image image = imagen.getImage();
		image = image.getScaledInstance(500, 400, java.awt.Image.SCALE_SMOOTH);
		imagen = new ImageIcon(image);
		TransparenteLabel = new JLabel(imagen);
		TransparenteLabel.setBounds(500, 200, 500, 400);
		add(TransparenteLabel);

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
		
		VolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textMenu.setText("");
				VolverPanelMenu();
			}
		});
	}

	void VolverPanelMenu() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.add(new PanelMenu());
		menu.repaint();
	}

	static void setTexto(String texto) {
		try {
			FileWriter fw = new FileWriter("Info.txt", false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(texto);
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Error inesperado!! :(" + e);
		}

	}

	static void getTexto() {
		String textoArchivo = "";
		String texto = "";
		try {
			FileReader fr = new FileReader("Info.txt");
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
		textMenu.setText(texto);
	}

}
