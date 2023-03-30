import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class enfermedades {
static int enfermedad;
static String linea = "";
static String[] ciudad = new String[4],enfermedades, porcentajeCura;
static String[][] ciudades = new String[49][2];

enfermedades() throws FileNotFoundException, IOException {
	GetBin();
	GetTxt();
	Enfermedad();
	SaveBin();
}

// lee el archivo CCP.bin y lo muestra por pantalla
static void GetBin() {
	try {
		FileInputStream fis = new FileInputStream("src/CCP.bin");
		DataInputStream dis = new DataInputStream(fis);
		System.out.println(dis.readUTF() + "\n" + dis.readInt() + " " + dis.readUTF() + " " + dis.readUTF() + "\n"
				+ dis.readInt() + " " + dis.readUTF() + " " + dis.readUTF() + "\n" + dis.readInt() + " "
				+ dis.readUTF() + " " + dis.readUTF() + "\n" + dis.readInt() + " " + dis.readUTF() + " "
				+ dis.readUTF() + "\n" + dis.readInt() + " " + dis.readInt());
	} catch (IOException e) {
		System.out.println("Error!");
	}
}

// lee el archivo ciudades.txt y lo convierten en una matriz de arrays
static void GetTxt() {
	try {
		FileReader fr = new FileReader("src/ciudades.txt");
		BufferedReader br = new BufferedReader(fr);
		if (linea == null) {
			System.out.println("El archivo eta vacio, Error");
		}
		int i = 0;
		linea = br.readLine();
		while (linea != null) {
			ciudad = linea.split(";");
			ciudades[i][0] = ciudad[0];
			ciudades[i][1] = ciudad[1];
			i++;
			linea = br.readLine();
		}

		fr.close();
		br.close();
	} catch (IOException e) {
		if (linea == null) {
			System.out.println("El archivo eta vacio, Error");
		} else
			System.out.println("Error!");
	}
}

// transforma los numeros de cada enfermedad en el nombre que le coresponda
static void Enfermedad() {
	linea = "";
	for (int i = 0; i < ciudades.length; i++) {
		enfermedad = Integer.parseInt(ciudades[i][1]);
		if (enfermedad == 0) {
			ciudades[i][1] = "Alfa";
		} else if (enfermedad == 1) {
			ciudades[i][1] = "Beta";
		} else if (enfermedad == 2) {
			ciudades[i][1] = "Gama";
		} else if (enfermedad == 3) {
			ciudades[i][1] = "Delta";
		}
	}
}

// guarda todas las ciudades con su enfermedad principal en una unica linea en
// el archivo ciudades-enfermedades.bin
static void SaveBin() {
	try {
		FileWriter fw = new FileWriter("src/ciudades-enfermedades.bin", false);
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 0; i < ciudades.length; i++) {
			linea = linea + ciudades[i][0] + "," + ciudades[i][1] + ";";
		}
		bw.write(linea);
		bw.close();
		fw.close();
	} catch (IOException e) {
		System.out.println("Error E/S: " + e);
	}
}
static void ActualizarCiudades() {
	
}
}
