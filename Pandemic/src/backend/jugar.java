package backend;

import java.util.ArrayList;
import Paneles.*;

public class jugar {

	public static ArrayList<ArrayList> nivelBroteCiudades;
	public static ArrayList<ArrayList> vacunasCura;
	public static int acciones = 4, rondas = 0;
	public static int numBrotes = 0;
	// Array con las ciudades
	public static String[][] ciudades = IA.leerCiudades();

	public static void Main() {

		boolean derrota = false;
		boolean victoria = false;

//			nivelBroteCiudades.get(0).set(1, "3");
		if (acciones > 0) {
//				accion.Main(nivelBroteCiudades, vacunasCura);
			// Turno IA
		} else {
			ArrayList<String> ciudadesAfectadas = IA.infectarCiudadesRondas(nivelBroteCiudades);
			numBrotes = IA.comprobarBroteNivel4(nivelBroteCiudades, numBrotes);
			PanelTablero.mostrarInfecciones(ciudadesAfectadas);
			acciones = 4;
			rondas++;
		}

	}

//		if (victoria == true) {
//			System.out.println("HAS GANADO!");
//		} else {
//			System.out.println("PRINGAO");
//		}

	public static void inicializarPartida() {
		// Array con las ciudades y su nivel de brote
		nivelBroteCiudades = brotes.inicializarNivelBrote(ciudades);
		vacunasCura = vacunas.inicializarVacunas();
	}

	// Comprobar si el jugador ha ganado
	public static boolean comprobarVictoria() {
		return IA.comprobarVictoria(nivelBroteCiudades);
	}

	// Comprobar si el jugador ha perdido
	public static boolean comprobarDerrota() {
		return IA.comprobarDerrota(nivelBroteCiudades, numBrotes);
	}

	public static void cargarDatosPartida(ArrayList<String> sqlvacunas, ArrayList<String> sqlciudades) {

		int x = 0;
		for (ArrayList ciudades : nivelBroteCiudades) {
			ciudades.set(1, sqlciudades.get(x));
			x++;
		}
		x = 0;
		for (ArrayList vacuna : vacunasCura) {
			vacuna.set(1, sqlvacunas.get(x));
			x++;
		}

	}

}
