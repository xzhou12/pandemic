package backend;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class AccionBoton {

	// obtine el texto del archivo seleccionado y lo pone en el archivo "info.txt"
	// para establecerlo en el panel de info mas tarde
	public static void SetTextoInfo(String Archivo) {
		String textoArchivo = null;
		String texto = "";

		try {
			FileReader fr = new FileReader(Archivo + ".txt");
			BufferedReader br = new BufferedReader(fr);
			textoArchivo = br.readLine();
			while (textoArchivo != null) {
				texto = texto + "\n" + textoArchivo;
				textoArchivo = br.readLine();
			}
			Paneles.PanelInfo.setTexto(texto);
			br.close();
			fr.close();
		} catch (IOException e) {
			if (textoArchivo == null) {
				texto = "Error al leer los archivos!!\nvuelve a instalar el juego!";
			}
		}
	}

	// salir del juego
	public static void Salir() {
		System.exit(0);
	}
}
