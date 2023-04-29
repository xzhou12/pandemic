import java.util.ArrayList;
import java.util.Scanner;

public class accion {

	/* ESTO ES PROVISIONAL, PUEDE SER ELIMINADO */

	public static void Main(ArrayList<ArrayList> broteCiudades, ArrayList<ArrayList> vacunasCura) {
		Scanner s = new Scanner(System.in);
		System.out.println("1: INVESTIGAR \n2: CURAR");
		if (s.nextInt() == 1) {
			inversigarCura(vacunasCura);
		} else {
			curarCiudades(broteCiudades);
		}

	}

	public static void inversigarCura(ArrayList<ArrayList> vacunasCura) {
		Scanner s = new Scanner(System.in);

		System.out.println("Que vacuna?");
		System.out.println("Alfa " + "Beta " + "Gama " + "Delta ");
		vacunas.investigarCura(vacunasCura, s.nextInt());
		for (ArrayList vacuna : vacunasCura) {
			System.out.println(vacuna);
		}

	}

	public static void curarCiudades(ArrayList<ArrayList> broteCiudades) {
		// CURAR
		Scanner t = new Scanner(System.in);

		for (int i = 0; i < 4; i++) {
			System.out.println("CUAL?");

			// FALTA CURAR TODO SI HAY UNA VACUNA HECHA

			brotes.bajarCiudadParametro(broteCiudades, t.nextLine());

			for (int k = 0; k < broteCiudades.size(); k++) {
				System.out.println(k + ": " + broteCiudades.get(k).get(0) + ", " + broteCiudades.get(k).get(1));
			}

		}

	}

}