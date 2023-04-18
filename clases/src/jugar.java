import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class jugar {

	public static void Main() {
		String[][] ciudades = leerCiudades();
		String[][] nivelBrote = inicializarNivelBrote(ciudades);
		inicializarBrotesPartida(nivelBrote);

	}

	// ************************************************************************************
	// ** Nombre de la función: leerCiudades()
	// ** Función: Lee las ciudades que hay en el archivo de ciudades
	// ** Parámetros de entrada: File
	// ** Parámetros de salida: int
	// ************************************************************************************
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

	// ************************************************************************************
	// ** Nombre de la función: contarLineas()
	// ** Función: Cuenta la linea de ciudades que hay en el archivo
	// ** Parámetros de entrada: File
	// ** Parámetros de salida: int
	// ************************************************************************************
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

	// ************************************************************************************
	// ** Nombre de la función: inicializarNivelBrote()
	// ** Función: Inicializa el nivel de brote a 0
	// ** Parámetros de entrada: String[][]
	// ** Parámetros de salida: String[][]
	// ************************************************************************************
	public static String[][] inicializarNivelBrote(String[][] ciudades) {

		String[][] ciudadesBrote = new String[ciudades.length][2];

		// Bucle para copiar el nombre de las ciudades y inicializar el nivel
		// de brote a 0
		for (int i = 0; i < ciudades.length; i++) {
			ciudadesBrote[i][0] = ciudades[i][0];
			ciudadesBrote[i][1] = "0";
		}

		return ciudadesBrote;
	}

	// ************************************************************************************
	// ** Nombre de la función: inicializarBrotesPartida()
	// ** Función: Inicializa los brotes encima de la partida
	// ** Parámetros de entrada: String[][]
	// ** Parámetros de salida: void
	// ************************************************************************************
	public static void inicializarBrotesPartida(String[][] ciudadesBrotes) {
		File fileParametros = new File("parametros.xml");
		String[] param = parametros.leerArchivo(fileParametros);

	}

}
