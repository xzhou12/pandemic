import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class jugar {

	public static void Main() {

		// Array con las ciudades
		String[][] ciudades = leerCiudades();

		// Array con las ciudades y su nivel de brote
		ArrayList<ArrayList> nivelBrote = inicializarNivelBrote(ciudades);

		System.out.println("INFECTADOS");
		// Inicializa brotes
		IA.infectarCiudadesInicio(nivelBrote);
		IA.comprobarBroteNivel4(nivelBrote);
//		for (ArrayList aux : nivelBrote) {
//			System.out.println(aux);
//		}

	}

	// Lee las ciudades que hay en el archivo de ciudades
	public static String[][] leerCiudades() {
		File fileCiudades = new File("ciudades.txt");
		String s = "";
		int tamano = contarLineas(fileCiudades);
		String[][] ciudades = new String[tamano][];

		// Recorre el archivo linea por linea hasta que sea null
		try (BufferedReader br = new BufferedReader(new FileReader(fileCiudades))) {

			for (int i = 0; s != null; i++) {
				s = br.readLine();
				if (s != null) { // Y si no es nula
					// La separa y la guarda en la array[][]
					ciudades[i] = s.split(";");
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return ciudades;

	}

	// Cuenta la linea de ciudades que hay en el archivo
	public static int contarLineas(File fileCiudades) {
		int contador = 0;

		// Recorre el archivo linea por linea hasta que sea null
		try (BufferedReader br = new BufferedReader(new FileReader(fileCiudades))) {
			while (br.readLine() != null)
				contador++;
		} catch (Exception e) {
		}

		// Devuelve las lineas contadas
		return contador;
	}

	// Inicializa el nivel de brote a 0
	public static ArrayList<ArrayList> inicializarNivelBrote(String[][] ciudades) {

		ArrayList<ArrayList> ciudadesBrotes = new ArrayList<ArrayList>();
		String[][] ciudadesBrote = new String[ciudades.length][2];

		// Bucle para copiar el nombre de las ciudades y inicializar el nivel
		// de brote a 0
		for (int i = 0; i < ciudades.length; i++) {
			ArrayList<String> ciudad = new ArrayList<String>();
			ciudad.add(ciudades[i][0]);
			ciudad.add("0");
			ciudadesBrotes.add(ciudad);
			ciudadesBrote[i][0] = ciudades[i][0];
			ciudadesBrote[i][1] = "0";
		}

		return ciudadesBrotes;
	}

}
