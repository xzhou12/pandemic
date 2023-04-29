import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class tablero extends JPanel {
	public tablero() {
		setBounds(0, 0, 1550, 850);
		setBackground(new Color(6, 153, 209));
		setLayout(null);

		// imagenes ciudades
		ImageIcon bola1 = new ImageIcon("punto.png");

		JLabel San_Francisco = new JLabel(bola1);
		San_Francisco.setBounds(235, 315, 15, 15);
		add(San_Francisco);

		JLabel Chicago = new JLabel(bola1);
		Chicago.setBounds(300, 280, 15, 15);
		add(Chicago);

		JLabel Atlanta = new JLabel(bola1);
		Atlanta.setBounds(320, 320, 15, 15);
		add(Atlanta);

		JLabel Montreal = new JLabel(bola1);
		Montreal.setBounds(350, 280, 15, 15);
		add(Montreal);

		JLabel Nueva_York = new JLabel(bola1);
		Nueva_York.setBounds(380, 290, 15, 15);
		add(Nueva_York);

		JLabel Washington = new JLabel(bola1);
		Washington.setBounds(360, 330, 15, 15);
		add(Washington);

		JLabel Londres = new JLabel(bola1);
		Londres.setBounds(700, 230, 15, 15);
		add(Londres);

		JLabel Madrid = new JLabel(bola1);
		Madrid.setBounds(687, 290, 15, 15);
		add(Madrid);

		JLabel Paris = new JLabel(bola1);
		Paris.setBounds(727, 250, 15, 15);
		add(Paris);

		JLabel Essen = new JLabel(bola1);
		Essen.setBounds(755, 190, 15, 15);
		add(Essen);

		JLabel Milan = new JLabel(bola1);
		Milan.setBounds(755, 235, 15, 15);
		add(Milan);

		JLabel San_Petesburgo = new JLabel(bola1);
		San_Petesburgo.setBounds(815, 210, 15, 15);
		add(San_Petesburgo);

		JLabel Los_Angeles = new JLabel(bola1);
		Los_Angeles.setBounds(275, 355, 15, 15);
		add(Los_Angeles);

		JLabel Miami = new JLabel(bola1);
		Miami.setBounds(380, 360, 15, 15);
		add(Miami);

		JLabel Mexico = new JLabel(bola1);
		Mexico.setBounds(300, 385, 15, 15);
		add(Mexico);

		JLabel Bogota = new JLabel(bola1);
		Bogota.setBounds(400, 460, 15, 15);
		add(Bogota);

		JLabel Lima = new JLabel(bola1);
		Lima.setBounds(395, 520, 15, 15);
		add(Lima);

		JLabel Santiago_de_chile = new JLabel(bola1);
		Santiago_de_chile.setBounds(430, 620, 15, 15);
		add(Santiago_de_chile);

		JLabel Buenaos_Aires = new JLabel(bola1);
		Buenaos_Aires.setBounds(453, 670, 15, 15);
		add(Buenaos_Aires);

		JLabel Sao_Paolo = new JLabel(bola1);
		Sao_Paolo.setBounds(520, 570, 15, 15);
		add(Sao_Paolo);

		JLabel Lagos = new JLabel(bola1);
		Lagos.setBounds(710, 450, 15, 15);
		add(Lagos);

		JLabel Kinsasa = new JLabel(bola1);
		Kinsasa.setBounds(770, 540, 15, 15);
		add(Kinsasa);

		JLabel Jarmut = new JLabel(bola1);
		Jarmut.setBounds(815, 450, 15, 15);
		add(Jarmut);

		JLabel Johannesburgo = new JLabel(bola1);
		Johannesburgo.setBounds(815, 630, 15, 15);
		add(Johannesburgo);

		JLabel Argel = new JLabel(bola1);
		Argel.setBounds(730, 330, 15, 15);
		add(Argel);

		JLabel El_Cairo = new JLabel(bola1);
		El_Cairo.setBounds(820, 350, 15, 15);
		add(El_Cairo);

		JLabel Riad = new JLabel(bola1);
		Riad.setBounds(895, 385, 15, 15);
		add(Riad);

		JLabel Estambul = new JLabel(bola1);
		Estambul.setBounds(830, 294, 15, 15);
		add(Estambul);

		JLabel Baglad = new JLabel(bola1);
		Baglad.setBounds(880, 320, 15, 15);
		add(Baglad);

		JLabel Moscu = new JLabel(bola1);
		Moscu.setBounds(890, 230, 15, 15);
		add(Moscu);

		JLabel Teheran = new JLabel(bola1);
		Teheran.setBounds(920, 310, 15, 15);
		add(Teheran);

		JLabel Karachi = new JLabel(bola1);
		Karachi.setBounds(980, 230, 15, 15);
		add(Karachi);

		JLabel Bombay = new JLabel(bola1);
		Bombay.setBounds(1005, 395, 15, 15);
		add(Bombay);

		JLabel Nueva_Dehli = new JLabel(bola1);
		Nueva_Dehli.setBounds(1025, 330, 15, 15);
		add(Nueva_Dehli);

		JLabel Calcuta = new JLabel(bola1);
		Calcuta.setBounds(1070, 370, 15, 15);
		add(Calcuta);

		JLabel Madras = new JLabel(bola1);
		Madras.setBounds(1035, 410, 15, 15);
		add(Madras);

		JLabel Yakarta = new JLabel(bola1);
		Yakarta.setBounds(1150, 525, 15, 15);
		add(Yakarta);

		JLabel Bangkok = new JLabel(bola1);
		Bangkok.setBounds(1120, 415, 15, 15);
		add(Bangkok);

		JLabel Hong_Kong = new JLabel(bola1);
		Hong_Kong.setBounds(1165, 370, 15, 15);
		add(Hong_Kong);

		JLabel Shanghai = new JLabel(bola1);
		Shanghai.setBounds(1195, 355, 15, 15);
		add(Shanghai);

		JLabel Pekin = new JLabel(bola1);
		Pekin.setBounds(1175, 300, 15, 15);
		add(Pekin);

		JLabel Seul = new JLabel(bola1);
		Seul.setBounds(1225, 297, 15, 15);
		add(Seul);

		JLabel Tokio = new JLabel(bola1);
		Tokio.setBounds(1280, 290, 15, 15);
		add(Tokio);

		JLabel Osaka = new JLabel(bola1);
		Osaka.setBounds(1255, 320, 15, 15);
		add(Osaka);

		JLabel Taipei = new JLabel(bola1);
		Taipei.setBounds(1205, 375, 15, 15);
		add(Taipei);

		JLabel Ho_Chi_Minh = new JLabel(bola1);
		Ho_Chi_Minh.setBounds(1148, 100, 15, 15);
		add(Ho_Chi_Minh);

		JLabel Manila = new JLabel(bola1);
		Manila.setBounds(1200, 420, 15, 15);
		add(Manila);

		JLabel Sidney = new JLabel(bola1);
		Sidney.setBounds(1320, 645, 15, 15);
		add(Sidney);

		// imagen de fondo(mapa)
		ImageIcon Imagen = new ImageIcon("mapa_mundo.png");
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
