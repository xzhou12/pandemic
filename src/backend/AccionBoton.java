package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Paneles.PanelInfo;

/**
 * AccionBoton Se definen las acciones del botón, pero solo aquellos que
 * requieran de texto
 * 
 * @author Albert Barrachina
 * @version 1.0
 */

public class AccionBoton {

	/**
	 * SetTextoInfo: Obtiene el texto del archivo seleccionado y lo pone en el
	 * archivo "info.txt" para establecerlo en el panel de info mas tarde
	 * 
	 * @param Archivo se le pasa el archivo donde aparece toda la información
	 */
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
			PanelInfo.setTexto(texto);
			br.close();
			fr.close();
		} catch (IOException e) {
			if (textoArchivo == null) {
				texto = "Error al leer los archivos!!\nvuelve a instalar el juego!";
			}
		}
	}

	/**
	 * Salir Sale del juego
	 * 
	 */
	public static void Salir() {
		System.exit(0);
	}
}
