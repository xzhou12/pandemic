package backend;

public class accion {

	/* ESTO ES PROVISIONAL, PUEDE SER ELIMINADO */

	//-----------------------------------------------------------
	//investiga la enfermedad de la ciudad en la que se ecuentra
	//-----------------------------------------------------------
	public static void investigar(int numero, String ciudad) {
		int codVacuna = Integer.parseInt((String)jugar.nivelBroteCiudades.get(numero).get(2));
		vacunas.investigarCura(codVacuna);
	}
	
	
	//--------------------------------------------------------------------------------------------------
	//cura la ciudad, si tiene la vacuna la cura por completo aunque tenga mas de 1 nivel de enfermedad
	//--------------------------------------------------------------------------------------------------
	public static void curar(int numero, String ciudad) {
		int enf = vacunas.sacarEnfermedadCiudad(ciudad);
		boolean cura = vacunas.comprobarCura(jugar.vacunasCura, enf);
		if (cura == true) {
			brotes.curarTodo(jugar.nivelBroteCiudades, ciudad);
		} else {
			brotes.bajarCiudadParametro(jugar.nivelBroteCiudades, ciudad);
		}
	}
}