package backend;

public class accion {

	// -----------------------------------------------------------
	// investiga la enfermedad de la ciudad en la que se ecuentra
	// -----------------------------------------------------------
	public static void investigar(int numero, String ciudad) {
		// Saca el codigo de la ciudad que se quiere investigar
		int codVacuna = Integer.parseInt((String) jugar.nivelBroteCiudades.get(numero).get(2));
		// Y se manda a investigar
		vacunas.investigarCura(codVacuna);
	}

	// --------------------------------------------------------------------------------------------------
	// cura la ciudad, si tiene la vacuna la cura por completo aunque tenga mas de 1
	// nivel de enfermedad
	// --------------------------------------------------------------------------------------------------
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