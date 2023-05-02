package backend;
import java.util.ArrayList;
import java.util.Scanner;

public class jugar {

	static ArrayList<ArrayList> nivelBroteCiudades;
	static ArrayList<ArrayList> vacunasCura;
	public static void Main() {

		Scanner s = new Scanner(System.in);

		int rondas = 0;
		int[] numBrotes = { 0 };
		boolean derrota = false;
		boolean victoria = false;

		// Array con las ciudades
		String[][] ciudades = IA.leerCiudades();

		// Array con las ciudades y su nivel de brote
		nivelBroteCiudades = brotes.inicializarNivelBrote(ciudades);
		vacunasCura = vacunas.inicializarVacunas();

		for (ArrayList ciudadess : nivelBroteCiudades) {
			if (!ciudadess.get(1).equals("0"))
				System.out.println(ciudadess.get(0) + "," + ciudadess.get(1));
		}

		while (derrota == false && victoria == false) {

			nivelBroteCiudades.get(0).set(1, "3");
			if (rondas % 2 == 0) {
				// Turno jugador
				System.out.println("-----------------------------------------------");
				System.out.println("TU TURNO");
				System.out.println("-----------------------------------------------");
				accion.Main(nivelBroteCiudades, vacunasCura);
				// Turno IA
			} else {
				ArrayList<String> ciudadesAfectadas = IA.infectarCiudadesRondas(nivelBroteCiudades);
				System.out.println("CIUDADES AFECTADAS: " + ciudadesAfectadas);
				for (ArrayList ciudadess : nivelBroteCiudades) {
					if (!ciudadess.get(1).equals("0"))
						System.out.println(ciudadess.get(0) + "," + ciudadess.get(1));
				}
			}

			// Comprobaciones
			IA.comprobarBroteNivel4(nivelBroteCiudades, numBrotes);
			victoria = IA.comprobarVictoria(nivelBroteCiudades);
			derrota = IA.comprobarDerrota(nivelBroteCiudades, numBrotes);

			rondas++;

		}

		if (victoria == true) {
			System.out.println("HAS GANADO!");
		} else {
			System.out.println("PRINGAO");
		}

	}

}
