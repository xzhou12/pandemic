import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Font;

public class menu extends JFrame {
	// string para sobreescribir el panel de texto
	static String StringMenu;

	public menu() {
		setTitle("Pandemic");
		ImageIcon img = new ImageIcon("Pandemic.png");
		setIconImage(img.getImage());
		setBounds(100, 100, 1550, 850);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// panel del menu(fondo menu)-----------------------------------
		JPanel Pciudades = new JPanel();
		Pciudades.setBounds(0, 0, 1550, 850);
		Pciudades.setBackground(new Color(6, 153, 209));
		Pciudades.setLayout(null);

		// imagen de fondo(mapa)
		ImageIcon Imagen = new ImageIcon("mapa_mundo.png");
		// cambia las dimensiones de la imagen
		Image image = Imagen.getImage();
		Image newimg = image.getScaledInstance(1550, 850, java.awt.Image.SCALE_SMOOTH);
		Imagen = new ImageIcon(newimg);
		JLabel MapaMundi = new JLabel(Imagen);
		MapaMundi.setFocusable(false);
		MapaMundi.setBounds(0, 0, 1550, 850);
		Pciudades.add(MapaMundi);
		// panel botones 1(botnes del menu principal)--------------------------
		JPanel PBotones1 = new JPanel();
		PBotones1.setBounds(0, 0, 1550, 850);
		PBotones1.setOpaque(false);
		PBotones1.setLayout(null);
		// nueva partida
		JButton NuevaPartida = new JButton();
		NuevaPartida.setText("Nueva partida");
		NuevaPartida.setBounds(670, 330, 180, 40);
		PBotones1.add(NuevaPartida);
		// cargar partida
		JButton CargarPartida = new JButton();
		CargarPartida.setText("Cargar partida");
		CargarPartida.setBounds(670, 373, 180, 40);
		PBotones1.add(CargarPartida);
		// resumen de puntuaciones
		JButton ResumenPuntuaciones = new JButton();
		ResumenPuntuaciones.setText("Resumen de puntuaciones");
		ResumenPuntuaciones.setBounds(660, 453, 200, 40);
		PBotones1.add(ResumenPuntuaciones);
		// informacion del juego (instrucciones de juego)
		JButton Informacion = new JButton();
		Informacion.setText("Informacion");
		Informacion.setBounds(670, 660, 180, 40);
		PBotones1.add(Informacion);
		// autores
		JButton Autores = new JButton();
		Autores.setText("Autores");
		Autores.setBounds(710, 730, 100, 40);
		PBotones1.add(Autores);
		// version
		JButton Version = new JButton();
		Version.setText("Version");
		Version.setBounds(50, 730, 100, 40);
		PBotones1.add(Version);
		// salir del juego
		JButton Salir = new JButton();
		Salir.setText("Salir");
		Salir.setName("");
		Salir.setBounds(1320, 730, 100, 40);
		PBotones1.add(Salir);
		// pnael para poner texto (texto de informacion y boton volver)---------------------------------
		JPanel PBotones2 = new JPanel();
		PBotones2.setBounds(0, 0, 1550, 850);
		PBotones2.setOpaque(false);
		PBotones2.setVisible(false);
		PBotones2.setLayout(null);
		// botn de volver
		JButton VolverMenu = new JButton();
		VolverMenu.setText("Volver");
		VolverMenu.setBounds(710, 730, 100, 40);
		PBotones2.add(VolverMenu);
		// texto usado en botones autores, informacion y varsion
		JTextPane textMenu = new JTextPane();
		textMenu.setForeground(new Color(238, 238, 238));
		textMenu.setOpaque(false);
		textMenu.setFont(
				new Font("Constantia", textMenu.getFont().getStyle() | Font.BOLD, textMenu.getFont().getSize() + 5));
		textMenu.setEditable(false);
		textMenu.setBounds(500, 200, 500, 400);
		StyledDocument doc = textMenu.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		PBotones2.add(textMenu);
		// marco translucido
		ImageIcon TransparenteImagen = new ImageIcon("transparente.png");
		JLabel TransparenteLable = new JLabel(TransparenteImagen);
		TransparenteLable.setBounds(500, 200, 500, 400);
		PBotones2.add(TransparenteLable);
		// añade los menus y hace el marco visible-------------------------------------------------
		getContentPane().add(PBotones1);
		getContentPane().add(PBotones2);
		getContentPane().add(Pciudades);
		setVisible(true);
		// listeners de los botones
		Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Salir();
			}
		});
		// los siguientes 3 listeners hacen ciertos botones visibles y ciertos botones
		// visibles, tambien un texto y una imagen
		Autores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringMenu = Autores(StringMenu);
				textMenu.setText(StringMenu);
				PBotones1.setVisible(false);
				PBotones2.setVisible(true);
			}
		});

		Version.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringMenu = Version(StringMenu);
				textMenu.setText(StringMenu);
				PBotones1.setVisible(false);
				PBotones2.setVisible(true);
			}
		});

		Informacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringMenu = Informacion(StringMenu);
				textMenu.setText(StringMenu);
				PBotones1.setVisible(false);
				PBotones2.setVisible(true);
			}
		});

		VolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textMenu.setText("");
				PBotones1.setVisible(true);
				PBotones2.setVisible(false);
			}
		});
	}

	static void NuevaPartida() {

	}

	static void CargarPartida() {

	}

	static void ResumenPuntuaciones() {

	}

	// obtine el texto del archivo informacion.txt
	static String Informacion(String StringMenu) {
		String texto = "";
		StringMenu = "";
		try {
			FileReader fr = new FileReader("informacion.txt");
			BufferedReader br = new BufferedReader(fr);
			texto = br.readLine();
			while (texto != null) {
				StringMenu = StringMenu + "\n" + texto;
				texto = br.readLine();
			}
			fr.close();
			br.close();
		} catch (IOException e) {
			if (texto == null) {
				texto = "Error al leer los archivos!!\nvuelve a instalar el juego!";
			}
		}
		return StringMenu;
	}

	// obtine el texto del archivo autores.txt
	static String Autores(String StringMenu) {
		String texto = "";
		StringMenu = "";
		try {
			FileReader fr = new FileReader("autores.txt");
			BufferedReader br = new BufferedReader(fr);
			texto = br.readLine();
			while (texto != null) {
				StringMenu = StringMenu + "\n" + texto;
				texto = br.readLine();
			}
			fr.close();
			br.close();
		} catch (IOException e) {
			if (texto == null) {
				StringMenu = "Error al leer los archivos!!\nvuelve a instalar el juego!";
			}
		}
		return StringMenu;
	}

	// obtine el texto del archivo version.txt
	static String Version(String StringMenu) {
		String texto = "";
		StringMenu = "";
		try {
			FileReader fr = new FileReader("version.txt");
			BufferedReader br = new BufferedReader(fr);
			texto = br.readLine();
			while (texto != null) {
				StringMenu = StringMenu + "\n" + texto;
				texto = br.readLine();
			}
			fr.close();
			br.close();
		} catch (IOException e) {
			if (texto == null) {
				texto = "Error al leer los archivos!!\nvuelve a instalar el juego!";
			}
		}
		return StringMenu;
	}

	// salir del juego
	static void Salir() {
		System.exit(0);
	}
}