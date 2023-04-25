import java.util.ArrayList;
import java.util.Random;

public class vacunas {

	// Inicializa la Array de las vacunas
	public static ArrayList<ArrayList> inicializarVacunas() {
		ArrayList<ArrayList> vacunasVirus = new ArrayList<ArrayList>();
		String[] nombreVirus = { "Alfa", "Beta", "Gama", "Delta" };

		// Añade a la ArrayList el nombre de la vacuna junto a un 0% de investigación
		for (int i = 0; i < nombreVirus.length; i++) {
			ArrayList<String> vacuna = new ArrayList<String>();
			vacuna.add(nombreVirus[i]);
			vacuna.add("0");
			vacunasVirus.add(vacuna);
		}

		// Retorna la vacuna
		return vacunasVirus;
	}

	// Investiga la cura que le pasamos por parametro
	public static void investigarCura(ArrayList<ArrayList> vacunasCura, int vacuna) {
		Random r = new Random();

		int porcentaje = Integer.parseInt((String) vacunasCura.get(vacuna).get(1));
		porcentaje += (r.nextInt(10) + 15);
		if (porcentaje > 100) {
			porcentaje = 100;
		}
		vacunasCura.get(vacuna).set(1, Integer.toString(porcentaje));
	}

	public static boolean comprobarCura(ArrayList<ArrayList> vacunasCura, int vacuna) {
		Random r = new Random();

		int porcentaje = Integer.parseInt((String) vacunasCura.get(vacuna).get(1));

		if (porcentaje >= 100) {
			return true;
		} else {
			return false;
		}

	}

}
