package backend;

import java.util.ArrayList;
import java.util.Scanner;

public class accion {

	/* ESTO ES PROVISIONAL, PUEDE SER ELIMINADO */

	public static void Main(int opcion, int numero, String ciudad) {

		if (opcion == 2) {
			vacunas.investigarCura(3);
		} else {
			int enf = vacunas.sacarEnfermedadCiudad(ciudad);
			boolean cura = vacunas.comprobarCura(jugar.vacunasCura, enf);
			if (cura == true) {
				brotes.curarTodo(jugar.nivelBroteCiudades, ciudad);
			} else {
				brotes.bajarCiudadParametro(jugar.nivelBroteCiudades, ciudad);
			}
		}
	}
}