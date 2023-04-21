import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class IA {
	boolean alfa, beta, gama, delta = true;

	// Inicializa los brotes encima de la partida
	public static void infectarCiudadesInicio(ArrayList<ArrayList> ciudadesBrotes) {
		File fileParametros = new File("parametros.xml");
		String[] param = parametros.leerArchivo(fileParametros);

		// numCiudadesInfectadasInicio
		int numCII = Integer.parseInt(param[0]);
		for (int i = 0; i < numCII; i++) {
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

		for (ArrayList ciudades : ciudadesBrotes) {
			String ciudadA = (String) ciudades.get(0);
			if (ciudadA.equals(ciudad)) {
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
				int nivel = Integer.parseInt((String) ciudades.get(1));
				nivel--;
				ciudades.set(1, Integer.toString(nivel));
			}
		}
	}

	// Comprueba si alguna ciudad tiene el nivel 4 de infección
	static void comprobarBroteNivel4(ArrayList<ArrayList> ciudadesBrotes) {

		for (ArrayList aux : ciudadesBrotes) {
			int nivel = Integer.parseInt((String) aux.get(1));
			if (nivel >= 4) {
				addBrotes(ciudadesBrotes, (String) aux.get(1));
				System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			}
		}

	}

	// Añade brotes a las ciudades colindantes que han llegado a nivel 4
	static void addBrotes(ArrayList<ArrayList> ciudadesBrotes, String ciudad) {

		String[][] ciudades = jugar.leerCiudades();
		ArrayList<String> colindante = buscarColindantes(ciudades, ciudad);

		// NO FUNCIONA
		infectarColindantes(ciudadesBrotes, colindante, ciudad);
		bajarCiudadParametro(ciudadesBrotes, ciudad);

	}

	// Busca y devuelve las ciudades colindantes
	static ArrayList buscarColindantes(String[][] ciudades, String ciudad) {

		ArrayList<String> colindante = new ArrayList<String>();

		// Busca la ciudad que le hemos pasado por parametro
		for (int i = 0; i < ciudades.length; i++) {
			if (ciudades[i][0].equals(ciudad)) {

				// Y guarda las ciudades colindantes
				for (int k = 3; k < ciudades[i].length; k++) {
					colindante.add(ciudades[i][k]);
				}
			}
		}
		// Deuvelve la ArrayList con las ciudades colindantes
		return colindante;

	}

	static void infectarColindantes(ArrayList<ArrayList> ciudadesBrotes, ArrayList<String> colindante, String ciudad4) {

		// NO FUNCIONA
		for (String ciudadColindante : colindante) {
			for (ArrayList ciudades : ciudadesBrotes) {
				String ciudadA = (String) ciudades.get(0);
				if (ciudadA.equals(ciudadColindante)) {
					infectarCiudadParametro(ciudadesBrotes, ciudadA);
				}
			}
		}

	}

	static void comprobarCura() {

	}
}
