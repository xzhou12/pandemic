package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class IA {
	public static final File fileCiudades = new File("ciudades.txt");
	public static final File fileParametros = new File("parametros.xml");

	// ---------------------------------------------------
	// Lee las ciudades que hay en el archivo de ciudades
	// ---------------------------------------------------
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

	// ----------------------------------------------------
	// Cuenta la linea de ciudades que hay en el archivo
	// ----------------------------------------------------
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

	// ----------------------------------
	// Infecta las ciudades por ronda
	// ----------------------------------
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

	// ---------------------------------------------------------
	// Comprueba si alguna ciudad tiene el nivel 4 de infección
	// ---------------------------------------------------------
	public static int comprobarBroteNivel4(ArrayList<ArrayList> ciudadesBrotes, int numBrotes) {

		ArrayList<String> ciudadesNivel4 = new ArrayList<String>();

		for (ArrayList ciudad : ciudadesBrotes) {
			int nivelBrote = Integer.parseInt((String) ciudad.get(1));
			if (nivelBrote >= 4) {
				numBrotes++;
				ciudadesNivel4.add((String) ciudad.get(0));
				Integer.parseInt((String) ciudad.set(1, "3"));
			}

		}

		brotes.addBrotesColindante(ciudadesNivel4, ciudadesBrotes);
		return numBrotes;

	}

	// -----------------------------------------------
	// Comprueba la victoria de parte de las ciudades
	// -----------------------------------------------
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

	// --------------------------------------
	// Comprueba la victoria de las vacunas
	// --------------------------------------
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

	// -------------------------------------------------------
	// Comprueba la derrota, con las dos formas de derrota
	// -------------------------------------------------------
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
			JOptionPane.showMessageDialog(null, "NUM BROTES: " + brotes);
			sonido.pulsarBoton();
			return false;
		} else if (contador >= numEAD) {
			sonido.pulsarBoton();
			JOptionPane.showMessageDialog(null, "ENF ACTIVAS " + contador);
			sonido.pulsarBoton();
			return false;
		}

		return false;
	}

	// -----------------------------------------------------------------
	// Cuenta el numero de enfermedades activas y devolvuelve el valor
	// -----------------------------------------------------------------
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
}
