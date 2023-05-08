package backend;

import java.util.ArrayList;
import java.util.Random;

public class vacunas {
	// -----------------------------------
	// Inicializa la Array de las vacunas
	// -----------------------------------
	public static ArrayList<ArrayList> inicializarVacunas() {
		ArrayList<ArrayList> vacunasVirus = new ArrayList<ArrayList>();
		String[] nombreVirus = { "Alfa", "Beta", "Gama", "Delta" };

		// Añade a la ArrayList el nombre de la vacuna junto a un 0% de investigación
		for (int i = 0; i < nombreVirus.length; i++) {
			ArrayList<String> vacuna = new ArrayList<String>();
			vacuna.add(nombreVirus[i]);
			vacuna.add("0");
			vacunasVirus.add(vacuna);
		}

		// Retorna la vacuna
		return vacunasVirus;
	}

	// ------------------------------------------------
	// Investiga la cura que le pasamos por parametro
	// ------------------------------------------------
	public static void investigarCura(int vacuna) {
		Random r = new Random();

		// Investiga la vacunas, cada investigación suma entre 15 y 25%
		int porcentaje = Integer.parseInt((String) jugar.vacunasCura.get(vacuna).get(1));
		porcentaje += (r.nextInt(10) + 100);
		if (porcentaje > 100) {
			porcentaje = 100; // Si supera los 100, se queda en 100
		}
		jugar.vacunasCura.get(vacuna).set(1, Integer.toString(porcentaje));
	}

	// -----------------------------------------------------------
	// Comprueba si la vacuna ya esta al 100% de su investigación
	// -----------------------------------------------------------
	public static boolean comprobarCura(ArrayList<ArrayList> vacunasCura, int vacuna) {

		int porcentaje = Integer.parseInt((String) vacunasCura.get(vacuna).get(1));

		if (porcentaje >= 100) {
			return true;
		} else {
			return false;
		}

	}

	// -------------------------------------------------------
	// Devuelve el codigo de la enfermedad que tiene x ciudad
	// -------------------------------------------------------
	public static int sacarEnfermedadCiudad(String ciudadSeleccionado) {

		for (ArrayList ciudad : jugar.nivelBroteCiudades) {
			String ciudadAux = (String) ciudad.get(0);
			if (ciudadAux.equals(ciudadSeleccionado)) {
				return Integer.parseInt((String) ciudad.get(2));
			}
		}

		return -1;

	}

	// ----------------------------------------
	// devuelve los porcentajes de las vacunas
	// ----------------------------------------
	public static int[] getPorcentajes(ArrayList<ArrayList> vacunasCura) {
		int[] porcentaje = new int[4];

		porcentaje[0] = Integer.parseInt((String) vacunasCura.get(0).get(0));
		porcentaje[1] = Integer.parseInt((String) vacunasCura.get(1).get(1));
		porcentaje[2] = Integer.parseInt((String) vacunasCura.get(2).get(2));
		porcentaje[3] = Integer.parseInt((String) vacunasCura.get(3).get(3));
		return porcentaje;
	}

}
