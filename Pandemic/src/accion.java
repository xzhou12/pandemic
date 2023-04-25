import java.util.ArrayList;
import java.util.Scanner;

public class accion {

	public static void Main(ArrayList<ArrayList> broteCiudades, ArrayList<ArrayList> vacunasCura) {
		Scanner s = new Scanner(System.in);
		// 1 CURAR
		// 2 INVESTIGAR
		int opcion = s.nextInt();
		if (opcion == 1) {
			System.out.println("Que vacuna?");
			System.out.println("Alfa " + "Beta " + "Gama " + "Delta ");
			vacunas.investigarCura(vacunasCura, s.nextInt());
			for (ArrayList vacuna : vacunasCura) {
				System.out.println(vacuna);
			}

		} else {
			curarCiudades(broteCiudades);
		}

	}

	public static void inversigarCura() {
		// PREGUNTAR SI INVESTIGAR ES 1 O TODAS

	}

	public static void curarCiudades(ArrayList<ArrayList> broteCiudades) {
		// CURAR
		Scanner t = new Scanner(System.in);

		for (int i = 0; i < 4; i++) {
			System.out.println("CUAL?");

			for (ArrayList ciudad : broteCiudades) {
				System.out.println(ciudad);
			}
			brotes.bajarCiudadParametro(broteCiudades, t.nextLine());

		}

	}

}