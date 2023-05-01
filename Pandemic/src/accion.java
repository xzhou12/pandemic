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
			curarCiudades(broteCiudades, vacunasCura);
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

	public static void curarCiudades(ArrayList<ArrayList> broteCiudades, ArrayList<ArrayList> vacunasCura) {
		// CURAR
		Scanner t = new Scanner(System.in);

		for (int i = 0; i < 4; i++) {
			System.out.println("CUAL?");
			String ciudadSelect = t.nextLine();
			// FALTA CURAR TODO SI HAY UNA VACUNA HECHA
			int enf = vacunas.sacarEnfermedadCiudad(broteCiudades, ciudadSelect);

			boolean cura = vacunas.comprobarCura(vacunasCura, enf);

			if (cura == true) {
				brotes.curarTodo(broteCiudades, ciudadSelect);
			} else {
				brotes.bajarCiudadParametro(broteCiudades, ciudadSelect);
			}

			for (int k = 0; k < broteCiudades.size(); k++) {
				if (!broteCiudades.get(k).get(1).equals("0"))
					System.out.println(k + ": " + broteCiudades.get(k).get(0) + ", " + broteCiudades.get(k).get(1));
			}

		}

	}

}