package backend;

public class accion {

	/* ESTO ES PROVISIONAL, PUEDE SER ELIMINADO */

	public static void investigar(int numero, String ciudad) {
		int codVacuna = Integer.parseInt((String)jugar.nivelBroteCiudades.get(numero).get(2));
		vacunas.investigarCura(codVacuna);
	}

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