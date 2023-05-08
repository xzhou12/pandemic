package Paneles;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
	private static boolean animacion;
	private static int[][] coords = new int[48][3];
	static String[] nombres = new String[48];
	private static int numeroCiudad;
	private static int[] Porcentajes = new int[4];
	private static JProgressBar porcentajeA, porcentajeB, porcentajeG, porcentajeD, porcentajeBrotes;
	private static JLabel PuntoCiudad, noticias, brotes, OptionLabel, ronda, LporcentajeA, LporcentajeB, LporcentajeD,
			LporcentajeG, enfermedadesActivas;

	public PanelTablero() {
		setBounds(0, 0, 1550, 850);
		setBackground(new Color(6, 153, 209));
		setLayout(null);
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
		// texto alfa
		LporcentajeA = new JLabel();
		LporcentajeA.setForeground(new Color(0, 0, 0));
		LporcentajeA.setBounds(90, 620, 200, 30);
		add(LporcentajeA);
		// alfa
		porcentajeA = new JProgressBar();
		porcentajeA.setForeground(new Color(32, 160, 224));
		porcentajeA.setBorder(BorderFactory.createLineBorder(Color.black));
		porcentajeA.setBounds(10, 620, 200, 30);
		add(porcentajeA);
		// texto beta
		LporcentajeB = new JLabel();
		LporcentajeB.setForeground(new Color(0, 0, 0));
		LporcentajeB.setBounds(90, 670, 200, 30);
		add(LporcentajeB);
		// beta
		porcentajeB = new JProgressBar();
		porcentajeB.setForeground(new Color(240, 62, 70));
		porcentajeB.setBorder(BorderFactory.createLineBorder(Color.black));
		porcentajeB.setBounds(10, 670, 200, 30);
		add(porcentajeB);
		// textogama
		LporcentajeG = new JLabel();
		LporcentajeG.setForeground(new Color(0, 0, 0));
		LporcentajeG.setBounds(90, 720, 200, 30);
		add(LporcentajeG);
		// gama
		porcentajeG = new JProgressBar();
		porcentajeG.setForeground(new Color(35, 184, 31));
		porcentajeG.setBorder(BorderFactory.createLineBorder(Color.black));
		porcentajeG.setBounds(10, 720, 200, 30);
		add(porcentajeG);
		// texto delta
		LporcentajeD = new JLabel();
		LporcentajeD.setForeground(new Color(0, 0, 0));
		LporcentajeD.setBounds(90, 770, 200, 30);
		add(LporcentajeD);
		// delta
		porcentajeD = new JProgressBar();
		porcentajeD.setForeground(new Color(253, 240, 0));
		porcentajeD.setBorder(BorderFactory.createLineBorder(Color.black));
		porcentajeD.setBounds(10, 770, 200, 30);
		add(porcentajeD);
		// enfermedades activas
		String[] parametro = new String[4];
		parametro = parametros.leerArchivo();
		enfermedadesActivas = new JLabel();
		enfermedadesActivas.setBounds(32, 585, 200, 30);
		enfermedadesActivas.setText("Enfermedades activas: " + IA.enfermedadesActivas() + "/" + parametro[2]);
		add(enfermedadesActivas);
		// brotes
		brotes = new JLabel();
		brotes.setBounds(220, 620, 50, 180);
		add(brotes);
		porcentajeBrotes = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
		porcentajeBrotes.setBorder(BorderFactory.createLineBorder(Color.black));
		porcentajeBrotes.setForeground(new Color(255, 78, 78));
		porcentajeBrotes.setBounds(230, 620, 40, 180);
		add(porcentajeBrotes);
		// establece los porcentajes y los textos de dentro de los JProgressBar
		porcentajes();
		// imagen para representar una ciudad
		ImageIcon bola10 = new ImageIcon("puntoAlfa0.png");
		ImageIcon bola20 = new ImageIcon("puntoBeta0.png");
		ImageIcon bola30 = new ImageIcon("puntoGama0.png");
		ImageIcon bola40 = new ImageIcon("puntoDelta0.png");
		ImageIcon bola11 = new ImageIcon("puntoAlfa1.png");
		ImageIcon bola21 = new ImageIcon("puntoBeta1.png");
		ImageIcon bola31 = new ImageIcon("puntoGama1.png");
		ImageIcon bola41 = new ImageIcon("puntoDelta1.png");
		ImageIcon bola12 = new ImageIcon("puntoAlfa2.png");
		ImageIcon bola22 = new ImageIcon("puntoBeta2.png");
		ImageIcon bola32 = new ImageIcon("puntoGama2.png");
		ImageIcon bola42 = new ImageIcon("puntoDelta2.png");
		ImageIcon bola13 = new ImageIcon("puntoAlfa3.png");
		ImageIcon bola23 = new ImageIcon("puntoBeta3.png");
		ImageIcon bola33 = new ImageIcon("puntoGama3.png");
		ImageIcon bola43 = new ImageIcon("puntoDelta3.png");
		// guarda los nombres y coordenadas de las ciudades en dos arrays que se han
		// creado antes
		getCiudades();

		// bucle para crear todos los iconos de las ciudades
		for (int i = 0; i < 48; i++) {
			// establece el icono de la ciudad
			if (coords[i][2] == 0) {
				if (jugar.nivelBroteCiudades.get(i).get(1).equals("0")) {
					PuntoCiudad = new JLabel(bola10);
				} else if (jugar.nivelBroteCiudades.get(i).get(1).equals("1")) {
					PuntoCiudad = new JLabel(bola11);
				} else if (jugar.nivelBroteCiudades.get(i).get(1).equals("2")) {
					PuntoCiudad = new JLabel(bola12);
				} else {
					PuntoCiudad = new JLabel(bola13);
				}
			} else if (coords[i][2] == 1) {
				if (jugar.nivelBroteCiudades.get(i).get(1).equals("0")) {
					PuntoCiudad = new JLabel(bola20);
				} else if (jugar.nivelBroteCiudades.get(i).get(1).equals("1")) {
					PuntoCiudad = new JLabel(bola21);
				} else if (jugar.nivelBroteCiudades.get(i).get(1).equals("2")) {
					PuntoCiudad = new JLabel(bola22);
				} else {
					PuntoCiudad = new JLabel(bola23);
				}
			} else if (coords[i][2] == 2) {
				if (jugar.nivelBroteCiudades.get(i).get(1).equals("0")) {
					PuntoCiudad = new JLabel(bola30);
				} else if (jugar.nivelBroteCiudades.get(i).get(1).equals("1")) {
					PuntoCiudad = new JLabel(bola31);
				} else if (jugar.nivelBroteCiudades.get(i).get(1).equals("2")) {
					PuntoCiudad = new JLabel(bola32);
				} else {
					PuntoCiudad = new JLabel(bola33);
				}
			} else if (coords[i][2] == 3) {
				if (jugar.nivelBroteCiudades.get(i).get(1).equals("0")) {
					PuntoCiudad = new JLabel(bola40);
				} else if (jugar.nivelBroteCiudades.get(i).get(1).equals("1")) {
					PuntoCiudad = new JLabel(bola41);
				} else if (jugar.nivelBroteCiudades.get(i).get(1).equals("2")) {
					PuntoCiudad = new JLabel(bola42);
				} else {
					PuntoCiudad = new JLabel(bola43);
				}
			}
			// establece las coordenadas y las dimensiones de la ciudad
			PuntoCiudad.setBounds(coords[i][0], coords[i][1], 15, 15);
			PuntoCiudad.setToolTipText(nombres[i]);
			add(PuntoCiudad);
			PuntoCiudad.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					sonido.pulsarBoton();
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
		if (jugar.rondas == 0) {
			TextoAnimado();
		}

		// texto con el numero de la ronda
		ronda = new JLabel();
		ronda.setFont(new Font("Tahoma", Font.BOLD, 20));
		ronda.setText("Ronda: " + (jugar.rondas + 1));
		ronda.setBounds(1240, 700, 150, 30);
		add(ronda);

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
				sonido.pulsarBoton();
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
		menu.add(new PanelCiudad(numeroCiudad));
		menu.repaint();
	}

	// --------------------------------------------------------------------
	// cambia al panel de informacion de las cudades con las opciones de
	// cura/investigacion de la ciudad que se ha seleccionado
	// --------------------------------------------------------------------
	public void MostrarOpciones() {
		JFrame menu = (JFrame) SwingUtilities.getWindowAncestor(this);
		menu.remove(this);
		menu.add(new PanelOpciones());
		menu.repaint();
	}

	// --------------------------------------------------------------------
	// Muestra por pantalla con un emergente que ciudades se han innfectado
	// --------------------------------------------------------------------
	public static void mostrarInfecciones(ArrayList<String> ciudadesAfectadas) {
		JOptionPane.showMessageDialog(null, "CIUDADES INFECTADAS:\n" + ciudadesAfectadas);
		sonido.pulsarBoton();
	}

	// -------------------------------------------------------------------------------------------
	// obtine la informacion de porcentaje, brotes, ronda y enfermedades activas y
	// los actualiza
	// -------------------------------------------------------------------------------------------
	static void porcentajes() {
		for (int i = 0; i < 4; i++) {
			Porcentajes[i] = Integer.parseInt((String) jugar.vacunasCura.get(i).get(1));
		}
		// vacunas
		LporcentajeA.setText(
				(String) jugar.vacunasCura.get(0).get(0) + ": " + (String) jugar.vacunasCura.get(0).get(1) + "%");
		porcentajeA.setValue(Porcentajes[0]);
		LporcentajeB.setText(
				(String) jugar.vacunasCura.get(1).get(0) + ": " + (String) jugar.vacunasCura.get(1).get(1) + "%");
		porcentajeB.setValue(Porcentajes[1]);
		LporcentajeG.setText(
				(String) jugar.vacunasCura.get(2).get(0) + ": " + (String) jugar.vacunasCura.get(2).get(1) + "%");
		porcentajeG.setValue(Porcentajes[2]);
		LporcentajeD.setText(
				(String) jugar.vacunasCura.get(3).get(0) + ": " + (String) jugar.vacunasCura.get(3).get(1) + "%");
		porcentajeD.setValue(Porcentajes[3]);
		// brotes
		String[] parametro = new String[4];
		parametro = parametros.leerArchivo();
		porcentajeBrotes.setValue((100 / Integer.valueOf(parametro[3]) * jugar.numBrotes));
		brotes.setText("\n\n    " + jugar.numBrotes + "/" + Integer.valueOf(parametro[3]));
	}

	// ---------------------------------------------------------------------------
	// mueve el texto de noticias de izquieda a derecha constantemente, cuando
	// desaparece por completo vuelve a empezar
	// ---------------------------------------------------------------------------
	public static void TextoAnimado() {
		if (!animacion) {
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
			animacion = true;
		}
	}

}
