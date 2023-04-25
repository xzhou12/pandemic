import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IA {
	public static final File fileCiudades = new File("ciudades.txt");
	public static final File fileParametros = new File("parametros.xml");

	// Lee las ciudades que hay en el archivo de ciudades
	public static String[][] leerCiudades() {
		String s = "";
		int tamano = contarLineas();
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
	public static int contarLineas() {
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

		// Bucle para copiar el nombre de las ciudades y inicializar el nivel
		// de brote a 0
		for (int i = 0; i < ciudades.length; i++) {
			ArrayList<String> ciudad = new ArrayList<String>();
			ciudad.add(ciudades[i][0]);
			ciudad.add("0");
			ciudadesBrotes.add(ciudad);
		}

		infectarCiudadesInicio(ciudadesBrotes);

		return ciudadesBrotes;
	}

	// Inicializa los brotes encima de la partida
	public static void infectarCiudadesInicio(ArrayList<ArrayList> ciudadesBrotes) {
		String[] param = parametros.leerArchivo(fileParametros);

		// numCiudadesInfectadasInicio
		int numCII = Integer.parseInt(param[0]);
		for (int i = 0; i < numCII; i++) {
			infectarCiudadesAleatorio(ciudadesBrotes);
		}

	}

	// Infecta las ciudades por ronda
	public static void infectarCiudadesRondas(ArrayList<ArrayList> ciudadesBrotes) {
		String[] param = parametros.leerArchivo(fileParametros);

		// numCiudadesInfectadasRondas
		int numCIR = Integer.parseInt(param[1]);
		for (int i = 0; i < numCIR; i++) {
			infectarCiudadesAleatorio(ciudadesBrotes);
		}

	}

	// Infecta las ciudades de forma aleatoria
	static void infectarCiudadesAleatorio(ArrayList<ArrayList> ciudadesBrotes) {
		Random r = new Random();
		int numR = r.nextInt(ciudadesBrotes.size());
		int nivel = Integer.parseInt((String) ciudadesBrotes.get(numR).get(1));
		nivel++;
		ciudadesBrotes.get(numR).set(1, Integer.toString(nivel));

	}

	// Infecta la ciudad que le pasamos por parametro
	static void infectarCiudadParametro(ArrayList<ArrayList> ciudadesBrotes, String ciudad) {

		// Busca la ciudad que le pasamos por parametro
		for (ArrayList ciudades : ciudadesBrotes) {
			String ciudadA = (String) ciudades.get(0);
			if (ciudadA.equals(ciudad)) {
				// Cuando la encuentra, la infecta o sube 1 nivel
				int nivel = Integer.parseInt((String) ciudades.get(1));
				nivel++;
				ciudades.set(1, Integer.toString(nivel));
			}
		}
	}

	// Baja un nivel a la ciudad que le pasamos por parametro
	static void bajarCiudadParametro(ArrayList<ArrayList> ciudadesBrotes, String ciudad) {
		for (ArrayList ciudades : ciudadesBrotes) {
			String ciudadA = (String) ciudades.get(0);
			if (ciudadA.equals(ciudad)) {
				// Coge el nivel y lo pasa a int
				int nivel = Integer.parseInt((String) ciudades.get(1));
				nivel--; // Baja un nivel
				// Y lo vuelve a meter
				ciudades.set(1, Integer.toString(nivel));
			}
		}
	}

	// Comprueba si alguna ciudad tiene el nivel 4 de infección
	static void comprobarBroteNivel4(ArrayList<ArrayList> ciudadesBrotes, int[] numBrotes) {

		// **************************************************************************
		// SI UNA CIUDAD YA HA LLEGADO A NIVEL 4, NO PUEDE VOLVER A INFECTASE EN LA
		// MISMA RONDA
		// **************************************************************************

		for (ArrayList aux : ciudadesBrotes) {
			int nivel = Integer.parseInt((String) aux.get(1));
			if (nivel >= 4) {
				// Si hay, infecta las colindantes
				numBrotes[0]++;
				addBrotesColindante(ciudadesBrotes, (String) aux.get(0));
			}
		}

	}

	// Añade brotes a las ciudades colindantes que han llegado a nivel 4
	static void addBrotesColindante(ArrayList<ArrayList> ciudadesBrotes, String ciudad) {

		String[][] ciudades = leerCiudades();
		ArrayList<String> colindante = buscarColindantes(ciudades, ciudad);

		// Infecta las ciudades colindantes y baja un nivel a la ciudad que ha llegado a
		// nivel 4
		infectarColindantes(ciudadesBrotes, colindante);
		bajarCiudadParametro(ciudadesBrotes, ciudad);

	}

	// Busca y devuelve las ciudades colindantes
	static ArrayList buscarColindantes(String[][] ciudades, String ciudad) {

		ArrayList<String> colindante = new ArrayList<String>();

		// Busca la ciudad que le hemos pasado por parametro
		for (int i = 0; i < ciudades.length; i++) {
			if (ciudades[i][0].equals(ciudad)) {
				// Y guarda las ciudades colindantes
				colindante.addAll(Arrays.asList(ciudades[i][3].split(",")));
			}
		}
		// Deuvelve la ArrayList con las ciudades colindantes
		return colindante;

	}

	static void infectarColindantes(ArrayList<ArrayList> ciudadesBrotes, ArrayList<String> colindante) {

		// Bucle que se reptie x veces por ciudadesColindantes
		for (String ciudadColindante : colindante) {

			// Busca la ciudad en la lista
			for (ArrayList brotes : ciudadesBrotes) {

				String ciudad = (String) brotes.get(0);
				if (ciudadColindante.equals(ciudad)) {
					// Si la encuentra, infecta esa ciudad
					infectarCiudadParametro(ciudadesBrotes, ciudad);
				}

			}
		}

	}

	public static boolean comprobarVictoria(ArrayList<ArrayList> ciudadesBrotes) {
		return false;
	}

	public static boolean comprobarDerrota(ArrayList<ArrayList> ciudadesBrotes, int[] brotes) {
		return false;
	}
}
