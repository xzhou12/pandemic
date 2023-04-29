import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AccionBoton {
	void NuevaPartida(String linea) {

	}

	static void CargarPartida() {

	}

	static void ResumenPuntuaciones() {

	}

	// obtine el texto del archivo informacion.txt
	static void Informacion() {
		String textoArchivo = null;
		String texto = "";

		try {
			FileReader fr = new FileReader("informacion.txt");
			BufferedReader br = new BufferedReader(fr);
			textoArchivo = br.readLine();
			while (textoArchivo != null) {
				texto = texto + "\n" + textoArchivo;
				textoArchivo = br.readLine();
			}
			PanelInfo.setTexto(texto);
			br.close();
			fr.close();
		} catch (IOException e) {
			if (textoArchivo == null) {
				texto = "Error al leer los archivos!!\nvuelve a instalar el juego!";
			}
		}

	}

	// obtine el texto del archivo autores.txt
	static void Autores() {
		String textoArchivo = null;
		String texto = "";

		try {
			FileReader fr = new FileReader("autores.txt");
			BufferedReader br = new BufferedReader(fr);
			textoArchivo = br.readLine();
			while (textoArchivo != null) {
				texto = texto + "\n" + textoArchivo;
				textoArchivo = br.readLine();
			}
			PanelInfo.setTexto(texto);
			br.close();
			fr.close();
		} catch (IOException e) {
			if (textoArchivo == null) {
				texto = "Error al leer los archivos!!\nvuelve a instalar el juego!";
			}
		}

	}

	// obtine el texto del archivo version.txt
	static void Version() {
		String textoArchivo = null;
		String texto = "";

		try {
			FileReader fr = new FileReader("version.txt");
			BufferedReader br = new BufferedReader(fr);
			textoArchivo = br.readLine();
			while (textoArchivo != null) {
				texto = texto + "\n" + textoArchivo;
				textoArchivo = br.readLine();
			}
			PanelInfo.setTexto(texto);
			br.close();
			fr.close();
		} catch (IOException e) {
			if (textoArchivo == null) {
				texto = "Error al leer los archivos!!\nvuelve a instalar el juego!";
			}
		}

	}

	// salir del juego
	static void Salir() {
		System.exit(0);
	}
}
