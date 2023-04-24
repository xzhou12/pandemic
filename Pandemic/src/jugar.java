import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class jugar {

	public static void Main() {

		// Array con las ciudades
		String[][] ciudades = IA.leerCiudades();

		int numBrotes = 0;
		int numInfeccActivas = 0;

		// Array con las ciudades y su nivel de brote
		ArrayList<ArrayList> nivelBroteCiudades = IA.inicializarNivelBrote(ciudades);

		// Inicializa brotes

		IA.comprobarBroteNivel4(nivelBroteCiudades);
		for (ArrayList aux : nivelBroteCiudades) {
			System.out.println(aux);
		}

	}

}
