import java.io.File;

public class config{

	static void SetDificultad(int Dificultad) {
		File archivo = new File("parametros.xml");
		if (Dificultad == 1) {
			parametros.actualizarValor(archivo, 6, 3, 50, 10);
		} else if (Dificultad == 2) {
			parametros.actualizarValor(archivo, 8, 4, 40, 7);
		} else if (Dificultad == 3) {
			parametros.actualizarValor(archivo, 10, 5, 35, 5);
		}
	}
}
