package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * brotes En esta clase es donde se hacen la mayoria de gestiones relacionadas
 * con brotes o enfermedades
 * 
 * @author Xiaobin Zhou
 * @version 1.0
 */

public class brotes {

	/**
	 * inicializarNivelBrote: Obtiene las ciudades, las reformatea y las inicializa
	 * para poder proceder con el juego
	 * 
	 * @param ciudades se le pasan la array con las ciudades
	 * @return ArrayList devuelve la arraylist ya inicializada
	 */
	public static ArrayList<ArrayList> inicializarNivelBrote(String[][] ciudades) {

		ArrayList<ArrayList> ciudadesBrotes = new ArrayList<ArrayList>();

		// Bucle para copiar el nombre de las ciudades y inicializar el nivel
		// de brote a 0
		for (int i = 0; i < ciudades.length; i++) {
			ArrayList<String> ciudad = new ArrayList<String>();
			ciudad.add(ciudades[i][0]); // Nombre
			ciudad.add("0"); // Nivel de brote
			ciudad.add(ciudades[i][1]); // Enfermedad
			ciudadesBrotes.add(ciudad);
		}

		// Infecta las ciudades predefinidas al inicio
		infectarCiudadesInicio(ciudadesBrotes);

		// Retorna la ArrayList
		return ciudadesBrotes;
	}

	/**
	 * infectarCiudadesInicio: Inicializa los brotes que hay antes de iniciar la
	 * partida
	 * 
	 * @param ciudadesBrotes se le pasa la arraylist de las ciuades con el nivel de
	 *                       brote
	 */
	public static void infectarCiudadesInicio(ArrayList<ArrayList> ciudadesBrotes) {
		String[] param = parametros.leerArchivo();

		// numCiudadesInfectadasInicio
		int numCII = Integer.parseInt(param[0]);
		// Infecta x veces, como esta predefinido en el archivo parametros
		for (int i = 0; i < numCII; i++) {
			// Infecta las ciudades aleatoriamente
			infectarCiudadesAleatorio(ciudadesBrotes);

		}

	}

	/**
	 * infectarCiudadesAleatorio: Infecta las ciudades de forma aleatoria
	 * 
	 * @param ciudades se le pasa la arraylist de las ciuades con el nivel de brote
	 * @return String devuevle las ciudades que han sido infectadas
	 */
	public static String infectarCiudadesAleatorio(ArrayList<ArrayList> ciudades) {
		Random r = new Random();
		// Escoge un numero entre el 0 y el numero de ciudades
		int numR = r.nextInt(ciudades.size());
		// Coge el nivel y suma un valor
		int nivel = Integer.parseInt((String) ciudades.get(numR).get(1));
		nivel++;
		// Y vuelve a poner ese valor
		ciudades.get(numR).set(1, Integer.toString(nivel));

		// Devuelve la ciudad afectada
		return (String) ciudades.get(numR).get(0);

	}

	/**
	 * infectarCiudadParametro: Infecta la ciudad que le pasamos por parametro
	 * 
	 * @param ciudadesBrotes se le pasa la arraylist de las ciuades con el nivel de
	 *                       brote
	 * @param ciudad         se le pasa la ciudad que queremos que se infecte
	 */
	public static void infectarCiudadParametro(ArrayList<ArrayList> ciudadesBrotes, String ciudad) {

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

	/**
	 * bajarCiudadParametro: Baja un nivel a la ciudad que le pasamos por parametro
	 * 
	 * @param ciudadesBrotes se le pasa la arraylist de las ciuades con el nivel de
	 *                       brote
	 * @param ciudad         se le pasa la ciudad que queremos que baje
	 */
	public static void bajarCiudadParametro(ArrayList<ArrayList> ciudadesBrotes, String ciudad) {
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

	/**
	 * curarTodo: Cura toda la ciudad si la vacuna esta hecha
	 * 
	 * @param ciudadesBrotes se le pasa la arraylist de las ciuades con el nivel de
	 *                       brote
	 * @param ciudad         se le pasa la ciudad que queremos que baje
	 */
	public static void curarTodo(ArrayList<ArrayList> ciudadesBrotes, String ciudad) {
		for (ArrayList ciudades : ciudadesBrotes) {
			String ciudadA = (String) ciudades.get(0);
			if (ciudadA.equals(ciudad)) {
				// Coge el nivel y lo pasa a int
				int nivel = Integer.parseInt((String) ciudades.get(1));
				nivel = 0; // Cura la ciudad
				// Y lo vuelve a meter
				ciudades.set(1, Integer.toString(nivel));
			}
		}
	}

	/**
	 * addBrotesColindante: Añade brotes a las ciudades colindantes que han llegado
	 * a nivel 4
	 * 
	 * @param ciudadesNivel4 se le pasa las ciudades que han llegado a nivel 4
	 * @param ciudadesBrotes se le pasa la arraylist de las ciuades con el nivel de
	 *                       brote
	 * 
	 */
	static void addBrotesColindante(ArrayList<String> ciudadesNivel4, ArrayList<ArrayList> ciudadesBrotes) {

		String[][] ciudades = IA.leerCiudades();

		for (String ciudad : ciudadesNivel4) {
			// Busca las ciudades colindantes
			ArrayList<String> ciudadColindante = buscarColindantes(ciudades, ciudad);
			// Infecta las ciudades colindantes y baja un nivel a la ciudad que ha llegado a
			// nivel 4
			for (String colindante : ciudadColindante) {
				if (!ciudadesNivel4.contains(colindante)) {
					infectarCiudadParametro(ciudadesBrotes, colindante);
				}

			}
			bajarCiudadParametro(ciudadesBrotes, ciudad);

		}

	}

	// Busca y devuelve las ciudades colindantes
	/**
	 * buscarColindantes: Busca y devuelve las ciudades colindantes
	 * 
	 * @param ciudades se le pasa la información de las ciudades
	 * @param ciudad   se le pasa la ciudad que queremos que busque las colindantes
	 * @return ArrayList devuelve una arraylist con las colindantes
	 */
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

}
