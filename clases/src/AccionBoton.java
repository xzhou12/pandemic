import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AccionBoton {
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
