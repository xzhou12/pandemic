package Paneles;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import backend.*;
import java.awt.Font;

public class PanelTablero extends JPanel {
	private static int[][] coords = new int[48][3];
	static String[] nombres = new String[48];
	private static int numeroCiudad;
	private static int[] Porcentajes = new int[4];
	private static JProgressBar porcentajeA, porcentajeB, porcentajeG, porcentajeD, porcentajeBrotes;
	JLabel PuntoCiudad, noticias, brotes, OptionLabel;

	public PanelTablero() {
		setBounds(0, 0, 1550, 850);
		setBackground(new Color(6, 153, 209));
		setLayout(null);

		backend.jugar.Main();
		// boton de opciones (guardar partida/salir del juego)
		ImageIcon imagen = new ImageIcon("config.png");
		Image image = imagen.getImage();
		image = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		imagen = new ImageIcon(image);
		OptionLabel = new JLabel(imagen);
		OptionLabel.setBounds(20, 20, 45, 45);
		add(OptionLabel);
		// tabla de porcentajes y numero de brotes
		// porcentajes
		// alfa
		porcentajeA = new JProgressBar();
		porcentajeA.setStringPainted(true);
		porcentajeA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		porcentajeA.setForeground(new Color(128, 128, 128));
		porcentajeA.setBounds(10, 620, 200, 30);
		add(porcentajeA);
		// beta
		porcentajeB = new JProgressBar();
		porcentajeB.setForeground(new Color(128, 128, 128));
		porcentajeB.setStringPainted(true);
		porcentajeB.setLocation(10, 670);
		porcentajeB.setSize(200, 30);
		add(porcentajeB);
		// gama
		porcentajeG = new JProgressBar();
		porcentajeG.setForeground(new Color(128, 128, 128));
		porcentajeG.setStringPainted(true);
		porcentajeG.setLocation(10, 720);
		porcentajeG.setSize(200, 30);
		add(porcentajeG);
		// delta
		porcentajeD = new JProgressBar();
		porcentajeD.setForeground(new Color(128, 128, 128));
		porcentajeD.setStringPainted(true);
		porcentajeD.setLocation(10, 770);
		porcentajeD.setSize(200, 30);
		add(porcentajeD);
		// brotes
		brotes = new JLabel();
		brotes.setText("\n\n  4/6");
		brotes.setBounds(250, 620, 30, 180);
		add(brotes);
		porcentajeBrotes = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
		porcentajeBrotes.setForeground(new Color(255, 78, 78));
		porcentajeBrotes.setBounds(250, 620, 30, 180);
		add(porcentajeBrotes);
		// establece los porcentajes y los textos de dentro de los JProgressBar
		porcentajes();
		// imagen para representar una ciudad
		ImageIcon bola10 = new ImageIcon("puntoAlfa0.png");
		ImageIcon bola20 = new ImageIcon("puntoBeta0.png");
		ImageIcon bola30 = new ImageIcon("puntoGama0.png");
		ImageIcon bola40 = new ImageIcon("puntoDelta0.png");
		// guarda los nombres y coordenadas de las ciudades en dos arrays que se han
		// creado antes
		getCiudades();

		// bucle para crear todos los iconos de las ciudades
		for (int i = 0; i < 48; i++) {
			if (coords[i][2] == 0) {
				PuntoCiudad = new JLabel(bola10);
			} else if (coords[i][2] == 1) {
				PuntoCiudad = new JLabel(bola20);
			} else if (coords[i][2] == 2) {
				PuntoCiudad = new JLabel(bola30);
			} else if (coords[i][2] == 3) {
				PuntoCiudad = new JLabel(bola40);
			}
			PuntoCiudad.setBounds(coords[i][0], coords[i][1], 15, 15);
			PuntoCiudad.setToolTipText(nombres[i]);
			add(PuntoCiudad);
			PuntoCiudad.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					numeroCiudad = 0;
					// obtiene la id de la ciudad
					for (int j = 0; j < nombres.length; j++) {
						if (nombres[j].equals(((JLabel) e.getSource()).getToolTipText())) {
							numeroCiudad = j;
							j = nombres.length;
						}
					}
					// abre el panel de informacion de la ciudad encontrada
					MostrarInfo();
				}
			});
		}
		// texto de noticias(animado de derecha a izquierda)
		noticias = new JLabel();
		noticias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		noticias.setText(
				"¡Pulsa en uno de los puntos para ver la informacion de la ciudad||Al pasar el raton por encima de una ciudad esta te mostrara su nombre||Noticia: Asia tiene demasiadas ciudades, obten las vacunas antes de que sea demasiado tarde!");
		noticias.setBounds(1550, 0, 1550, 20);
		add(noticias);
		TextoAnimado();

		// imagen de fondo(conexiones)
		ImageIcon Imagen = new ImageIcon("conexiones.png");
		JLabel Conexiones = new JLabel(Imagen);
		Conexiones.setBounds(-10, -20, 1550, 850);
		add(Conexiones);

		// imagen de fondo(mapa)
		Imagen = new ImageIcon("mapa_mundo.png");
		// cambia las dimensiones de la imagen
		image = Imagen.getImage();
		image = image.getScaledInstance(1550, 850, java.awt.Image.SCALE_SMOOTH);
		Imagen = new ImageIcon(image);
		setLayout(null);
		JLabel MapaMundi = new JLabel(Imagen);
		MapaMundi.setBounds(0, 0, 1550, 850);
		add(MapaMundi);

		OptionLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MostrarOpciones();
			}
		});
	}

	// -----------------------------------------------------------------
	// Obtiene los nombres y las coordenadas de todas las ciudades (48)
	// -----------------------------------------------------------------
	static void getCiudades() {
		String s;
		String[] lineas = new String[48];
		String[][] temp = new String[1][2];
		String[][] ciudades = new String[48][3];
		int i = 0;
		try {
			FileReader fr = new FileReader("ciudades.txt");
			BufferedReader br = new BufferedReader(fr);
			do {
				s = br.readLine();
				if (s != null) {
					lineas[i] = s;
					ciudades[i] = lineas[i].split(";");
					nombres[i] = ciudades[i][0];
					temp[0] = ciudades[i][2].split(",");
					coords[i][0] = Integer.valueOf(temp[0][0]);
					coords[i][1] = Integer.valueOf(temp[0][1]);
					coords[i][2] = Integer.valueOf(ciudades[i][1]);
					i++;
				}
			} while (s != null);
			fr.close();
			br.close();

		} catch (IOException e) {
			System.exit(0);
		}
	}

	// --------------------------------------------------------------------
	// cambia al panel de informacion de las cudades con las opciones de
	// cura/investigacion de la ciudad que se ha seleccionado
	// --------------------------------------------------------------------
	public void MostrarInfo() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.getContentPane().add(new PanelCiudad(numeroCiudad));
		menu.repaint();
	}

	// --------------------------------------------------------------------
	// cambia al panel de informacion de las cudades con las opciones de
	// cura/investigacion de la ciudad que se ha seleccionado
	// --------------------------------------------------------------------
	public void MostrarOpciones() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.getContentPane().add(new PanelOpciones());
		menu.repaint();
	}

	public static void mostrarInfecciones(ArrayList<String> ciudadesAfectadas) {
		JOptionPane.showMessageDialog(null, "CIUDADES INFECTADAS:\n" + ciudadesAfectadas);
	}

	// obtine los porcentajes y los actualiza
	static void porcentajes() {
//		porcentaje = vacunas.getPorcentajes(backend.jugar.vacunasCura);
//		for (int i = 1; i < 5; i++) {
//			System.out.println(porcentaje[i]);
//		}

		/// ------------------------------------------------------------------------------------
		// este for es solo temporal!!! cuando se pueda cambiar para que lea el
		// orcentaje real esto se va fuera!!!
		// -------------------------------------------------------------------------------------
		for (int i = 0; i < 4; i++) {
			Porcentajes[i] = Integer.parseInt((String) jugar.vacunasCura.get(i).get(1));
		}
		// vacunas
		porcentajeA.setString(
				(String) jugar.vacunasCura.get(0).get(0) + ": " + (String) jugar.vacunasCura.get(0).get(1) + "%");
		porcentajeA.setValue(Porcentajes[0]);
		porcentajeB.setString(
				(String) jugar.vacunasCura.get(1).get(0) + ": " + (String) jugar.vacunasCura.get(1).get(1) + "%");
		porcentajeB.setValue(Porcentajes[1]);
		porcentajeG.setString(
				(String) jugar.vacunasCura.get(2).get(0) + ": " + (String) jugar.vacunasCura.get(2).get(1) + "%");
		porcentajeG.setValue(Porcentajes[2]);
		porcentajeD.setString(
				(String) jugar.vacunasCura.get(3).get(0) + ": " + (String) jugar.vacunasCura.get(3).get(1) + "%");
		porcentajeD.setValue(Porcentajes[3]);
		// brotes
		porcentajeBrotes.setValue(67);
	}

	// ---------------------------------------------------------------------------
	// mueve el texto de noticias de izquieda a derecha constantemente, cuando
	// desaparece por completo vuelve a empezar
	// ---------------------------------------------------------------------------
	public void TextoAnimado() {

		Thread animationThread = new Thread(() -> {
			while (true) {
				int x = noticias.getLocation().x - 1;
				if (x < -1550) {
					x = 1550;
				}
				noticias.setLocation(x, 0);
				try {
					Thread.sleep(16);
				} catch (InterruptedException e) {
					break;
				}
			}
		});
		animationThread.start();
	}

}
