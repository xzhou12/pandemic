package backend;

import java.util.ArrayList;
import java.util.Random;

/**
 * vacunas En esta clase es donde se hacen la mayoria de gestiones relacionadas
 * con las vacunas
 * 
 * @author Xiaobin Zhou
 * @version 1.0
 */

public class vacunas {

	/**
	 * Inicializa la Array de las vacunas
	 * 
	 * @return ArrayList devuelve las vacunas ya inicializadas
	 */
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

	/**
	 * Investiga la cura que le pasamos por parametro
	 * 
	 * @param vacuna se pasa la vacuna que quiere investigar
	 */
	public static void investigarCura(int vacuna) {
		Random r = new Random();

		// Investiga la vacunas, cada investigación suma entre 18 y 28%
		int porcentaje = Integer.parseInt((String) jugar.vacunasCura.get(vacuna).get(1));
		porcentaje += (r.nextInt(10) + 18);
		if (porcentaje > 100) {
			porcentaje = 100; // Si supera los 100, se queda en 100
		}
		jugar.vacunasCura.get(vacuna).set(1, Integer.toString(porcentaje));
	}

	/**
	 * Comprueba si la vacuna ya esta al 100% de su investigación
	 * 
	 * @param vacunasCura se pasa la arraylist con el porcentaje de cura
	 * @param vacuna      se pasa la vacuna que quiere comprobar
	 * @return boolean se devuelve si esta lista o no la cura
	 */
	public static boolean comprobarCura(ArrayList<ArrayList> vacunasCura, int vacuna) {

		int porcentaje = Integer.parseInt((String) vacunasCura.get(vacuna).get(1));

		if (porcentaje >= 100) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Devuelve el codigo de la enfermedad que tiene x ciudad
	 * 
	 * @param ciudadSeleccionado se pasa la ciuada que queremos que saque el codigo
	 *                           de la vacuna
	 * @return int se devuelve el codigo de la vacuna
	 */
	public static int sacarEnfermedadCiudad(String ciudadSeleccionado) {

		for (ArrayList ciudad : jugar.nivelBroteCiudades) {
			String ciudadAux = (String) ciudad.get(0);
			if (ciudadAux.equals(ciudadSeleccionado)) {
				return Integer.parseInt((String) ciudad.get(2));
			}
		}

		return -1;

	}

	/**
	 * Devuelve los porcentajes de las vacunas
	 * 
	 * @param vacunasCura se pasa la arraylist con el porcentaje de cura de las
	 *                    vacunas
	 * @return int[] Devuelve los porcentajes de las vacunas
	 */
	public static int[] getPorcentajes(ArrayList<ArrayList> vacunasCura) {
		int[] porcentaje = new int[4];

		porcentaje[0] = Integer.parseInt((String) vacunasCura.get(0).get(0));
		porcentaje[1] = Integer.parseInt((String) vacunasCura.get(1).get(1));
		porcentaje[2] = Integer.parseInt((String) vacunasCura.get(2).get(2));
		porcentaje[3] = Integer.parseInt((String) vacunasCura.get(3).get(3));
		return porcentaje;
	}

}
