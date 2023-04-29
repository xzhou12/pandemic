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

public class PanelNuevaPartida extends JPanel {
	JTextField NombreUsuario;
	JButton IniciarPartida;
	JLabel LabelNombre;
	JLabel LimagenNueva;

	public PanelNuevaPartida() {
		// panel para nueva partida
		setBounds(0, 0, 1550, 850);
		setBackground(new Color(6, 153, 209));
		setLayout(null);

		NombreUsuario = new JTextField();
		NombreUsuario.setForeground(new Color(255, 255, 255));
		NombreUsuario.setBackground(new Color(192, 192, 192));
		NombreUsuario.setBounds(670, 250, 180, 30);
		NombreUsuario.setColumns(10);
		add(NombreUsuario);

		IniciarPartida = new JButton("Iniciar Partida");
		IniciarPartida.setBackground(new Color(128, 128, 128));
		IniciarPartida.setForeground(new Color(255, 255, 255));
		IniciarPartida.setHideActionText(true);
		IniciarPartida.setBounds(670, 340, 180, 40);
		add(IniciarPartida);

		LabelNombre = new JLabel("Introduze tu nombre");
		LabelNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
		LabelNombre.setForeground(new Color(255, 255, 255));
		LabelNombre.setBounds(660, 170, 250, 40);
		add(LabelNombre);

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
				String linea = NombreUsuario.getText();
				if (linea.length() < 3 || linea.length() > 50) {
					JOptionPane.showMessageDialog(null, "Introduce un nombre de entre 3 y 50 caracteres.");
				} else {
					if (conexionBD.comprobarUsuario(linea)) {
						conexionBD.guardarUsuario(linea);
						iniciarJuego();
					}
				}
				NombreUsuario.setText("");
			}
		});
	}

	public void iniciarJuego() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.add(new tablero());
		menu.repaint();
	}
}
