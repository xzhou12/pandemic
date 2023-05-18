package backend;

/**
* Acciones del jugador
* Esta clase realiza y procede las acciones seleccionadas por el jugador.
* @author Xiaobin Zhou
* @version 1.0
*/

public class accion {

	/**
	* INVESTIGAR:
	* Investiga la enfermedad de la ciudad en la que se ecuentra
	* @param numero se le pasa el numero de la ciudad
	* @param ciudad se le pasa la ciudad
	*/
	public static void investigar(int numero, String ciudad) {
		// Saca el codigo de la ciudad que se quiere investigar
		int codVacuna = Integer.parseInt((String) jugar.nivelBroteCiudades.get(numero).get(2));
		// Y se manda a investigar
		vacunas.investigarCura(codVacuna);
	}

	/**
	* CURAR:
	* Cura la ciudad, si tiene la vacuna la cura por completo aunque tenga mas de 1 nivel de enfermedad
	* @param numero se le pasa el numero de la ciudad
	* @param ciudad se le pasa la ciudad
	*/
	public static void curar(int numero, String ciudad) {
		// Saca el codigo de la ciudad y comprueba si la cura esta creada
		int enf = vacunas.sacarEnfermedadCiudad(ciudad);
		boolean cura = vacunas.comprobarCura(jugar.vacunasCura, enf);
		if (cura == true) {
			// Si esta creada, cura todo
			brotes.curarTodo(jugar.nivelBroteCiudades, ciudad);
		} else {
			// Si no, baja un nivel
			brotes.bajarCiudadParametro(jugar.nivelBroteCiudades, ciudad);
		}
	}
}