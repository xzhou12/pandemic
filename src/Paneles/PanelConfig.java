package Paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import backend.*;

/**
* Panel de configuracion de dificultad y volumen de juego.
* @author Albert Barrachina
* @version 1.0
*/

public class PanelConfig extends JPanel {

	JLabel MapaMundi;
	public static int difficultad = 2;

	public PanelConfig() {
		setBounds(0, 0, 1550, 850);
		setBackground(new Color(6, 153, 209));
		setLayout(null);
		// boton para volver al menu principal
		JButton VolverConf = new JButton();
		VolverConf.setBackground(new Color(72, 72, 72));
		VolverConf.setForeground(new Color(255, 255, 255));
		VolverConf.setText("Volver");
		VolverConf.setBounds(20, 20, 100, 40);
		add(VolverConf);
		// lable de dificultad
		JLabel DificultadLable = new JLabel("DIFICULTAD");
		DificultadLable.setForeground(new Color(255, 255, 255));
		DificultadLable.setFont(new Font("Tahoma", Font.PLAIN, 32));
		DificultadLable.setBounds(670, 100, 180, 40);
		add(DificultadLable);
		// difficultad facil
		JButton FACIL = new JButton();
		FACIL.setBackground(new Color(72, 72, 72));
		FACIL.setForeground(new Color(255, 255, 255));
		FACIL.setText("FACIL");
		FACIL.setBounds(670, 170, 180, 40);
		add(FACIL);
		// dificultad normal
		JButton NORMAL = new JButton();
		NORMAL.setBackground(new Color(72, 72, 72));
		NORMAL.setForeground(new Color(255, 255, 255));
		NORMAL.setText("NORMAL");
		NORMAL.setBounds(670, 230, 180, 40);
		add(NORMAL);
		// dificultad dificil
		JButton DIFICIL = new JButton();
		DIFICIL.setBackground(new Color(72, 72, 72));
		DIFICIL.setForeground(new Color(255, 255, 255));
		DIFICIL.setText("DIFICIL");
		DIFICIL.setBounds(670, 290, 180, 40);
		add(DIFICIL);
		// lable de volumen
		JLabel VolumenLable = new JLabel("VOLUMEN");
		VolumenLable.setForeground(new Color(255, 255, 255));
		VolumenLable.setFont(new Font("Tahoma", Font.PLAIN, 32));
		VolumenLable.setBounds(670, 530, 180, 40);
		add(VolumenLable);
		// lable de volumen SFX
		JLabel VolumenSFXLable = new JLabel("SFX");
		VolumenSFXLable.setForeground(new Color(255, 255, 255));
		VolumenSFXLable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		VolumenSFXLable.setBounds(530, 690, 100, 40);
		add(VolumenSFXLable);
		// slider de SFX
		JSlider sliderSFX = new JSlider();
		sliderSFX.setOpaque(false);
		sliderSFX.setBounds(660, 690, 200, 40);
		sliderSFX.setValue((int) sonido.sonidoSFX);
		add(sliderSFX);
		// imagen trasparente de fondo para efecto de menu
		ImageIcon imagen = new ImageIcon("transparente.png");
		Image image = imagen.getImage();
		image = image.getScaledInstance(1500, 800, java.awt.Image.SCALE_SMOOTH);
		imagen = new ImageIcon(image);
		JLabel ConfigBoxImage = new JLabel(imagen);
		ConfigBoxImage.setBounds(15, 15, 1500, 780);
		add(ConfigBoxImage);

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

		// configuracion
		VolverConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.pulsarBoton();
				volver();
			}
		});

		FACIL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.pulsarBoton();
				SetDificultad(1);
				JOptionPane.showMessageDialog(null,
						"Infecciones primera ronda: 5\nInfecciones por ronda: 4\nMax. ciudades activas : 48\nMax. brotes: 12");
				sonido.pulsarBoton();
			}
		});
		NORMAL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.pulsarBoton();
				SetDificultad(2);
				JOptionPane.showMessageDialog(null,
						"Infecciones primera ronda: 8\nInfecciones por ronda: 4\nMax. ciudades activas : 43\nMax. brotes: 9");
				sonido.pulsarBoton();
			}
		});
		DIFICIL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.pulsarBoton();
				SetDificultad(3);
				JOptionPane.showMessageDialog(null,
						"Infecciones primera ronda: 10\nInfecciones por ronda: 6\nMax. ciudades activas : 40\nMax. brotes: 7");
				sonido.pulsarBoton();
			}
		});
		sliderSFX.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					sonido.sonidoSFX = source.getValue();
					sonido.pulsarBoton();
				}
			}
		});
	}

	// ------------------------
	// establece la dificultad
	// ------------------------
	/**
	* Establece la difucultad del juego, cambiando parametros y cambiando el texto indicativo de esta en nueva partida.
	* @param Dificultad numero del 1 al 3 que indica la dificultad.
	*/
	public static void SetDificultad(int Dificultad) {
		File archivo = new File("parametros.xml");
		if (Dificultad == 1) {
			parametros.actualizarValor(archivo, 5, 4, 48, 12);
			PanelNuevaPartida.dificultadString = "Facil";
			difficultad = 1;
		} else if (Dificultad == 2) {
			parametros.actualizarValor(archivo, 8, 4, 43, 9);
			PanelNuevaPartida.dificultadString = "Normal";
			difficultad = 2;
		} else if (Dificultad == 3) {
			parametros.actualizarValor(archivo, 10, 6, 40, 7);
			PanelNuevaPartida.dificultadString = "Dificil";
			difficultad = 3;
		}
	}

	// ---------------------------
	// vuelve al menu principal
	// ---------------------------
	/**
	* Vuelve al menu principal del juego.
	*/
	private void volver() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.add(new PanelMenu());
		menu.repaint();
	}
}
