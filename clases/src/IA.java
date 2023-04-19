import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class IA {
	boolean alfa, beta, gama, delta = true;

	// Inicializa los brotes encima de la partida
	public static void infectarCiudadesInicio(ArrayList<ArrayList> ciudadesBrotes) {
		File fileParametros = new File("parametros.xml");
		String[] param = parametros.leerArchivo(fileParametros);

		// numCiudadesInfectadasInicio
		int numCII = Integer.parseInt(param[0]);
		for (int i = 0; i < numCII; i++) {
			infectarCiudadesAleatorio(ciudadesBrotes);
		}

	}

	// Infecta las ciudades de forma aleatoria
	static void infectarCiudadesAleatorio(ArrayList<ArrayList> ciudadesBrotes) {
		Random r = new Random();
		int numR = r.nextInt(ciudadesBrotes.size());
		int nivel = Integer.parseInt((String) ciudadesBrotes.get(numR).get(1));
		nivel = 4;
		ciudadesBrotes.get(numR).set(1, Integer.toString(nivel));

	}

	// Comprueba si alguna ciudad tiene el nivel 4 de infección
	static void comprobarBroteNivel4(ArrayList<ArrayList> ciudadesBrotes) {

		for (ArrayList aux : ciudadesBrotes) {
			int nivel = Integer.parseInt((String) aux.get(1));
			if (nivel >= 4) {
				addBrotes(ciudadesBrotes, (String) aux.get(1));
			}
		}

	}

	static void addBrotes(ArrayList<ArrayList> ciudadesBrotes, String ciudad) {

	}

	static void comprovarCura() {

	}
}
