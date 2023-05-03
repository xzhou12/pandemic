package backend;

import java.util.ArrayList;
import java.util.Scanner;

public class jugar {

	public static ArrayList<ArrayList> nivelBroteCiudades;
	public static ArrayList<ArrayList> vacunasCura;
	public static int acciones = 4, rondas = 0;
	private static int numBrotes = 0;
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
			Paneles.PanelTablero.mostrarInfecciones(ciudadesAfectadas);
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

	public static boolean comprobarVictoria() {
		// Comprobaciones
		return IA.comprobarVictoria(nivelBroteCiudades);
	}

	public static boolean comprobarDerrota() {
		// Comprobaciones
		return IA.comprobarDerrota(nivelBroteCiudades, numBrotes);
	}

}
