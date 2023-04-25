import java.util.ArrayList;

public class jugar {

	public static void Main() {

		boolean derrota, victoria;
		derrota = victoria = false;

		// Array con las ciudades
		String[][] ciudades = IA.leerCiudades();

		int[] numBrotes = { 0 };

		// Array con las ciudades y su nivel de brote
		ArrayList<ArrayList> nivelBroteCiudades = IA.inicializarNivelBrote(ciudades);
		ArrayList<ArrayList> vacunasCura = vacunas.inicializarVacunas();

		while (derrota == false && victoria == false) {
			// Turno jugador

			IA.infectarCiudadesRondas(nivelBroteCiudades);
			IA.comprobarBroteNivel4(nivelBroteCiudades, numBrotes);

			for (ArrayList ciudad : nivelBroteCiudades) {
				System.out.println(ciudad);
			}
			victoria = IA.comprobarVictoria(nivelBroteCiudades);
			derrota = IA.comprobarDerrota(nivelBroteCiudades, numBrotes);
			victoria = true;

		}

	}

}
