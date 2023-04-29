import java.util.ArrayList;
import java.util.Scanner;

public class jugar {

	public static void Main() {

		Scanner s = new Scanner(System.in);

		int rondas = 0;
		int[] numBrotes = { 0 };
		boolean derrota = false;
		boolean victoria = false;

		// Array con las ciudades
		String[][] ciudades = IA.leerCiudades();

		// Array con las ciudades y su nivel de brote
		ArrayList<ArrayList> nivelBroteCiudades = brotes.inicializarNivelBrote(ciudades);
		ArrayList<ArrayList> vacunasCura = vacunas.inicializarVacunas();

		for (ArrayList ciudadess : nivelBroteCiudades) {
			System.out.println(ciudadess.get(0) + "," + ciudadess.get(1));
		}

		while (derrota == false && victoria == false) {
			// Turno jugador
			System.out.println("-----------------------------------------------");
			System.out.println("TU TURNO");
			System.out.println("-----------------------------------------------");
			accion.Main(nivelBroteCiudades, vacunasCura);
			// Turno IA

			ArrayList<String> ciudadesAfectadas = IA.infectarCiudadesRondas(nivelBroteCiudades);
			System.out.println("CIUDADES AFECTADAS: " + ciudadesAfectadas);

			// Comprobaciones
			IA.comprobarBroteNivel4(nivelBroteCiudades, numBrotes);
			victoria = IA.comprobarVictoria(nivelBroteCiudades);
			derrota = IA.comprobarDerrota(nivelBroteCiudades, numBrotes);

			rondas++;

			for (ArrayList ciudadess : nivelBroteCiudades) {
				System.out.println(ciudadess.get(0) + "," + ciudadess.get(1));
			}

		}

	}

}
