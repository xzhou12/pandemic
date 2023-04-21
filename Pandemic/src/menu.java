import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Font;
import javax.swing.JSlider;

public class menu extends JFrame {
	//string para sobreescribir el panel de texto
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
		JPanel PFondo = new JPanel();
		PFondo.setBounds(0, 0, 1550, 850);
		PFondo.setBackground(new Color(6, 153, 209));
		PFondo.setLayout(null);
		// imagen de fondo(mapa)
		ImageIcon Imagen = new ImageIcon("mapa_mundo.png");
		// cambia las dimensiones de la imagen
		Image image = Imagen.getImage();
		Image newimg = image.getScaledInstance(1550, 850, java.awt.Image.SCALE_SMOOTH);
		Imagen = new ImageIcon(newimg);
		JLabel MapaMundi = new JLabel(Imagen);
		MapaMundi.setFocusable(false);
		MapaMundi.setBounds(0, 0, 1550, 850);
		PFondo.add(MapaMundi);
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
		// configuracion
		ImageIcon ConfigIcon = new ImageIcon("config.png");
		Image image2 = ConfigIcon.getImage();
		Image newimg2 = image2.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ConfigIcon = new ImageIcon(newimg2);
		JLabel ConfigLable = new JLabel(ConfigIcon);
		ConfigLable.setBounds(20, 20, 45, 45);
		PBotones1.add(ConfigLable);
		// salir del juego
		JButton Salir = new JButton();
		Salir.setText("Salir");
		Salir.setName("");
		Salir.setBounds(1320, 730, 100, 40);
		PBotones1.add(Salir);
		// pnael para poner texto (texto de informacion y boton
		// volver)---------------------------------
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
		image = TransparenteImagen.getImage();
		newimg = image.getScaledInstance(500, 400, java.awt.Image.SCALE_SMOOTH);
		TransparenteImagen = new ImageIcon(newimg);
		JLabel TransparenteLable = new JLabel(TransparenteImagen);
		TransparenteLable.setBounds(500, 200, 500, 400);
		PBotones2.add(TransparenteLable);
		// panel de configuracion
		JPanel ConfigPanel = new JPanel();
		ConfigPanel.setVisible(false);
		ConfigPanel.setOpaque(false);
		ConfigPanel.setBounds(0, 0, 1550, 850);
		ConfigPanel.setLayout(null);
		// boton para volver al menu principal
		JButton VolverConf = new JButton();
		VolverConf.setBackground(new Color(72, 72, 72));
		VolverConf.setForeground(new Color(255, 255, 255));
		VolverConf.setText("Volver");
		VolverConf.setBounds(20, 20, 100, 40);
		ConfigPanel.add(VolverConf);
		// lable de dificultad
		JLabel DificultadLable = new JLabel("DIFICULTAD");
		DificultadLable.setForeground(new Color(255, 255, 255));
		DificultadLable.setFont(new Font("Tahoma", Font.PLAIN, 32));
		DificultadLable.setBounds(670, 100, 180, 40);
		ConfigPanel.add(DificultadLable);
		// difficultad facil
		JButton FACIL = new JButton();
		FACIL.setBackground(new Color(72, 72, 72));
		FACIL.setForeground(new Color(255, 255, 255));
		FACIL.setText("FACIL");
		FACIL.setBounds(670, 170, 180, 40);
		ConfigPanel.add(FACIL);
		// dificultad normal
		JButton NORMAL = new JButton();
		NORMAL.setBackground(new Color(72, 72, 72));
		NORMAL.setForeground(new Color(255, 255, 255));
		NORMAL.setText("NORMAL");
		NORMAL.setBounds(670, 230, 180, 40);
		ConfigPanel.add(NORMAL);
		// dificultad dificil
		JButton DIFICIL = new JButton();
		DIFICIL.setBackground(new Color(72, 72, 72));
		DIFICIL.setForeground(new Color(255, 255, 255));
		DIFICIL.setText("DIFICIL");
		DIFICIL.setBounds(670, 290, 180, 40);
		ConfigPanel.add(DIFICIL);
		// lable de volumen
		JLabel VolumenLable = new JLabel("VOLUMEN");
		VolumenLable.setForeground(new Color(255, 255, 255));
		VolumenLable.setFont(new Font("Tahoma", Font.PLAIN, 32));
		VolumenLable.setBounds(670, 530, 180, 40);
		ConfigPanel.add(VolumenLable);
		// lable de volumen maestro
		JLabel VolumenMaestroLable = new JLabel("MAESTRO");
		VolumenMaestroLable.setForeground(new Color(255, 255, 255));
		VolumenMaestroLable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		VolumenMaestroLable.setBounds(530, 580, 100, 40);
		ConfigPanel.add(VolumenMaestroLable);
		// lable de volumen musica
		JLabel VolumenMusicaLable = new JLabel("MUSICA");
		VolumenMusicaLable.setForeground(new Color(255, 255, 255));
		VolumenMusicaLable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		VolumenMusicaLable.setBounds(530, 630, 100, 40);
		ConfigPanel.add(VolumenMusicaLable);
		// lable de volumen SFX
		JLabel VolumenSFXLable = new JLabel("SFX");
		VolumenSFXLable.setForeground(new Color(255, 255, 255));
		VolumenSFXLable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		VolumenSFXLable.setBounds(530, 690, 100, 40);
		ConfigPanel.add(VolumenSFXLable);
		// slider del volumen maestro
		JSlider sliderMaestro = new JSlider();
		sliderMaestro.setOpaque(false);
		sliderMaestro.setBounds(660, 580, 200, 40);
		ConfigPanel.add(sliderMaestro);
		// slider de la musica
		JSlider sliderMusica = new JSlider();
		sliderMusica.setOpaque(false);
		sliderMusica.setBounds(660, 630, 200, 40);
		ConfigPanel.add(sliderMusica);
		// slider de SFX
		JSlider sliderSFX = new JSlider();
		sliderSFX.setOpaque(false);
		sliderSFX.setBounds(660, 690, 200, 40);
		ConfigPanel.add(sliderSFX);
		// imagen trasparente de fondo para efecto de menu
		ImageIcon ConfigImagen = new ImageIcon("transparente.png");
		image = ConfigImagen.getImage();
		newimg = image.getScaledInstance(1500, 800, java.awt.Image.SCALE_SMOOTH);
		ConfigImagen = new ImageIcon(newimg);
		JLabel ConfigBoxImage = new JLabel(ConfigImagen);
		ConfigBoxImage.setBounds(15, 15, 1500, 780);
		ConfigPanel.add(ConfigBoxImage);
		// añade los menus y hace el marco
		// visible-------------------------------------------------
		getContentPane().add(PBotones1);
		getContentPane().add(PBotones2);
		getContentPane().add(ConfigPanel);
		getContentPane().add(PFondo);
		setVisible(true);
		// listeners de los botones
		Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionBoton.Salir();
			}
		});
		// los siguientes 3 listeners hacen ciertos botones visibles y ciertos botones
		// visibles, tambien un texto y una imagen
		Autores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringMenu = AccionBoton.Autores(StringMenu);
				textMenu.setText(StringMenu);
				PBotones1.setVisible(false);
				PBotones2.setVisible(true);
			}
		});

		Version.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringMenu = AccionBoton.Version(StringMenu);
				textMenu.setText(StringMenu);
				PBotones1.setVisible(false);
				PBotones2.setVisible(true);
			}
		});

		Informacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringMenu = AccionBoton.Informacion(StringMenu);
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

		// configuracion
		ConfigLable.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PBotones1.setVisible(false);
				PBotones2.setVisible(false);
				ConfigPanel.setVisible(true);
			}
		});
		VolverConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PBotones1.setVisible(true);
				ConfigPanel.setVisible(false);
			}
		});

		FACIL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				config.SetDificultad(1);
			}
		});
		NORMAL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				config.SetDificultad(2);
			}
		});
		DIFICIL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				config.SetDificultad(3);
			}
		});
	}
}