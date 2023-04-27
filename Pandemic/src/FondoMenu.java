import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FondoMenu extends JPanel{

	public FondoMenu() {
		// panel del menu(fondo menu)-----------------------------------
				setBounds(0, 0, 1550, 850);
				setBackground(new Color(6, 153, 209));
				setLayout(null);
				// imagen de fondo(mapa)
				ImageIcon Imagen = new ImageIcon("mapa_mundo.png");
				// cambia las dimensiones de la imagen
				Image image = Imagen.getImage();
				image = image.getScaledInstance(1550, 850, java.awt.Image.SCALE_SMOOTH);
				Imagen = new ImageIcon(image);
				JLabel MapaMundi = new JLabel(Imagen);
				MapaMundi.setFocusable(false);
				MapaMundi.setBounds(0, 0, 1550, 850);
				add(MapaMundi);
	}
}
