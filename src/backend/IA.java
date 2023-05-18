package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * IA Esta clase es la que se encarga de automatizar todo
 * 
 * @author Xiaobin Zhou
 * @version 1.0
 */
public class IA {
	public static final File fileCiudades = new File("ciudades.txt");
	public static final File fileParametros = new File("parametros.xml");

	/**
	 * Lee las ciudades que hay en el archivo de ciudades
	 * 
	 * @return String[][] devuelve las ciuades
	 */
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

	/**
	 * contarLineas
	 * 
	 * @return int devuelve la cantidad de lineas
	 */
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

	/**
	 * Infecta las ciudades por ronda
	 * 
	 * @param ciudadesBrotes se pasan las ciudades para infectar
	 * @return ArrayList devuelve las ciudades afectadas
	 */
	public static ArrayList<String> infectarCiudadesRondas(ArrayList<ArrayList> ciudadesBrotes) {
		String[] param = parametros.leerArchivo();
		ArrayList<String> ciudadesAfectadas = new ArrayList<String>();

		// numCiudadesInfectadasRondas
		int numCIR = Integer.parseInt(param[1]);
		for (int i = 0; i < numCIR; i++) {
			// Infecta x ciudades por ronda de forma aleatoria
			String ciudadAfectada = brotes.infectarCiudadesAleatorio(ciudadesBrotes);
			ciudadesAfectadas.add(ciudadAfectada);
		}

		// Devuelve las ciudades que han sido afectdas
		return ciudadesAfectadas;

	}

	/**
	 * Comprueba si alguna ciudad tiene el nivel 4 de infección
	 * 
	 * @param ciudadesBrotes se pasan las ciudades para infectar
	 * @param numBrotes      se pasa el numero de brotes
	 * @return int devuelve el numero de brotes
	 */
	public static int comprobarBroteNivel4(ArrayList<ArrayList> ciudadesBrotes, int numBrotes) {

		ArrayList<String> ciudadesNivel4 = new ArrayList<String>();

		for (ArrayList ciudad : ciudadesBrotes) {
			int nivelBrote = Integer.parseInt((String) ciudad.get(1));
			if (nivelBrote >= 4) {
				numBrotes++;
				ciudadesNivel4.add((String) ciudad.get(0));
			}

		}

		brotes.addBrotesColindante(ciudadesNivel4, ciudadesBrotes);
		bajarComprobadasNivel4(ciudadesBrotes);
		return numBrotes;

	}

	/**
	 * Comprueba la victoria de parte de las ciudades
	 * 
	 * @return boolean devuelve si se ha ganado o no
	 */
	public static boolean comprobarVictoriaCiudades() {
		// Comprueba si todas las ciudades han sido curadas o no
		for (ArrayList ciudad : jugar.nivelBroteCiudades) {
			int nivelBrote = Integer.parseInt((String) ciudad.get(1));
			if (nivelBrote != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Comprueba la victoria de parte de las vacunas
	 * 
	 * @return boolean devuelve si se ha ganado o no
	 */
	public static boolean comprobarVictoriaVacunas() {
		// Comprueba si todas las vacunas han sido investigadas o no
		for (ArrayList vacuna : jugar.vacunasCura) {
			int nivelBrote = Integer.parseInt((String) vacuna.get(1));
			if (nivelBrote != 100) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Comprueba la derrota, con las dos formas de derrota
	 * 
	 * @param brotes numero de brotes
	 * @return boolean devuelve si se ha perdido o no
	 */
	public static boolean comprobarDerrota(int brotes) {
		String[] param = parametros.leerArchivo();

		// numEnfermedadesActivasDerrota
		int numEAD = Integer.parseInt(param[2]);
		// numBrotesDerrota
		int numBD = Integer.parseInt(param[3]);
		int contador = enfermedadesActivas();

		// Si el numero de brotes o enfermedades activas superan al de la derrota, se
		// marca como derrota
		if (brotes >= numBD) {
			sonido.pulsarBoton();
			JOptionPane.showMessageDialog(null, "Perdiste!\nNumero de brotes: " + brotes);
			sonido.pulsarBoton();
			return true;
		} else if (contador >= numEAD) {
			sonido.pulsarBoton();
			JOptionPane.showMessageDialog(null, "Perdiste\nCiudades infectadas: " + contador);
			sonido.pulsarBoton();
			return true;
		}

		return false;
	}

	/**
	 * Cuenta el numero de enfermedades activas y devolvuelve el valor
	 * 
	 * @return int devuelve las enfermedades activadadas
	 */
	public static int enfermedadesActivas() {
		int contador = 0;

		for (ArrayList ciudad : jugar.nivelBroteCiudades) {
			int nivelBrote = Integer.parseInt((String) ciudad.get(1));
			if (nivelBrote != 0) { // Cuenta las ciudades estan infectadas
				contador++;
			}

		}

		// Devuelve las ciudades contadas
		return contador;

	}

	/**
	 * Baja el valor a nivel 3 todas las ciudades que ya han sido comprobadas
	 * 
	 * @param ciudadesBrotes se pasan la arraylist con las ciuadades y su nivel de
	 *                       brote
	 */
	public static void bajarComprobadasNivel4(ArrayList<ArrayList> ciudadesBrotes) {

		for (ArrayList ciudad : ciudadesBrotes) {
			int nivelBrote = Integer.parseInt((String) ciudad.get(1));
			if (nivelBrote >= 4) {
				ciudad.set(1, "3");
			}

		}

	}
}
